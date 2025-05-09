package com.gh.rsv;
/**
 * <pre>
 * 
 * {@code
 *  room클래스는 객실 정보를 정의합니다.
 *  객실 번호, 객실 유형, 사용 가능 성별, 가격 정보를 포함합니다.
 * }
 * </pre>
 */
public class Room implements Comparable<Room> {
	private String roomNum;
	private int roomType;
	private char gender;
	private int price;
	private int booked;

	public Room() {
	}

	public Room(String roomNum, int roomType, char gender, int price, int booked) {
		super();
		this.roomNum = roomNum;
		this.roomType = roomType;
		this.gender = gender;
		this.price = price;
		this.booked = booked;
	}

	public String getRoomNum() {
		return roomNum;
	}

	public void setRoomNum(String roomNum) {
		this.roomNum = roomNum;
	}

	public int getRoomType() {
		return roomType;
	}

	public void setRoomType(int roomType) {
		this.roomType = roomType;
	}

	public char getGender() {
		return gender;
	}

	public void setGender(char gender) {
		this.gender = gender;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}
	
	public int getBooked() {
		return booked;
	}

	public void setBooked(int booked) {
		this.booked = booked;
	}

	@Override
	public String toString() {
		return "Room [roomNum=" + roomNum + ", roomType=" + roomType + ", gender=" + gender + ", price=" + price
				+ ", booked=" + booked + "]";
	}

	@Override
	public int compareTo(Room o) {
		return this.roomNum.compareTo(o.roomNum);
	}
}