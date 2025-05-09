package com.gh.service;

import java.util.ArrayList;
import java.util.TreeSet;

import com.gh.child.Guest;
import com.gh.rsv.Party;
import com.gh.rsv.Reservation;
import com.gh.rsv.Room;
import com.gh.util.Date;

public interface SearchService {
	/**
	 * Guest 가 예약 번호를 조회하여 예약 정보를 조회하는 기능
	 * @param rsvNum
	 * @return 예약 정보를 String 타입으로 반환
	 */
	String searchRsv(int rsvNum);
	
	/**
	 * Guest 가 예약 번호를 바탕으로 배정받은 파티 정보를 조회하는 기능
	 * @param rsvNum
	 * @return 예약 번호에 해당하는 Party 정보
	 */
	Party searchParty(int rsvNum);
	
	/**
	 * 특정 일자를 기준으로 전체 파티 정보를 조회하는 기능
	 * @param rsvDate
	 * @return 특정 일자의 Party 배열
	 */
	Party[] searchParty(Date rsvDate);
	
	/**
	 * 특정 일자를 기준으로 조식을 신청한 게스트 목록을 조회하는 기능
	 * @param resvDate
	 * @return 특정 일자의 조식 신청 Guest 배열
	 */
	Guest[] searchBreakfastGuest(Date resvDate);
	
	/**
	 * 특정 일자를 기준으로 전체 예약 목록을 조회하는 기능
	 * @param rsvDate
	 * @return 특정 일자의 Reservation 배열
	 */
	Reservation[] searchAllRsv(Date rsvDate);

	/**
	 * 특정 일자를 기준으로 방 별 예약 현황을 조회하는 기능
	 * @param rsvDate
	 * @return 특정 일자의 방 예약 현황을 String 타입 반환
	 */
	String searchRsvCondition(Date rsvDate);

	/**
	 * 특정 달의 조식 가격의 평균 가격을 조회하는 기능
	 * @param year
	 * @param month
	 * @return 평균 가격 반환
	 */
	int searchAvgBreakfastPrice(int year,int month);

	/**
	 * 특정 날짜에 특정 성별의 예약 가능한 방 목록을 조회하는 기능
	 * @param rsvDate
	 * @param gender
	 * @return 예약 가능한 방 목록을 TreeSet 타입으로 오름차순 반환
	 */
	TreeSet<Room> searchAvailableRoom(Date rsvDate, char gender);

}
