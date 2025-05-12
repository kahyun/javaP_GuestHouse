package com.gh.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeSet;

import com.gh.child.Guest;
import com.gh.exception.NoBreakfastException;
import com.gh.exception.NoRoomException;
import com.gh.rsv.FoodShop;
import com.gh.rsv.Party;
import com.gh.rsv.Reservation;
import com.gh.rsv.Room;
import com.gh.service.GehaService;
import com.gh.util.Date;

public  class GahaServiceImpl implements GehaService {
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
	public HashMap<Integer, Integer> getPartyMap() {
	    return partyMap;
	}

	@Override
	public TreeSet<Room> searchAvailableRoom(Date rsvDate, char gender) { // 예약 가능 여부 확인
		TreeSet<Room> temp = new TreeSet<>();
		if(gender == 'F') { // 여자 방 조회
			for(String k : roomMap.keySet()) {
				if(k.substring(0, 1).equals("F") && Integer.parseInt(k.substring(1, 2)) > roomMap.get(k).getBooked()) {
					temp.add(roomMap.get(k));
				}
			}
		} else { // 남자 방 조회
			for(String k : roomMap.keySet()) {
				if(k.substring(0, 1).equals("M") && Integer.parseInt(k.substring(1, 2)) > roomMap.get(k).getBooked()) {
					temp.add(roomMap.get(k));
				}
			}
		}
		return temp;
	}
	
	@Override
	public Reservation makeRsv(Date rsvDate, char gender, int roomType,
						Guest rsvGuest, Party party, boolean eatBreakfast) throws NoRoomException { // C
		TreeSet<Room> tempRoom = new TreeSet<>();
		Reservation tempRsv = null;
		tempRoom = searchAvailableRoom(rsvDate, gender);
		for(Room r : tempRoom) { // gender 조건 만족하는 방 중에서
			if(r.getRoomType() == roomType) { // 방 타입(인원수)가 맞다면
				r.setBooked(r.getBooked()+1);
				tempRsv = new Reservation(rsvNum, rsvDate, rsvGuest, r, party, eatBreakfast);
				rsvMap.put(rsvNum, tempRsv);
				break;
			}
		}
		if(tempRsv == null)
			throw new NoRoomException("예약 가능한 방이 없습니다.");
		// attendFee 랑 eatBreakfast 인자값 받은 거 Party Map과 Breakfast Map에 넣기
		 if (party != null) {
		        partyMap.put(rsvNum, party.getAttendFee());
		    }
		if(eatBreakfast == true)
			breakfastMap.put(rsvNum, rsvDate);
		rsvNum++;
		return tempRsv;
	}
	
	@Override
	public void updateRsv(int rsvNum, Reservation rsv) { // U
		// 기존 예약 정보 확인
		Reservation oldRsv = rsvMap.get(rsvNum);

		if (oldRsv == null)
			return;// 예약이 없으면 무시

		Room oldRoom = oldRsv.getRsvRoom();
		Room newRoom = rsv.getRsvRoom();

		// 방이 바뀌었을때
		if (!oldRoom.getRoomNum().equals(newRoom.getRoomNum())) {
			//기존 방 인원 -1
			Room updated01Room = new Room(oldRoom.getRoomNum(), oldRoom.getRoomType(), oldRoom.getGender(),
					oldRoom.getPrice(), oldRoom.getBooked() - 1);

			roomMap.put(updated01Room.getRoomNum(), updated01Room);
			//새방 인원 +1
			Room realNewRoom = roomMap.get(newRoom.getRoomNum());
			Room updatedRoom = new Room(newRoom.getRoomNum(), newRoom.getRoomType(), newRoom.getGender(),
					newRoom.getPrice(), newRoom.getBooked() + 1);
			roomMap.put(updatedRoom.getRoomNum(), updatedRoom);
		}
		// breakMap 갱신
		if(rsvMap.get(rsvNum).getEatBreakfast() == false && rsv.getEatBreakfast() == true)
			breakfastMap.put(rsvNum, rsv.getRsvDate());
		else if(rsvMap.get(rsvNum).getEatBreakfast() == true && rsv.getEatBreakfast() == false)
			breakfastMap.remove(rsvNum);
		//rsvMap 갱신
		rsvMap.put(rsvNum,rsv);

		
		// 방 정보가 바꼈는지 먼저 확인하고 방 정보가 바꼈을 때만 roomMap 을 건드린다
		// roomMap 해시맵 정보도 수정해야 함
		// (방 예약인원 수정 / 원래 예약했던 방 인원 -1 새롭게 예약한 방 인원 +1)

		// rsvs 배열도 수정해야하고 (예약 수정)
	}
	
	@Override
	public void deleteRsv(int rsvNum) { // D
		if(rsvMap.containsKey(rsvNum)) {
			Room findRoom = rsvMap.get(rsvNum).getRsvRoom();
			findRoom.setBooked(findRoom.getBooked()-1);
			rsvMap.remove(rsvNum); // 예약 Map에서 해당 예약 삭제
			if(breakfastMap.containsKey(rsvNum))
				breakfastMap.remove(rsvNum);
			System.out.println("예약번호 "+rsvNum+"에 해당하는 예약 내역을 삭제하였습니다.");
		} else
			System.out.println("예약번호 "+rsvNum+"에 해당하는 예약 내역이 존재하지 않습니다.");
	}
	@Override
	public String searchRsv(int rsvNum) { // 특정 R
		
			System.out.println("예약 정본");
			for (Reservation r : rsvMap.values()) {

				if (r.getRsvNum() == rsvNum) {
					return r.toString();
				}
				
			}return "예약정보가 없습니다.";
		
	}
	
	@Override
	public ArrayList<Reservation> searchAllRsv(Date rsvDate) { // 전체 R
		ArrayList<Reservation> temp = new ArrayList<>();
		for(Reservation r : rsvMap.values()) {
			temp.add(r);
		}
		return temp;
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
	public Party[] searchParty(Date rsvDate) {
	    ArrayList<Party> parties = new ArrayList<>();
	    for (Reservation r : rsvMap.values()) {
	        if (r.getRsvDate().getYear() == rsvDate.getYear()
	                && r.getRsvDate().getMonth() == rsvDate.getMonth()
	                && r.getRsvDate().getDay() == rsvDate.getDay()) {
	            if (r.getParty() != null) { // 파티가 배정된 경우만
	                parties.add(r.getParty());
	            }
	        }
	    }
	    return parties.toArray(new Party[0]);
	}
	
	@Override
	public ArrayList<Guest> searchBreakfastGuest(Date rsvDate) throws NoBreakfastException { // Guest[] -> ArrayList<Guest> 로 수정
		ArrayList<Guest> mealList = new ArrayList<>();
		for(int i : breakfastMap.keySet()) { // 조식 목록을 돌면서
			Date tempDate = breakfastMap.get(i); // 조식 목록의 날짜를 임시로 담을 Date 변수
			// 연도 월 일이 같다면
			if(tempDate.getYear() == rsvDate.getYear() && tempDate.getMonth() == rsvDate.getMonth() && tempDate.getDay() == rsvDate.getDay()) {
				mealList.add(rsvMap.get(i).getRsvGuest()); // 게스트 목록에 추가
			}
		}
		if(mealList.size() == 0)
			throw new NoBreakfastException("해당일에 조식을 신청한 인원이 없습니다.");
		return mealList;
	}
	
	@Override
	public String searchRsvCondition(Date rsvDate) {
//		String s = null; -> 스트링을 했는데  안정적이지 않다.

		StringBuilder sb = new StringBuilder();
		for(Room r : roomMap.values()) {
			String room = r.getRoomNum();
			int booked = r.getBooked();
			int max = r.getRoomType();
			 sb.append(room).append(" ").append(booked).append("/").append(max).append("-");
		}
		
		return sb.toString();
	}
	
	@Override
	public void makeParty(Date rsvDate) {
	    ArrayList<Reservation> rsvList = new ArrayList<>();
	    for (Reservation r : rsvMap.values()) {
	        if (r.getRsvDate().getYear() == rsvDate.getYear()
	                && r.getRsvDate().getMonth() == rsvDate.getMonth()
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
	        if (fee == null) continue;
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
	    ArrayList<Reservation>[] partyGroups = new ArrayList[]{three, four, five};
	    for (ArrayList<Reservation> group : partyGroups) {
	        int idx = 0;
	        while (idx < group.size()) {
	            int remaining = group.size() - idx;
	            if (remaining >= 11 && remaining <= 13) {
	                // 11~13명은 반으로 나눈다
	                int mid = remaining / 2;
	                for (int i = 0; i < mid; i++) {
	                    group.get(idx + i).setParty(new Party(partyNumSeq, partyMap.get(group.get(idx + i).getRsvNum())));
	                }
	                partyNumSeq++;
	                for (int i = mid; i < remaining; i++) {
	                    group.get(idx + i).setParty(new Party(partyNumSeq, partyMap.get(group.get(idx + i).getRsvNum())));
	                }
	                partyNumSeq++;
	                idx += remaining;
	            } else if (remaining >= 4 && remaining <= 10) {
	                // 4명 이상 10명 이하
	                for (int i = 0; i < remaining; i++) {
	                    group.get(idx + i).setParty(new Party(partyNumSeq, partyMap.get(group.get(idx + i).getRsvNum())));
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
	                leftovers.get(idx + i).setParty(new Party(partyNumSeq, partyMap.get(leftovers.get(idx + i).getRsvNum())));
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
	public int makeBreakfast(Date rsvDate) throws NoBreakfastException {
		FoodShop fs = new FoodShop();
		HashMap<Integer, Integer> food = new HashMap<>();
		food.put(15, 3000);
		food.put(10, 3300);
		food.put(1, 4000);
		fs.setFoodMap(food);
		
		ArrayList<Guest> mealList = searchBreakfastGuest(rsvDate);
		int mealNum = mealList.size();
		int temp = mealNum;
		int total = 0;
//		System.out.println("조식 신청 인원은 "+mealNum+"명 입니다.");
		// 그리디 알고리즘
		Integer[] price = {15, 10, 1};
		for(int i : price) {
			if (temp>=i) {
				int j = temp/i;
				temp -= i*j;
				total += i*food.get(i)*j;
			}
		}
		return total/mealNum;
	}
	
	@Override
	public int searchAvgBreakfastPrice(int year, int month) throws NoBreakfastException {
		HashMap<Date, Integer> findBfPrice = new HashMap<>();
		int total = 0; // makeBreakfast 반환값을 더할 변수
		for(Date d : breakfastMap.values()) {
			if(d.getYear() == year && d.getMonth() == month) { // 찾으려는 연도, 월과 일치하는 날이라면
				if(!findBfPrice.containsKey(d))
					findBfPrice.put(d, makeBreakfast(d));
			}
		}
		for(int i : findBfPrice.values())
			total += i;
		return total/findBfPrice.size();
	}
}