package com.gh.util;
/**
 * <pre>
 * {@code
 * FoodType enum 클래스는 조식 타입과 관련된 정보를 가지고 있는 클래스
 * 게스트하우스에서 조식으로 한식과 양식을 제공한다.
 * }
 * </pre>
 */
public enum FoodType {
	Korean("미역국"),
	Western("샌드위치");

	private final String value;
	
	FoodType(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return value;
	}
}