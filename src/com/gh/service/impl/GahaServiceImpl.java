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
	private ArrayList<Reservation> rsvs; // 예약 정보를 담을 ArrayList
	private HashMap<String, Room> roomMap; // (중복 없는) 방 정보를 Key, 방 객체를 Value로 담은 HashMap
	private HashMap<Integer, Integer> partyMap = new HashMap<>(); // 예약 번호를 Key, 파티 참가비를 Value로 담은 HashMap
	private HashMap<Date, Integer> breakfastMap = new HashMap<>(); // 날짜 정보를 Key, 조식 신청 인원 수를 Value로 담은 HashMap
	public static final int NUMBER_OF_ROOM = 20; // 게스트하우스 전체 방 개수
	private int rsvNum = 1; // 예약 번호
	
	// 싱글톤
	static private GahaServiceImpl service = new GahaServiceImpl();
	public GahaServiceImpl() {
		super();
		rsvs = new ArrayList<>();		
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
				roomMap.replace(r.getRoomNum(), new Room(r.getRoomNum(), r.getRoomType(), r.getGender(), r.getPrice(), r.getBooked()+1));
				tempRsv = new Reservation(rsvNum, rsvDate, rsvGuest, r, attendFee, eatBreakfast);
				rsvs.add(tempRsv);
				break;
			}
		}
		if(tempRsv == null)
			throw new NoRoomException("예약 가능한 방이 없습니다.");
		// attendFee 랑 eatBreakfast 인자값 받은 거 Party 리스트랑 Breakfast 리스트에 넣기
		partyMap.put(rsvNum, attendFee);
		if(breakfastMap.containsKey(rsvDate))
			breakfastMap.put(rsvDate, breakfastMap.get(rsvDate)+1);
		else
			breakfastMap.put(rsvDate, 1);
		rsvNum++;
		return tempRsv;
	}
	
	@Override
	public void updateRsv(int rsvNum, Reservation rsv) { // U
		for(Reservation r : rsvs) {
//			if(r.getRsvNum() == rsvNum)
				
				// 방 정보가 바꼈는지 먼저 확인하고 방 정보가 바꼈을 때만 roomMap 을 건드린다
				// roomMap 해시맵 정보도 수정해야 함
				// (방 예약인원 수정 / 원래 예약했던 방 인원 -1 새롭게 예약한 방 인원 +1)
				
				// rsvs 배열도 수정해야하고 (예약 수정)
		}
		
	}
	@Override
	public void deleteRsv(int rsvNum) { // D
		// TODO Auto-generated method stub
		
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
	public Guest[] searchBreakfastGuest(Date resvDate) {
		// TODO Auto-generated method stub
		return null;
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
		// TODO Auto-generated method stub
		return 0;
	}
}