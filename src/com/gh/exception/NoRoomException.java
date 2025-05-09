package com.gh.exception;
/**
 * <pre>
 * {@code
 * NoRoomException 클래스는 예약 가능한 방이 없을 경우 예외를 처리하는 클래스
 * 예약 가능한 방이 없을 경우 예약을 더이상 진행하지 않고 정상 종료한다.
 * }
 * </pre>
 */
public class NoRoomException extends Exception{
	public NoRoomException() {
		this("This is NoRoomException");
	}
	public NoRoomException(String message) {
		super(message);
	}
}