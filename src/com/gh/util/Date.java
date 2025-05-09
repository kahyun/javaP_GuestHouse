package com.gh.util;
/**
 * <pre>
 * {@code
 * Date 클래스는 날짜와 관련된 정보를 가지고 있는 클래스
 * 연도/월/일 정보를 가지고 있다.
 * }
 * </pre>
 */
public class Date {
	private int year;
	private int month;
	private int day;
	
	public Date() {}
	public Date(int year, int month, int day) {
		super();
		this.year = year;
		this.month = month;
		this.day = day;
	}
	
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public int getMonth() {
		return month;
	}
	public void setMonth(int month) {
		this.month = month;
	}
	public int getDay() {
		return day;
	}
	public void setDay(int day) {
		this.day = day;
	}
	
	@Override
	public String toString() {
		return year + "/" + month + "/" + day;
	}
}