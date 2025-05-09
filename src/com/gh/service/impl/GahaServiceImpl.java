package com.gh.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import com.gh.child.Guest;
import com.gh.exception.NoRoomException;
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
						Guest rsvGuest, int attendFee, boolean eatBreakfast) throws NoRoomException { // C
		TreeSet<Room> tempRoom = new TreeSet<>();
		Reservation tempRsv = null;
		tempRoom = searchAvailableRoom(rsvDate, gender);
		for(Room r : tempRoom) { // gender 조건 만족하는 방 중에서
			if(r.getRoomType() == roomType) { // 방 타입(인원수)가 맞다면
				r.setBooked(r.getBooked()+1);
				tempRsv = new Reservation(rsvNum, rsvDate, rsvGuest, r, attendFee, eatBreakfast);
				rsvMap.put(rsvNum, tempRsv);
				break;
			}
		}
		if(tempRsv == null)
			throw new NoRoomException("예약 가능한 방이 없습니다.");
		// attendFee 랑 eatBreakfast 인자값 받은 거 Party Map과 Breakfast Map에 넣기
		partyMap.put(rsvNum, attendFee);
		if(eatBreakfast == true)
			breakfastMap.put(rsvNum, rsvDate);
		rsvNum++;
		return tempRsv;
	}
	
	@Override
	public void updateRsv(int rsvNum, Reservation rsv) { // U
				
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
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Reservation[] searchAllRsv(Date rsvDate) { // 전체 r
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Party searchParty(int rsvNum) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Party[] searchParty(Date rsvDate) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public ArrayList<Guest> searchBreakfastGuest(Date rsvDate) { // Guest[] -> ArrayList<Guest> 로 수정
		ArrayList<Guest> mealList = new ArrayList<>();
		for(int i : breakfastMap.keySet()) { // 조식 목록을 돌면서
			Date tempDate = breakfastMap.get(i); // 조식 목록의 날짜를 임시로 담을 Date 변수
			// 연도 월 일이 같다면
			if(tempDate.getYear() == rsvDate.getYear() && tempDate.getMonth() == rsvDate.getMonth() && tempDate.getDay() == rsvDate.getDay()) {
				mealList.add(rsvMap.get(i).getRsvGuest()); // 게스트 목록에 추가
			}
		}
		return mealList;
	}
	
	@Override
	public String searchRsvCondition(Date rsvDate) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public int searchAvgBreakfastPrice(int year, int month) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public void makeParty(Date rsvDate) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public int makeBreakfast(Date rsvDate) {
		ArrayList<Guest> mealList = searchBreakfastGuest(rsvDate);
		int mealNum = mealList.size();
		// 그리디 알고리즘
		
		return 0;
	}
}