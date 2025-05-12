package com.gh.rsv;
/**
 * <pre>
 * {@code
 * Breakfast 클래스는 조식 신청 여부를 정의합니다.
 * }
 * </pre>
 */
public class Breakfast {
	private boolean eatBreakfast;

	public Breakfast() {
	}

	public Breakfast(boolean eatBreakfast) {
		super();
		this.eatBreakfast = eatBreakfast;
	}

	public boolean getEatBreakfast() {
		return eatBreakfast;
	}

	public void setEatBreakfast(boolean eatBreakfast) {
		this.eatBreakfast = eatBreakfast;
	}

	@Override
	public String toString() {
		return "Breakfast eatBreakfast=" + eatBreakfast + "";
	}

}
