package com.gh.test;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Scanner;

import com.gh.child.Guest;
import com.gh.exception.NoBreakfastException;
import com.gh.exception.NoReservationException;
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
		// 방 HashMap 생성
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
		
		GahaServiceImpl service = GahaServiceImpl.getInstance();
		service.setRoomMap(roomMap);
		
		// 게스트 객체 생성
		Guest g1 = new Guest("이지은", 'F', "010-1234-5678", new Date(1993,5,16));
		Guest g2 = new Guest("이수빈", 'F', "010-2462-4518", new Date(1994, 9, 6));
		Guest g3 = new Guest("정하준", 'M', "010-3158-9294", new Date(1983, 7, 9));
		Guest g4 = new Guest("윤지호", 'M', "010-7291-2803", new Date(1979, 10, 24));
		Guest g5 = new Guest("한서윤", 'F', "010-1236-6932", new Date(1978, 1, 7));
		Guest g6 = new Guest("박예진", 'F', "010-9721-4187", new Date(1991, 3, 18));
		Guest g7 = new Guest("최민수", 'M', "010-6482-7139", new Date(1980, 8, 13));
		Guest g8 = new Guest("장서은", 'F', "010-5731-8047", new Date(1992, 11, 20));
		Guest g9 = new Guest("김현우", 'M', "010-3609-1952", new Date(1986, 5, 5));
		Guest g10 = new Guest("임지호", 'M', "010-4523-6053", new Date(1988, 4, 11));
		Guest g11 = new Guest("조유진", 'F', "010-7814-3140", new Date(1973, 0, 16));
		Guest g12 = new Guest("이하준", 'M', "010-9248-7877", new Date(1995, 2, 21));
		Guest g13 = new Guest("윤서윤", 'F', "010-6342-8943", new Date(1996, 6, 26));
		Guest g14 = new Guest("정민수", 'M', "010-2073-5920", new Date(1993, 9, 9));
		Guest g15 = new Guest("한예린", 'F', "010-8195-4689", new Date(1981, 10, 2));
		Guest g16 = new Guest("조수빈", 'F', "010-9136-8427", new Date(1989, 11, 29));
		Guest g17 = new Guest("임지은", 'F', "010-3145-7591", new Date(1985, 5, 8));
		Guest g18 = new Guest("박도윤", 'M', "010-5071-1036", new Date(1977, 7, 4));
		Guest g19 = new Guest("김지훈", 'M', "010-8374-9823", new Date(1971, 4, 15));
		Guest g20 = new Guest("장지은", 'F', "010-7510-2946", new Date(1984, 1, 18));
		Guest g21 = new Guest("최유진", 'F', "010-6982-6845", new Date(1990, 2, 7));
		Guest g22 = new Guest("윤하진", 'M', "010-9164-3902", new Date(1987, 9, 10));
		Guest g23 = new Guest("이호준", 'M', "010-2714-4729", new Date(1994, 8, 25));
		Guest g24 = new Guest("조서윤", 'F', "010-6239-1734", new Date(1975, 10, 19));
		Guest g25 = new Guest("김예린", 'F', "010-1807-6086", new Date(1991, 5, 14));
		Guest g26 = new Guest("임민서", 'M', "010-3957-4021", new Date(1974, 3, 6));
		Guest g27 = new Guest("이현우", 'M', "010-5876-8512", new Date(1983, 6, 17));
		Guest g28 = new Guest("정지호", 'M', "010-7865-9410", new Date(1982, 11, 28));
		Guest g29 = new Guest("장수빈", 'F', "010-3256-3953", new Date(1972, 0, 12));
		Guest g30 = new Guest("최유정", 'F', "010-1467-6825", new Date(1998, 8, 23));
		Guest g31 = new Guest("박하린", 'F', "010-9568-2649", new Date(1980, 1, 2));
		
		// 예약 객체 생성
		try {
		    Reservation r1 = service.makeRsv(new Date(2025,5,8), g1.getGender(), 1, g1, new Party(-1,30000), new Breakfast(true));
		    System.out.println(g1.getName() + "님의 예약입니다 => " + r1);
		} catch (NoRoomException e) {
		    System.out.println(e.getMessage());
		}
		try {
		    Reservation r2 = service.makeRsv(new Date(2025,5,8), g2.getGender(), 1, g2, new Party(-1,30000), new Breakfast(true));
		    System.out.println(g2.getName() + "님의 예약입니다 => " + r2);
		} catch (NoRoomException e) {
		    System.out.println(e.getMessage());
		}
		try {
		    Reservation r3 = service.makeRsv(new Date(2025,5,8), g3.getGender(), 1, g3, new Party(-1,30000), new Breakfast(true));
		    System.out.println(g3.getName() + "님의 예약입니다 => " + r3);
		} catch (NoRoomException e) {
		    System.out.println(e.getMessage());
		}
		try {
		    Reservation r4 = service.makeRsv(new Date(2025,5,8), g4.getGender(), 1, g4, new Party(-1,30000), new Breakfast(true));
		    System.out.println(g4.getName() + "님의 예약입니다 => " + r4);
		} catch (NoRoomException e) {
		    System.out.println(e.getMessage());
		}
		try {
		    Reservation r5 = service.makeRsv(new Date(2025,5,8), g5.getGender(), 1, g5, new Party(-1,40000), new Breakfast(true));
		    System.out.println(g5.getName() + "님의 예약입니다 => " + r5);
		} catch (NoRoomException e) {
		    System.out.println(e.getMessage());
		}
		try {
		    Reservation r6 = service.makeRsv(new Date(2025,5,8), g6.getGender(), 2, g6, new Party(-1,40000), new Breakfast(true));
		    System.out.println(g6.getName() + "님의 예약입니다 => " + r6);
		} catch (NoRoomException e) {
		    System.out.println(e.getMessage());
		}
		try {
		    Reservation r7 = service.makeRsv(new Date(2025,5,8), g7.getGender(), 1, g7, new Party(-1,40000), new Breakfast(true));
		    System.out.println(g7.getName() + "님의 예약입니다 => " + r7);
		} catch (NoRoomException e) {
		    System.out.println(e.getMessage());
		}
		try {
		    Reservation r8 = service.makeRsv(new Date(2025,5,8), g8.getGender(), 2, g8, new Party(-1,40000), new Breakfast(true));
		    System.out.println(g8.getName() + "님의 예약입니다 => " + r8);
		} catch (NoRoomException e) {
		    System.out.println(e.getMessage());
		}
		try {
		    Reservation r9 = service.makeRsv(new Date(2025,5,8), g9.getGender(), 4, g9, new Party(-1,40000), new Breakfast(true));
		    System.out.println(g9.getName() + "님의 예약입니다 => " + r9);
		} catch (NoRoomException e) {
		    System.out.println(e.getMessage());
		}
		try {
		    Reservation r10 = service.makeRsv(new Date(2025,5,8), g10.getGender(), 2, g10, new Party(-1,40000), new Breakfast(true));
		    System.out.println(g10.getName() + "님의 예약입니다 => " + r10);
		} catch (NoRoomException e) {
		    System.out.println(e.getMessage());
		}
		try {
		    Reservation r11 = service.makeRsv(new Date(2025,5,8), g11.getGender(), 2, g11, new Party(-1,40000), new Breakfast(true));
		    System.out.println(g11.getName() + "님의 예약입니다 => " + r11);
		} catch (NoRoomException e) {
		    System.out.println(e.getMessage());
		}
		try {
		    Reservation r12 = service.makeRsv(new Date(2025,5,8), g12.getGender(), 2, g12, new Party(-1,40000), new Breakfast(true));
		    System.out.println(g12.getName() + "님의 예약입니다 => " + r12);
		} catch (NoRoomException e) {
		    System.out.println(e.getMessage());
		}
		try {
		    Reservation r13 = service.makeRsv(new Date(2025,5,8), g13.getGender(), 4, g13, new Party(-1,50000), new Breakfast(true));
		    System.out.println(g13.getName() + "님의 예약입니다 => " + r13);
		} catch (NoRoomException e) {
		    System.out.println(e.getMessage());
		}
		try {
		    Reservation r14 = service.makeRsv(new Date(2025,5,8), g14.getGender(), 4, g14, new Party(-1,50000), new Breakfast(true));
		    System.out.println(g14.getName() + "님의 예약입니다 => " + r14);
		} catch (NoRoomException e) {
		    System.out.println(e.getMessage());
		}
		try {
		    Reservation r15 = service.makeRsv(new Date(2025,5,8), g15.getGender(), 4, g15, new Party(-1,50000), new Breakfast(true));
		    System.out.println(g15.getName() + "님의 예약입니다 => " + r15);
		} catch (NoRoomException e) {
		    System.out.println(e.getMessage());
		}
		try {
		    Reservation r16 = service.makeRsv(new Date(2025,5,8), g16.getGender(), 4, g16, null, new Breakfast(true));
		    System.out.println(g16.getName() + "님의 예약입니다 => " + r16);
		} catch (NoRoomException e) {
		    System.out.println(e.getMessage());
		}
		try {
		    Reservation r17 = service.makeRsv(new Date(2025,5,8), g17.getGender(), 2, g17, null, new Breakfast(true));
		    System.out.println(g17.getName() + "님의 예약입니다 => " + r17);
		} catch (NoRoomException e) {
		    System.out.println(e.getMessage());
		}
		try {
		    Reservation r18 = service.makeRsv(new Date(2025,5,8), g18.getGender(), 2, g18, null, new Breakfast(true));
		    System.out.println(g18.getName() + "님의 예약입니다 => " + r18);
		} catch (NoRoomException e) {
		    System.out.println(e.getMessage());
		}
		try {
		    Reservation r19 = service.makeRsv(new Date(2025,5,8), g19.getGender(), 2, g19, null, new Breakfast(true));
		    System.out.println(g19.getName() + "님의 예약입니다 => " + r19);
		} catch (NoRoomException e) {
		    System.out.println(e.getMessage());
		}
		try {
		    Reservation r20 = service.makeRsv(new Date(2025,5,8), g20.getGender(), 4, g20, null, new Breakfast(true));
		    System.out.println(g20.getName() + "님의 예약입니다 => " + r20);
		} catch (NoRoomException e) {
		    System.out.println(e.getMessage());
		}
		try {
		    Reservation r21 = service.makeRsv(new Date(2025,5,8), g21.getGender(), 4, g21, null, new Breakfast(true));
		    System.out.println(g21.getName() + "님의 예약입니다 => " + r21);
		} catch (NoRoomException e) {
		    System.out.println(e.getMessage());
		}
		try {
		    Reservation r22 = service.makeRsv(new Date(2025,5,8), g22.getGender(), 2, g22, null, new Breakfast(true));
		    System.out.println(g22.getName() + "님의 예약입니다 => " + r22);
		} catch (NoRoomException e) {
		    System.out.println(e.getMessage());
		}
		try {
		    Reservation r23 = service.makeRsv(new Date(2025,5,8), g23.getGender(), 2, g23, null, new Breakfast(true));
		    System.out.println(g23.getName() + "님의 예약입니다 => " + r23);
		} catch (NoRoomException e) {
		    System.out.println(e.getMessage());
		}
		try {
		    Reservation r24 = service.makeRsv(new Date(2025,5,8), g24.getGender(), 4, g24, null, new Breakfast(true));
		    System.out.println(g24.getName() + "님의 예약입니다 => " + r24);
		} catch (NoRoomException e) {
		    System.out.println(e.getMessage());
		}
		try {
		    Reservation r25 = service.makeRsv(new Date(2025,5,8), g25.getGender(), 4, g25, null, new Breakfast(true));
		    System.out.println(g25.getName() + "님의 예약입니다 => " + r25);
		} catch (NoRoomException e) {
		    System.out.println(e.getMessage());
		}
		try {
		    Reservation r26 = service.makeRsv(new Date(2025,5,8), g26.getGender(), 4, g26, null, new Breakfast(true));
		    System.out.println(g26.getName() + "님의 예약입니다 => " + r26);
		} catch (NoRoomException e) {
		    System.out.println(e.getMessage());
		}
		try {
		    Reservation r27 = service.makeRsv(new Date(2025,5,8), g27.getGender(), 4, g27, null, new Breakfast(true));
		    System.out.println(g27.getName() + "님의 예약입니다 => " + r27);
		} catch (NoRoomException e) {
		    System.out.println(e.getMessage());
		}
		try {
		    Reservation r28 = service.makeRsv(new Date(2025,5,8), g28.getGender(), 4, g28, null, new Breakfast(true));
		    System.out.println(g28.getName() + "님의 예약입니다 => " + r28);
		} catch (NoRoomException e) {
		    System.out.println(e.getMessage());
		}
		try {
		    Reservation r29 = service.makeRsv(new Date(2025,5,8), g29.getGender(), 4, g29, null, new Breakfast(true));
		    System.out.println(g29.getName() + "님의 예약입니다 => " + r29);
		} catch (NoRoomException e) {
		    System.out.println(e.getMessage());
		}
		try {
		    Reservation r30 = service.makeRsv(new Date(2025,5,8), g30.getGender(), 4, g30, null, new Breakfast(true));
		    System.out.println(g30.getName() + "님의 예약입니다 => " + r30);
		} catch (NoRoomException e) {
		    System.out.println(e.getMessage());
		}
		try {
		    Reservation r31 = service.makeRsv(new Date(2025,5,8), g31.getGender(), 4, g31, null, new Breakfast(true));
		    System.out.println(g31.getName() + "님의 예약입니다 => " + r31);
		} catch (NoRoomException e) {
		    System.out.println(e.getMessage());
		}
		
		// case 10~12를 위한 breakfastMap 데이터 만들기
		HashMap<Integer, Date> breakfastMap = service.getbreakfastMap();
		breakfastMap.put(101, new Date(2025, 5, 1));
		breakfastMap.put(102, new Date(2025, 5, 1));
		breakfastMap.put(103, new Date(2025, 5, 1));
		breakfastMap.put(104, new Date(2025, 5, 1));
		breakfastMap.put(105, new Date(2025, 5, 1));
		breakfastMap.put(106, new Date(2025, 5, 1));
		breakfastMap.put(107, new Date(2025, 5, 1));
		breakfastMap.put(108, new Date(2025, 5, 1));
		breakfastMap.put(109, new Date(2025, 5, 1));
		breakfastMap.put(110, new Date(2025, 5, 1));
		breakfastMap.put(111, new Date(2025, 5, 1));
		breakfastMap.put(112, new Date(2025, 5, 1));
		breakfastMap.put(113, new Date(2025, 5, 1));
		breakfastMap.put(114, new Date(2025, 5, 1));
		breakfastMap.put(115, new Date(2025, 5, 1));
		breakfastMap.put(116, new Date(2025, 5, 1));
		breakfastMap.put(117, new Date(2025, 5, 1));
		breakfastMap.put(118, new Date(2025, 5, 1));
		breakfastMap.put(119, new Date(2025, 5, 1));
		breakfastMap.put(120, new Date(2025, 5, 1));
		service.setbreakfastMap(breakfastMap);
		
		try(Scanner sc = new Scanner(System.in)){
			while (true) {
				System.out.println("\n========= 게스트하우스 예약 시스템 =========");
				System.out.print("0. 게스트하우스 전체 방 목록 조회하기\n1. 예약 가능한 방 조회하기\n2. 예약하기\n3. 내 예약 조회하기\n4. 예약 수정하기\n5. 예약 취소하기\n");
				System.out.print("6. 날짜별 전체 예약 조회하기\n7. 날짜별 방 예약 현황 조회하기\n");
				System.out.print("8. 내 파티 조회하기\n9. 날짜별 파티 조회하기\n");
				System.out.print("10. 조식 신청한 게스트 조회하기\n11. 날짜별 조식 가격 조회하기\n12. 월별 조식 가격 평균 조회하기\n13. 서비스 종료\n");
				System.out.print("원하는 메뉴 번호를 입력하세요 => ");
				int menu = sc.nextInt();
				sc.nextLine();//버퍼 문제 생겨서 같이 물어보게 됨
				
				switch (menu) {
					case 0: // 0. 게스트하우스 전체 방 목록 조회하기
						System.out.println("=====전체 방 목록=====");
						List<String> keyList = new ArrayList<>(roomMap.keySet());
						Collections.sort(keyList);
						for(String k : keyList)
							System.out.println(roomMap.get(k));
						break;
					case 1: // 1. 예약 가능한 방 조회하기
						// Date(2025,5,8) 조회
						System.out.println("조회할 날짜의 연도를 입력하세요 => ");
						int year1 = sc.nextInt();
						System.out.println("조회할 날짜의 월을 입력하세요 => ");
						int month1 = sc.nextInt();
						System.out.println("조회할 날짜의 일을 입력하세요 => ");
						int day1 = sc.nextInt();
						System.out.println(year1+"/"+month1+"/"+day1+"의 예약 가능한 방 목록입니다.");
						System.out.println("=====예약 가능한 여자 방 목록=====");
						try {
							System.out.println(service.searchAvailableRoom(new Date(2025,5,8), 'F'));
						} catch (NoRoomException e) {
							System.out.println(e.getMessage());
						}
						System.out.println("=====예약 가능한 남자 방 목록=====");
						try {
							System.out.println(service.searchAvailableRoom(new Date(2025,5,8), 'M'));
						} catch (NoRoomException e) {
							System.out.println(e.getMessage());
						}
						break;
					case 2: // 2. 예약하기
						System.out.println("이름 : ");
						String name = sc.nextLine();
						System.out.println("핸드폰 번호(- 포함해서 작성해주세요) : ");
						String phnum = sc.nextLine();
						System.out.println("성별 (M/F) : ");
						char gender = sc.nextLine().toUpperCase().charAt(0);
						System.out.println("신청할 방 타입 (1/2/4 중 택 1) : ");
						int person = sc.nextInt();
						System.out.println("조식 포함(True/False) : ");
						boolean braekfast = sc.nextBoolean();
						System.out.println("파티 참여 여부(0(참여 안 함)/3/4/5) : ");
						int attendFee = sc.nextInt();
						Guest guest = new Guest(name,gender,phnum,new Date(1998,2,22));
						Party party = attendFee!=0 ? new Party(-1,attendFee) : null; 
						
						try {
							Reservation rsv = service.makeRsv(new Date(2025,5,8), gender, person, guest, party, new Breakfast(braekfast));
							System.out.println(name+"님 예약이 완료되었습니다!^^\n"+rsv);
							System.out.println(name+"님의 예약 번호는 "+rsv.getRsvNum()+"입니다.");
						}catch (NoRoomException e) {
							System.out.println(e.getMessage());
							
						}
						break;
					case 3: // 3. 내 예약 조회하기
						System.out.println("예약 번호를 입력하세요 => ");
						int rsvNum3 = sc.nextInt();
						System.out.println(service.searchRsv(rsvNum3));
						break;
					case 4: // 4. 예약 수정하기
						System.out.println("수정할 예약 번호를 입력하세요 => ");
						int rsvNum4 = sc.nextInt();
						System.out.println("수정하고 싶은 정보를 수정해주세요");
						sc.nextLine();
						System.out.println("이름 : ");
						String reName = sc.nextLine();
						System.out.println("핸드폰 번호(- 포함해서 작성해주세요) : ");
						String rePhnum = sc.nextLine();
						System.out.println("성별 (M/F) : ");
						char reGender = sc.nextLine().toUpperCase().charAt(0);
						System.out.println("신청할 방 타입 (1/2/4 중 택 1) : ");
						int rePerson = sc.nextInt();
						System.out.println("조식 포함(True/False) : ");
						boolean reBraekfast = sc.nextBoolean();
						System.out.println("파티 참여 여부(0(참여 안 함)/3/4/5) : ");
						int reattendFee = sc.nextInt();
						Guest reGuest = new Guest(reName,reGender,rePhnum,new Date(2025,5,8));
						Party reParty = reattendFee!=0 ? new Party(-1,reattendFee) : null;
						Breakfast reBf = new Breakfast(reBraekfast);
						Room newRoom = null;
						for(Room r : service.getRoomMap().values()) {
							if(r.getGender() == reGender && r.getRoomType() == rePerson && r.getBooked() < r.getRoomType()) {
								newRoom = r;
								break;
							}
						}
						if(newRoom != null) {
							try {
								  service.updateRsv(rsvNum4, new Reservation(rsvNum4, new Date(2025,5,8), reGuest, newRoom, reParty, reBf)); // 번호 1번 예약자 g1의 방을 변경
								  System.out.println("수정이 완료되었습니다!^^ " + service.getRsvMap().get(rsvNum4));
								} catch (NoReservationException e) {
									System.out.println("수정이 실패되었습니다. " + e.getMessage());
								}
						} else {
							System.out.println("수정할 수 없습니다.");
						}
						break;
					case 5: // 5. 예약 취소하기
						System.out.println("삭제할 예약 번호를 입력하세요 => ");
						int rsvNum5 = sc.nextInt();
						try {
							service.deleteRsv(rsvNum5);
						} catch (NoReservationException e) {
							System.out.println(e.getMessage());
						}
						break;
					case 6: // 6. 날짜별 전체 예약 조회하기
						// Date(2025,5,8) 조회
						System.out.println("조회할 날짜의 연도를 입력하세요 => ");
						int year6 = sc.nextInt();
						System.out.println("조회할 날짜의 월을 입력하세요 => ");
						int month6 = sc.nextInt();
						System.out.println("조회할 날짜의 일을 입력하세요 => ");
						int day6 = sc.nextInt();
						Date queryDate = new Date(year6, month6, day6);
					    System.out.println(queryDate + "의 모든 예약 목록입니다:");
						ArrayList<Reservation> allRsv = service.searchAllRsv(queryDate);
						
						try (BufferedWriter writer = new BufferedWriter(new FileWriter("reservation_output.txt"))) {
					        if (allRsv == null || allRsv.isEmpty()) {
					            System.out.println("예약이 없습니다.");
					            writer.write("예약이 없습니다.");
					            writer.newLine();
					        } else {
					            for (Reservation r : allRsv) {
					                System.out.println(r.toString());    // 콘솔 출력
					                writer.write(r.toString());          // 파일 저장
					                writer.newLine();            // 줄바꿈
					            }
					        }
					        System.out.println("파일 저장 완료: reservation_output.txt");
					    } catch (IOException e) {
					        System.out.println("파일 저장 중 오류 발생: " + e.getMessage());
					    }
						break;
					case 7: // 7. 날짜별 방 예약 현황 조회하기
						// Date(2025,5,8) 조회
						System.out.println("조회할 날짜의 연도를 입력하세요 => ");
						int year7 = sc.nextInt();
						System.out.println("조회할 날짜의 월을 입력하세요 => ");
						int month7 = sc.nextInt();
						System.out.println("조회할 날짜의 일을 입력하세요 => ");
						int day7 = sc.nextInt();
						System.out.println(year7+"/"+month7+"/"+day7+"에 예약된 방 현황입니다.");
						String[] rsvCon = service.searchRsvCondition(new Date(year7,month7,day7)).split("-");
						ArrayList<String> rsvConList = new ArrayList<>();
						for(int i=0; i<rsvCon.length; i++)
							rsvConList.add(rsvCon[i]);
						Collections.sort(rsvConList, new Comparator<String>() {
							@Override
							public int compare(String o1, String o2) {
								return o1.compareTo(o2);
							}
						});
						for(String s : rsvConList)
							System.out.println(s);
						break;
					case 8: // 8. 내 파티 조회하기
						System.out.println("파티 정보를 조회할 예약 번호를 입력하세요 => ");
						int rsvNum9 = sc.nextInt();
						service.makeParty(new Date(2025, 5, 8));
						Party p = service.searchParty(rsvNum9);
						if (p != null) {
							System.out.println("해당하는 파티 정보입니다.");
						    System.out.println("partyNum: " + p.getPartyNum() + ", attendFee: " + p.getAttendFee());
						} else {
						    System.out.println("파티가 생성되지 않았습니다.");
						}
						break;
					case 9: // 9. 날짜별 파티 조회하기
						// Date(2025,5,8) 조회
						System.out.println("조회할 날짜의 연도를 입력하세요 => ");
						int year9 = sc.nextInt();
						System.out.println("조회할 날짜의 월을 입력하세요 => ");
						int month9 = sc.nextInt();
						System.out.println("조회할 날짜의 일을 입력하세요 => ");
						int day9 = sc.nextInt();
						System.out.println(year9+"/"+month9+"/"+day9+"의 파티 목록입니다.");
						service.makeParty(new Date(year9, month9, day9));
						List<Party> parties = new ArrayList<Party>();
								parties = service.searchParty(new Date(year9, month9, day9));
					    Optional<Party> optional9 = parties.stream()
					    									.filter(Objects::nonNull)
					    									.findFirst();
						if (optional9.isPresent()) {
					        parties.stream()
					        .filter(Objects::nonNull)
					        .sorted((o1,o2)->o1.getPartyNum()-o2.getPartyNum())
					        .forEach(System.out::println);
					    } else {
					            System.out.println(year9+"/"+month9+"/"+day9+"에는 파티가 없습니다.");
					    }
						break;
//						// 파티 구성 결과 확인
//						ArrayList<Reservation> rsvAfterParty = service.searchAllRsv(new Date(2025,5,8));
//						for (Reservation r : rsvAfterParty) {
//						    System.out.print("예약번호: " + r.getRsvNum()
//						                       + ", 이름: " + r.getRsvGuest().getName());
//						    if (r.getParty() != null) {
//						        System.out.println(", 파티번호: " + r.getParty().getPartyNum());
//						    } else {
//						        System.out.println(", 파티번호: 없음");
//						    }
//						}
					case 10: // 10. 조식 신청한 게스트 조회하기
						// Date(2025,5,8) 조회하기 -> 조식 신청이 있는 경우
						// Date(2025,5,9) 조회하기 -> 조식 신청인이 없는 경우
						System.out.println("조회할 날짜의 연도를 입력하세요 => ");
						int year10 = sc.nextInt();
						System.out.println("조회할 날짜의 월을 입력하세요 => ");
						int month10 = sc.nextInt();
						System.out.println("조회할 날짜의 일을 입력하세요 => ");
						int day10 = sc.nextInt();
						ArrayList<Guest> bfday = new ArrayList<>();
						try {
							bfday = service.searchBreakfastGuest(new Date(year10,month10,day10));
							System.out.println(year10+"/"+month10+"/"+day10+"에 조식을 신청한 인원 수는 "+bfday.size()+"명입니다.\n조식 신청 게스트 목록은 아래와 같습니다.");
							for(Guest g : bfday)
								System.out.println(g);
						} catch (NoBreakfastException e) {
							System.out.println(e.getMessage());
						}
						break;
					case 11: // 11. 날짜별 조식 가격 조회하기
						System.out.println("조회할 날짜의 연도를 입력하세요 => ");
						int year11 = sc.nextInt();
						System.out.println("조회할 날짜의 월을 입력하세요 => ");
						int month11 = sc.nextInt();
						System.out.println("조회할 날짜의 일을 입력하세요 => ");
						int day11 = sc.nextInt();
						try {
							System.out.println("조식 타입은 "+FoodType.Korean.getValue()+", "+"인당 가격은 "+service.makeBreakfast(new Date(year11,month11,day11))+"입니다.");
						} catch (NoBreakfastException e) {
							System.out.println(e.getMessage());
						}
						break;
					case 12: // 12. 월별 조식 가격 평균 조회하기
						// 2025년 5월 조식 가격 평균 조회하기
						System.out.println("조회할 날짜의 연도를 입력하세요 => ");
						int year12 = sc.nextInt();
						System.out.println("조회할 날짜의 월을 입력하세요 => ");
						int month12 = sc.nextInt();
						try {
							int avg = service.searchAvgBreakfastPrice(year12, month12);
							System.out.println(year12+"년 "+month12+"월의 평균 조식 가격 : "+ avg);
						}catch(NoBreakfastException e) {
							System.out.println("조식 정보가 없습니다."+e.getMessage());
						}
						break;
					case 13: // 13. 서비스 종료
						System.out.println("프로그램을 종료합니다!!");
						return;
					default:
						System.out.println("잘못된 입력입니다. 다시 시도해 주세요!");
				}
			}
		}
	}
}