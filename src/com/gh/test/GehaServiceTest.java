package com.gh.test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import com.gh.child.Guest;
import com.gh.exception.NoBreakfastException;
import com.gh.exception.NoRoomException;
import com.gh.rsv.Breakfast;
import com.gh.rsv.Party;
import com.gh.rsv.Reservation;
import com.gh.rsv.Room;
import com.gh.service.impl.GahaServiceImpl;
import com.gh.util.Date;
import com.gh.util.FoodType;

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
			Reservation r1 = service.makeRsv(new Date(2025,5,8), 'F', 1, g1, null, new Breakfast(true));
			System.out.println(g1.getName()+"님의 예약입니다 => "+r1);
		} catch (NoRoomException e) {
			System.out.println(e.getMessage());
		}
		try {
			Reservation r2 = service.makeRsv(new Date(2025,5,8), 'F', 1, g2, null, new Breakfast(true));
			System.out.println(g2.getName()+"님의 예약입니다 => "+r2);
		} catch (NoRoomException e) {
			System.out.println(e.getMessage());
		}
		try {
			Reservation r3 = service.makeRsv(new Date(2025,5,8), 'F', 1, g3, null, new Breakfast(true));
			System.out.println(g3.getName()+"님의 예약입니다 => "+r3);
		} catch (NoRoomException e) {
			System.out.println(e.getMessage());
		}
		try {
			Reservation r4 = service.makeRsv(new Date(2025,5,8), 'F', 1, g4, null, new Breakfast(true));
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
		
		System.out.println("===========예약 가능한 F 방 목록=============");
		System.out.println();
		System.out.println("=====예약 가능한 F 방 목록=====");
		System.out.println(service.searchAvailableRoom(new Date(2025,5,8), 'F'));
		
		System.out.println();
		System.out.println("===== 파티 구성 테스트 =====");
		
		System.out.println();
		System.out.println("===== 예약번호 1번의 파티 정보 =====");
		service.getPartyMap().put(1, 30000);
		service.makeParty(new Date(2025, 5, 8));
		Party p = service.searchParty(1);
		if (p != null) {
		    System.out.println("partyNum: " + p.getPartyNum() + ", attendFee: " + p.getAttendFee());
		} else {
		    System.out.println("파티가 생성되지 않았습니다.");
		}
		System.out.println();
		System.out.println("===== 2025-05-08 날짜의 파티 목록 =====");
		Party[] partyList = service.searchParty(new Date(2025,5,8));
		for (Party party : partyList) {
		    System.out.println(party);
		}

		// 파티 구성 결과 확인
		ArrayList<Reservation> rsvAfterParty = service.searchAllRsv(new Date(2025,5,8));
		for (Reservation r : rsvAfterParty) {
		    System.out.print("예약번호: " + r.getRsvNum()
		                       + ", 이름: " + r.getRsvGuest().getName());
		    if (r.getParty() != null) {
		        System.out.println(", 파티번호: " + r.getParty().getPartyNum());
		    } else {
		        System.out.println(", 파티번호: 없음");
		    }
		}
		
		try {
		    Room changedRoom = roomMap.get("F21"); // 2인실 방 하나 선택
		    Reservation newRsv = new Reservation(1, new Date(2025, 5, 8), g1, changedRoom, null, new Breakfast(false)); // 예약번호 1번 수정
		    service.updateRsv(1, newRsv); // 번호 1번 예약자 g1의 방을 변경

		    System.out.println();
		    System.out.println("==============업데이트 후 방 상태===============");
			System.out.println();
		    System.out.println("업데이트 후 예약 정보:");
		    System.out.println(newRsv);
			System.out.println();
		    System.out.println("업데이트 후 방 상태:");
		    System.out.println("기존방(F11): " + roomMap.get("F11"));
		    System.out.println("신규방(F21): " + roomMap.get("F21"));
		} catch (Exception e) {
		    e.getMessage();
		}
		System.out.println();
		System.out.println("=====Date(2025,5,8) 에 조식 신청한 Guest 목록=====");
		ArrayList<Guest> bf250508 = new ArrayList<>();
		try {
			bf250508 = service.searchBreakfastGuest(new Date(2025,5,8));
			for(Guest g : bf250508)
				System.out.println(g);
		} catch (NoBreakfastException e) {
			System.out.println(e.getMessage());
		}
		System.out.println();
		System.out.println("=====Date(2025,5,9) 에 조식 신청한 Guest 목록=====");
		ArrayList<Guest> bf250509 = new ArrayList<>();
		try {
			bf250509 = service.searchBreakfastGuest(new Date(2025,5,9));
			for(Guest g : bf250509)
				System.out.println(g);
		} catch (NoBreakfastException e) {
			System.out.println(e.getMessage());
		}
		System.out.println();
		System.out.println("=====조식을 준비합니다.=====");
		try {
			System.out.println("조식 타입은 "+FoodType.Korean.getValue()+", "+"인당 가격은 "+service.makeBreakfast(new Date(2025,5,8))+"입니다.");
		} catch (NoBreakfastException e) {
			System.out.println(e.getMessage());
		}
		System.out.println("=====2025년 5월의 조식 가격 평균=====");
		try {
			System.out.println(service.searchAvgBreakfastPrice(2025, 5));
		} catch (NoBreakfastException e) {
			System.out.println(e.getMessage());
		}
		System.out.println();
		System.out.println("=====Date(2025,5,8)의 모든 예약 목록=====");
		ArrayList<Reservation> allRsv = service.searchAllRsv(new Date(2025,5,8));
		for(Reservation r : allRsv)
			System.out.println(r);
		System.out.println();
		System.out.println("=====Date(2025,5,8)의 방 예약 현황=====");
		System.out.println(service.searchRsvCondition(new Date(2025,5,8)));

		String[] rsvCon = service.searchRsvCondition(new Date(2025,5,8)).split("-");
		ArrayList<String> rsvConList = new ArrayList<>();
		for(int i=0; i<rsvCon.length; i++)
			rsvConList.add(rsvCon[i]);
		Collections.sort(rsvConList, new Comparator<String>() {

			@Override
			public int compare(String o1, String o2) {
				return o1.compareTo(o2);
			}
			
		});
	
			
		try(Scanner sc = new Scanner(System.in)){
			while (true) {
				System.out.println("\n=========게하 예약 시스템 ========");
				System.out.println("1.예약하기 \n2.전체 방 보기 \n3.조식 가격 보기  \n4.파티 구성 보기 \n5.종료");
				int menu = sc.nextInt();
				sc.nextLine();//버퍼 문제 생겨서 같이 물어보게 됨
				
				switch (menu) {
				case 1: 
						System.out.println("이름:");
						String name = sc.nextLine();
						
						System.out.println("대표자 해드폰 번호:(-넣어주세요)");
						String phnum = sc.nextLine();
						
						System.out.println("성별 (M/F):");
						char gender = sc.nextLine().toUpperCase().charAt(0);
						
						System.out.println("인원수 (1~4):");
						int person = sc.nextInt();
						
						System.out.println("조식 포함(True/False):");
						boolean braekfast = sc.nextBoolean();
						
						System.out.println("파티 참여(True/False):");
						Boolean joinParty = sc.nextBoolean();
						
						Guest guset = new Guest(name,gender,phnum,new Date(1998,2,22));
						Party party = joinParty ? new Party(0,30000) : new Party(0,0); 
						
						try {
							Reservation rsv = service.makeRsv(new Date(2025,5,13), gender, person, guset, party, new Breakfast(braekfast));
							System.out.println("에약이 완료되었습니다!^^"+rsv);
						}catch (NoRoomException e) {
						System.out.println("예약 실패되었습니다."+ e.getMessage());
						}
						break;
						
				case 2://정렬해서 전체방 목록 보기
						System.out.println("\n 전체 방 목록:");
						List<String> keyList1 = new ArrayList<>(roomMap.keySet());
						Collections.sort(keyList1);
						for(String k : keyList1) {
							System.out.println(roomMap.get(k));
						}
						break;
						
				case 3:
						System.out.println("\n======== 2025년 5월 조식 가격 평균========");
						try {
							int avg = service.searchAvgBreakfastPrice(2025, 5);
							System.out.println("조식타입:"+FoodType.Korean.getValue());
							System.out.println("평균 조식 가격:"+ avg);
							
						}catch(Exception e) {
							System.out.println("조식 정보가 없습니다."+e.getMessage());
						}
						break;
				case 4:
					System.out.println("\n =========2025-5-13 파티 목록==========");
					try {
					    Party[] parties = service.searchParty(new Date(2025, 5, 13));
					    if (parties.length == 0) {
					        System.out.println("등록된 파티가 없습니다.");
					    } else {
					        for (Party p1 : parties) {
					            System.out.println(p1);
					        }
					    }
					} catch (Exception e) {
					    System.out.println("파티 정보를 가져오는 중 오류: " + e.getMessage());
					}
						break;
						
					
				case 5:
					System.out.println("프로그램 종료합니다!! bbb");
					return;		
					
				default:
					System.out.println("잘못된 입력입니다. 다시 시도해 주세요!");
				}
			
				
			}
		}
		
	}
}