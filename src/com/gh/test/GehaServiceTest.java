package com.gh.test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.gh.child.Guest;
import com.gh.exception.NoBreakfastException;
import com.gh.exception.NoRoomException;
import com.gh.rsv.FoodShop;
import com.gh.rsv.Reservation;
import com.gh.rsv.Room;
import com.gh.service.impl.GahaServiceImpl;
import com.gh.util.Date;

public class GehaServiceTest {

	public static void main(String[] args) {
		HashMap<String, Room> roomMap = new HashMap<String, Room>();
		// 남성 전용
		roomMap.put("M11", new Room("M11", 1, 'M', 60000, 0));
		roomMap.put("M12", new Room("M12", 1, 'M', 60000, 0));
		roomMap.put("M13", new Room("M13", 1, 'M', 60000, 0));
		roomMap.put("M21", new Room("M21", 2, 'M', 40000, 0));
		roomMap.put("M22", new Room("M22", 2, 'M', 40000, 0));
		roomMap.put("M23", new Room("M23", 2, 'M', 40000, 0));
		roomMap.put("M41", new Room("M41", 4, 'M', 25000, 0));
		roomMap.put("M42", new Room("M42", 4, 'M', 25000, 0));
		roomMap.put("M43", new Room("M43", 4, 'M', 25000, 0));
		roomMap.put("M44", new Room("M44", 4, 'M', 25000, 0));
		// 여성 전용
		roomMap.put("F11", new Room("F11", 1, 'F', 60000, 0));
		roomMap.put("F12", new Room("F12", 1, 'F', 60000, 0));
		roomMap.put("F13", new Room("F13", 1, 'F', 60000, 0));
		roomMap.put("F21", new Room("F21", 2, 'F', 40000, 0));
		roomMap.put("F22", new Room("F22", 2, 'F', 40000, 0));
		roomMap.put("F23", new Room("F23", 2, 'F', 40000, 0));
		roomMap.put("F41", new Room("F41", 4, 'F', 25000, 0));
		roomMap.put("F42", new Room("F42", 4, 'F', 25000, 0));
		roomMap.put("F43", new Room("F43", 4, 'F', 25000, 0));
		roomMap.put("F44", new Room("F44", 4, 'F', 25000, 0));
	
		Guest g1 = new Guest("이지은1", 'F', "010-1234-5678", new Date(1993,5,16));
		Guest g2 = new Guest("이지은2", 'F', "010-1234-5678", new Date(1993,5,16));
		Guest g3 = new Guest("이지은3", 'F', "010-1234-5678", new Date(1993,5,16));
		Guest g4 = new Guest("이지은4", 'F', "010-1234-5678", new Date(1993,5,16));
		
		GahaServiceImpl service = GahaServiceImpl.getInstance();
		service.setRoomMap(roomMap);
		
		try {
			Reservation r1 = service.makeRsv(new Date(2025,5,8), 'F', 1, g1, 0, true);
			System.out.println(g1.getName()+"님의 예약입니다 => "+r1);
		} catch (NoRoomException e) {
			System.out.println(e.getMessage());
		}
		try {
			Reservation r2 = service.makeRsv(new Date(2025,5,8), 'F', 1, g2, 0, true);
			System.out.println(g2.getName()+"님의 예약입니다 => "+r2);
		} catch (NoRoomException e) {
			System.out.println(e.getMessage());
		}
		try {
			Reservation r3 = service.makeRsv(new Date(2025,5,8), 'F', 1, g3, 0, true);
			System.out.println(g3.getName()+"님의 예약입니다 => "+r3);
		} catch (NoRoomException e) {
			System.out.println(e.getMessage());
		}
		try {
			Reservation r4 = service.makeRsv(new Date(2025,5,8), 'F', 1, g4, 0, true);
			System.out.println(g4.getName()+"님의 예약입니다 => "+r4);
		} catch (NoRoomException e) {
			System.out.println(e.getMessage());
		}

		service.deleteRsv(2);
		service.deleteRsv(7);
		
		System.out.println("=====전체 방 목록=====");
		ArrayList<String> keyList = new ArrayList<>(roomMap.keySet());
		Collections.sort(keyList, new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {
				return o1.compareTo(o2);
			}
		});
		Iterator<String> keyIter = keyList.iterator();
		while(keyIter.hasNext()) {
			String key = keyIter.next();
			System.out.println(roomMap.get(key));
		}
//		for(Room r : roomMap.values())
//			System.out.println(r);		
		
		System.out.println("=====예약 가능한 F 방 목록=====");
		System.out.println(service.searchAvailableRoom(new Date(2025,5,8), 'F'));
	
		System.out.println("===== 파티 구성 테스트 =====");
		service.makeParty(new Date(2025, 5, 8));
		
		try {
		    Room changedRoom = roomMap.get("F21"); // 2인실 방 하나 선택
		    Reservation newRsv = new Reservation(1, new Date(2025, 5, 8), g1, changedRoom, 0, false); // 예약번호 1번 수정
		    service.updateRsv(1, newRsv); // 번호 1번 예약자 g1의 방을 변경

		    System.out.println("업데이트 후 예약 정보:");
		    System.out.println(newRsv);

		    System.out.println("업데이트 후 방 상태:");
		    System.out.println("기존방(F11): " + roomMap.get("F11"));
		    System.out.println("신규방(F21): " + roomMap.get("F21"));
		} catch (Exception e) {
			
		    e.printStackTrace();
		}
		
		System.out.println("=====Date(2025,5,8) 에 조식 신청한 Guest 목록=====");
		ArrayList<Guest> bf250508 = new ArrayList<>();
		try {
			bf250508 = service.searchBreakfastGuest(new Date(2025,5,8));
			for(Guest g : bf250508)
				System.out.println(g);
		} catch (NoBreakfastException e) {
			System.out.println(e.getMessage());
		}
		
		System.out.println("=====Date(2025,5,9) 에 조식 신청한 Guest 목록=====");
		ArrayList<Guest> bf250509 = new ArrayList<>();
		try {
			bf250509 = service.searchBreakfastGuest(new Date(2025,5,9));
			for(Guest g : bf250509)
				System.out.println(g);
		} catch (NoBreakfastException e) {
			System.out.println(e.getMessage());
		}
		
		System.out.println("=====조식을 준비합니다.=====");
		try {
			System.out.println("인당 가격은 "+service.makeBreakfast(new Date(2025,5,8))+"입니다.");
		} catch (NoBreakfastException e) {
			System.out.println(e.getMessage());
		}
		
		System.out.println("=====Date(2025,5,8)의 모든 예약 목록=====");
		ArrayList<Reservation> allRsv = service.searchAllRsv(new Date(2025,5,8));
		for(Reservation r : allRsv)
			System.out.println(r);
		
		System.out.println("=====Date(2025,5,8)의 방 예약 현황=====");
		System.out.println(service.searchRsvCondition(new Date(2025,5,8)));
		System.out.println("=====예약 번호 1로 조회한 예약 정보=====");
		System.out.println(service.searchRsv(1));
		
	}
	
	

}
