package com.gh.exception;
/**
 * <pre>
 * {@code
 * NoBreakfastException 클래스는 조식 신청자가 아무도 없을 경우 예외를 처리하는 클래스
 * 신청 인원이 0명이라 0으로 나누는 오류를 사전에 방지한다.
 * }
 * </pre>
 */
public class NoBreakfastException extends Exception{
	public NoBreakfastException() {
		this("This is NoBreakfastException");
	}
	public NoBreakfastException(String message) {
		super(message);
	}
}