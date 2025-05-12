package com.gh.exception;
/**
 * <pre>
 * {@code
 * NoReservationException 클래스는 예약 내역이 없을 경우 예외를 처리하는 클래스
 * 예약 내역이 없으면 수정과 삭제가 불가하다.
 * }
 * </pre>
 */
public class NoReservationException extends Exception{
	public NoReservationException() {
		this("This is NoReservationException");
	}
	public NoReservationException(String message) {
		super(message);
	}
}