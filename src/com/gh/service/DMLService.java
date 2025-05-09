package com.gh.service;

import com.gh.child.Guest;
import com.gh.exception.NoRoomException;
import com.gh.rsv.Reservation;
import com.gh.util.Date;

public interface DMLService {
	/**
	 * Guest 가 예약하는 기능
	 * @param rsvDate
	 * @param gender
	 * @param roomType
	 * @param rsvGuest
	 * @param attendFee
	 * @param eatBreakfast
	 * @return 예약 객체 반환
	 */
	Reservation makeRsv(Date rsvDate, char gender, int roomType, Guest rsvGuest,int attendFee, boolean eatBreakfast) throws NoRoomException;
	
	/**
	 * Guest 가 예약번호를 조회하여 예약 정보를 수정하는 기능
	 * @param rsvNum
	 * @param rsv
	 */
	void updateRsv(int rsvNum, Reservation rsv);
	
	/**
	 * Guest 가 예약번호를 조회하여 예약 내역을 삭제하는 기능
	 * @param rsvNum
	 */
	void deleteRsv(int rsvNum);
}