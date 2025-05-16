package com.gh.service.impl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

import com.gh.child.Guest;
import com.gh.exception.NoBreakfastException;
import com.gh.exception.NoReservationException;
import com.gh.exception.NoRoomException;
import com.gh.rsv.Breakfast;
import com.gh.rsv.FoodShop;
import com.gh.rsv.Party;
import com.gh.rsv.Reservation;
import com.gh.rsv.Room;
import com.gh.service.GehaService;
import com.gh.util.Date;

public class GahaServiceImpl implements GehaService {
	private HashMap<Integer, Reservation> rsvMap; // 예약번호를 Key, 예약 객체를 Value로 담은 HashMap
	private HashMap<String, Room> roomMap; // (중복 없는) 방 정보를 Key, 방 객체를 Value로 담은 HashMap
	private HashMap<Integer, Integer> partyMap = new HashMap<>(); // 예약 번호를 Key, 파티 참가비를 Value로 담은 HashMap
	private HashMap<Integer, Date> breakfastMap = new HashMap<>(); // 예약 번호를 Key, 날짜 정보를 Value로 담은 HashMap
	public static final int NUMBER_OF_ROOM = 20; // 게스트하우스 전체 방 개수
	private int rsvNum = 1; // 예약 번호

	// 싱글톤
	static private GahaServiceImpl service = new GahaServiceImpl();

	public GahaServiceImpl() {
		super();
		rsvMap = new HashMap<>();
	}

	public static GahaServiceImpl getInstance() {
		return service;
	}

	// roomMap을 사용하기 위해 setter getter 메소드 추가
	public void setRoomMap(HashMap<String, Room> roomMap) {
		this.roomMap = roomMap;
	}

	public HashMap<String, Room> getRoomMap() {
		return roomMap;
	}

	// partyMap을 사용하기 위해 getter 메소드 추가
	public HashMap<Integer, Integer> getPartyMap() {
		return partyMap;
	}

	// rsvMap을 사용하기 위해 getter 메소드 추가
	public HashMap<Integer, Reservation> getRsvMap() {
		return rsvMap;
	}

	// breakfastMap을 사용하기 위해 setter getter 메소드 추가
	public void setbreakfastMap(HashMap<Integer, Date> breakfastMap) {
		this.breakfastMap = breakfastMap;
	}

	public HashMap<Integer, Date> getbreakfastMap() {
		return breakfastMap;
	}

	@Override
	public List<Room> searchAvailableRoom(Date rsvDate, char gender) throws NoRoomException { // 예약 가능 여부 확인
		return roomMap.keySet().stream()
				.filter(r -> r.substring(0, 1).equals(String.valueOf(gender))
						&& Integer.parseInt(r.substring(1, 2)) > roomMap.get(r).getBooked())
				.sorted((o1, o2) -> o1.compareTo(o2)).map(r -> roomMap.get(r)).toList();
	}

	@Override
	public Reservation makeRsv(Date rsvDate, char gender, int roomType, Guest rsvGuest, Party party,
			Breakfast breakfast) throws NoRoomException { // C
		Reservation tempRsv = null;
		List<Room> roomList = searchAvailableRoom(rsvDate, gender);
		for (Room r : roomList) { // gender 조건 만족하는 방 중에서
			if (r.getRoomType() == roomType) { // 방 타입(인원수)가 맞다면
				r.setBooked(r.getBooked() + 1);
				tempRsv = new Reservation(rsvNum, rsvDate, rsvGuest, r, party, breakfast);
				rsvMap.put(rsvNum, tempRsv);
				break;
			}
		}
		if (tempRsv == null)
			throw new NoRoomException("예약 가능한 방이 없습니다.");
		// attendFee 랑 eatBreakfast 인자값 받은 거 Party Map과 Breakfast Map에 넣기

		if (party != null) {
			partyMap.put(rsvNum, party.getAttendFee());
		}
		if (breakfast.getEatBreakfast() == true)
			breakfastMap.put(rsvNum, rsvDate);
		rsvNum++;
		return tempRsv;
	}

	@Override
	public void updateRsv(int rsvNum, Reservation rsv) throws NoReservationException { // U
		if (rsvMap.containsKey(rsvNum)) {

			// 기존 예약 정보 확인
			Reservation oldRsv = rsvMap.get(rsvNum);

			if (oldRsv == null)
				return;// 예약이 없으면 무시

			Room oldRoom = oldRsv.getRsvRoom();
			Room newRoom = rsv.getRsvRoom();

			// 방이 바뀌었을때
			if (!oldRoom.getRoomNum().equals(newRoom.getRoomNum())) {
				// 기존 방 인원 -1
				oldRoom.setBooked(oldRoom.getBooked() - 1);
				roomMap.put(oldRoom.getRoomNum(), oldRoom);
				// 새방 인원 +1
				newRoom.setBooked(newRoom.getBooked() + 1);
				roomMap.put(newRoom.getRoomNum(), newRoom);
				System.out.println(oldRoom.getRoomNum() + "에서 " + newRoom.getRoomNum() + "으로 방을 이동합니다.");
			}
			// breakMap 갱신
			if (rsvMap.get(rsvNum).getBreakfast().getEatBreakfast() == false
					&& rsv.getBreakfast().getEatBreakfast() == true)
				breakfastMap.put(rsvNum, rsv.getRsvDate());
			else if (rsvMap.get(rsvNum).getBreakfast().getEatBreakfast() == true
					&& rsv.getBreakfast().getEatBreakfast() == false)
				breakfastMap.remove(rsvNum);
			// rsvMap 갱신
			rsvMap.put(rsvNum, rsv);
		} else
			throw new NoReservationException("예약 내역이 없습니다.");
	}

	@Override
	public void deleteRsv(int rsvNum) throws NoReservationException { // D
		if (rsvMap.containsKey(rsvNum)) {
			Room findRoom = rsvMap.get(rsvNum).getRsvRoom();
			findRoom.setBooked(findRoom.getBooked() - 1);
			rsvMap.remove(rsvNum); // 예약 Map에서 해당 예약 삭제
			if (breakfastMap.containsKey(rsvNum))
				breakfastMap.remove(rsvNum);
			System.out.println("예약번호 " + rsvNum + "에 해당하는 예약 내역을 삭제하였습니다.");
		} else
			throw new NoReservationException("예약 내역이 없습니다.");
	}

	@Override
	public String searchRsv(int rsvNum) { // 특정 R
		for (Reservation r : rsvMap.values()) {
			if (r.getRsvNum() == rsvNum) {
				System.out.println("예약 정보는 다음과 같습니다.");
				return r.toString();
			}
		}
		return "예약정보가 없습니다.";
	}

	@Override
	public List<Reservation> searchAllRsv(Date rsvDate) { // 전체 R : ArrayList<Reservation> -> List<Reservation>
		return rsvMap.values().stream().sorted((o1, o2) -> o1.getRsvNum() - o2.getRsvNum()).toList();
	}

	@Override
	public String searchRsvCondition(Date rsvDate) {
//		String s = null; -> 스트링을 했는데  안정적이지 않다.
		StringBuilder sb = new StringBuilder();
		for (Room r : roomMap.values()) {
			String room = r.getRoomNum();
			int booked = r.getBooked();
			int max = r.getRoomType();
			sb.append(room).append(" ").append(booked).append("/").append(max).append("-");
		}
		return sb.toString();
	}

	@Override
	public Party searchParty(int rsvNum) {
		Reservation rsv = rsvMap.get(rsvNum);
		if (rsv != null) {
			return rsv.getParty();
		}
		return null; // 예약이 없으면 null 리턴
	}

	@Override
	public List<Party> searchParty(Date rsvDate) {
	    ArrayList<Party> parties = new ArrayList<>();
	    return rsvMap.values().stream()
				.filter(r->
				r.getRsvDate().getYear() == rsvDate.getYear()
                && r.getRsvDate().getMonth() == rsvDate.getMonth()
                && r.getRsvDate().getDay() == rsvDate.getDay())
			.map(r->r.getParty())
			.toList();
	}
	

	
	@Override
	public void makeParty(Date rsvDate) {
		ArrayList<Reservation> rsvList = new ArrayList<>();
		for (Reservation r : rsvMap.values()) {
			if (r.getRsvDate().getYear() == rsvDate.getYear() && r.getRsvDate().getMonth() == rsvDate.getMonth()
					&& r.getRsvDate().getDay() == rsvDate.getDay()) {
				rsvList.add(r);
			}
		}
		// 참가비별로 분류
		ArrayList<Reservation> three = new ArrayList<>();
		ArrayList<Reservation> four = new ArrayList<>();
		ArrayList<Reservation> five = new ArrayList<>();

		for (Reservation r : rsvList) {
			Integer fee = partyMap.get(r.getRsvNum());
			if (fee == null)
				continue;
			if (fee == 30000) {
				three.add(r);
			} else if (fee == 40000) {
				four.add(r);
			} else if (fee == 50000) {
				five.add(r);
			}
		}

		int partyNumSeq = 1;

		// 각 그룹별로 처리
		ArrayList<Reservation>[] partyGroups = new ArrayList[] { three, four, five };
		for (ArrayList<Reservation> group : partyGroups) {
			int idx = 0;
			while (idx < group.size()) {
				int remaining = group.size() - idx;
				if (remaining >= 11 && remaining <= 13) {
					// 11~13명은 반으로 나눈다
					int mid = remaining / 2;
					for (int i = 0; i < mid; i++) {
						group.get(idx + i)
								.setParty(new Party(partyNumSeq, partyMap.get(group.get(idx + i).getRsvNum())));
					}
					partyNumSeq++;
					for (int i = mid; i < remaining; i++) {
						group.get(idx + i)
								.setParty(new Party(partyNumSeq, partyMap.get(group.get(idx + i).getRsvNum())));
					}
					partyNumSeq++;
					idx += remaining;
				} else if (remaining >= 4 && remaining <= 10) {
					// 4명 이상 10명 이하
					for (int i = 0; i < remaining; i++) {
						group.get(idx + i)
								.setParty(new Party(partyNumSeq, partyMap.get(group.get(idx + i).getRsvNum())));
					}
					partyNumSeq++;
					idx += remaining;
				} else if (remaining < 4) {
					// 남은 인원이 4명 미만이면
					// 다음 그룹에 재배치할 거니까 break
					break;
				}
			}
		}

		// 4명 미만 재배치
		ArrayList<Reservation> leftovers = new ArrayList<>();
		for (ArrayList<Reservation> group : partyGroups) {
			for (Reservation r : group) {
				if (r.getParty() == null) {
					leftovers.add(r);
				}
			}
		}

		int idx = 0;
		while (idx < leftovers.size()) {
			int remaining = leftovers.size() - idx;
			if (remaining >= 4) {
				int count = Math.min(remaining, 10); // 10명 넘으면 안 됨
				for (int i = 0; i < count; i++) {
					leftovers.get(idx + i)
							.setParty(new Party(partyNumSeq, partyMap.get(leftovers.get(idx + i).getRsvNum())));
				}
				partyNumSeq++;
				idx += count;
			} else {
				// 4명도 안 되는 나머지는 파티 배정 안 함
				break;
			}
		}
	}

	@Override
	public List<Guest> searchBreakfastGuest(Date rsvDate) throws NoBreakfastException { // ArrayList<Guest> ->
																						// List<Guest>
		return breakfastMap.keySet().stream()
				.filter(b -> breakfastMap.get(b).getYear() == rsvDate.getYear()
						&& breakfastMap.get(b).getMonth() == rsvDate.getMonth()
						&& breakfastMap.get(b).getDay() == rsvDate.getDay())
				.sorted((o1, o2) -> o1 - o2).map(b -> rsvMap.get(b).getRsvGuest()).toList();
	}

	// case 10~12를 위한 메소드
	@Override
	public int searchBreakfastNum(Date rsvDate) throws NoBreakfastException {
		int find = 0;
		for (Date d : breakfastMap.values()) {
			if (d.getYear() == rsvDate.getYear() && d.getMonth() == rsvDate.getMonth()
					&& d.getDay() == rsvDate.getDay())
				find += 1;
		}
		if (find == 0)
			throw new NoBreakfastException("해당일에 조식을 신청한 인원이 없습니다.");
		return find;
	}

	@Override
	public int makeBreakfast(Date rsvDate) throws NoBreakfastException {
		FoodShop fs = new FoodShop();
		HashMap<Integer, Integer> food = new HashMap<>();
		food.put(15, 3000);
		food.put(10, 3300);
		food.put(1, 4000);
		fs.setFoodMap(food);

//		ArrayList<Guest> mealList = searchBreakfastGuest(rsvDate);
//		int mealNum = mealList.size();
		int mealNum = searchBreakfastNum(rsvDate);
		int temp = mealNum;
		int total = 0;
		// 그리디 알고리즘
		Integer[] price = { 15, 10, 1 };
		for (int i : price) {
			if (temp >= i) {
				int j = temp / i;
				temp -= i * j;
				total += i * food.get(i) * j;
			}
		}
		return total / mealNum;
	}

	@Override
	public int searchAvgBreakfastPrice(int year, int month) throws NoBreakfastException {
		HashMap<Date, Integer> findBfPrice = new HashMap<>();
		int total = 0; // makeBreakfast 반환값을 더할 변수
		HashMap<Integer, Integer> monthDay = new HashMap<>();

		for (Date d : breakfastMap.values()) {
			if (d.getYear() == year && d.getMonth() == month) { // 찾으려는 연도, 월과 일치하는 날이라면
//				if(!findBfPrice.containsKey(new Date(d.getYear(), d.getMonth(), d.getDay())))
//					findBfPrice.put(d, makeBreakfast(d));
				// Date의 .toString 코드 떄문에 위의 코드는 날짜 비교를 못함
				boolean isDuplicate = false;
				for (Date existingDate : findBfPrice.keySet()) {
					// 이미 같은 날짜가 있으면 중복으로 처리
					if (existingDate.getYear() == d.getYear() && existingDate.getMonth() == d.getMonth()
							&& existingDate.getDay() == d.getDay()) {
						isDuplicate = true;
						break;
					}
				}
				if (!isDuplicate) {
					findBfPrice.put(d, makeBreakfast(d)); // 중복되지 않으면 추가
				}
			}
		}
		for (int i : findBfPrice.values()) {
			total += i;
		}
		return total / findBfPrice.size();
	}

}