package com.gh.rsv;
/**
 * <pre>
 * {@code
 *  party 클래스는 파티 예약 옵션을 정의합니다.
 *  각 파티는 고유 번호와 참가비 정보를 포함합니다.
 * }
 * </pre>
 */
public class Party {
	private int partyNum;
	private int attendFee;

	public Party() {
	}

	public Party(int partyNum, int attendFee) {
		super();
		this.partyNum = partyNum;
		this.attendFee = attendFee;
	}

	public int getPartyNum() {
		return partyNum;
	}

	public void setPartyNum(int partyNum) {
		this.partyNum = partyNum;
	}

	public int getAttendFee() {
		return attendFee;
	}

	public void setAttendFee(int attendFee) {
		this.attendFee = attendFee;
	}

	@Override
	public String toString() {
		return "Party partyNum=" + partyNum + ", attendFee=" + attendFee + "";
	}

}
