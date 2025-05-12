package com.gh.rsv;

import com.gh.child.Guest;
import com.gh.util.Date;
/**
 * <pre>
 *{@code
 * Reservation 클래스는 예약 정보를 표현합니다.
 * 예약 번호, 예약 날짜, 손님 정보, 방, 파티. 조식을 포함합니다.
 * }
 * </pre>
 */
public class Reservation {
	private int rsvNum;
	private Date rsvDate;
	private Guest rsvGuest;
	private Room rsvRoom;
	private Party party;
	private boolean eatBreakfast;

	public Reservation() {
	}


	public Reservation(int rsvNum, Date rsvDate, Guest rsvGuest, Room rsvRoom, Party party,
			boolean eatBreakfast) {
		super();
		this.rsvNum = rsvNum;
		this.rsvDate = rsvDate;
		this.rsvGuest = rsvGuest;
		this.rsvRoom = rsvRoom;
		this.party = party;
		this.eatBreakfast = eatBreakfast;
	}

	public Party getParty() {
		return party;
	}

	public void setParty(Party party) {
		party = party;
	}

	public int getRsvNum() {
		return rsvNum;
	}

	public void setRsvNum(int rsvNum) {
		this.rsvNum = rsvNum;
	}

	public Date getRsvDate() {
		return rsvDate;
	}

	public void setRsvDate(Date rsvDate) {
		this.rsvDate = rsvDate;
	}

	public Guest getRsvGuest() {
		return rsvGuest;
	}

	public void setRsvGuest(Guest rsvGuest) {
		this.rsvGuest = rsvGuest;
	}

	public Room getRsvRoom() {
		return rsvRoom;
	}

	public void setRsvRoom(Room rsvRoom) {
		this.rsvRoom = rsvRoom;
	}


	public boolean isEatBreakfast() {
		return eatBreakfast;
	}

	public void setEatBreakfast(boolean eatBreakfast) {
		this.eatBreakfast = eatBreakfast;
	}

	@Override
	public String toString() {
		return "Reservation [rsvNum=" + rsvNum + ", rsvDate=" + rsvDate + ", rsvGuest=" + rsvGuest + ", rsvRoom="
				+ rsvRoom + ", party=" + party + ", eatBreakfast=" + eatBreakfast + "]";
	}
}