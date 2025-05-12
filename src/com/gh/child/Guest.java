package com.gh.child;
/**
 * <pre>
 * {@code
 * Guest 클래스는 게스트와 관련된 정보를 가지고 있는 클래스
 * 이름, 성별, 연락처, 생년월일 정보를 가지고 있다.
 * }
 * </pre>
 */
import com.gh.parent.User;
import com.gh.util.Date;

public class Guest extends User{
	/**
	 * Guest 의 성별...남성은 'M', 여성은 'F'
	 */
	private char gender;
	/**
	 * Guest 의 연락처...전화번호는 '-'를 포함한다
	 */
	private String hp;
	/**
	 * Guest 의 생년월일...YYYY/MM/DD 형식을 갖는다
	 */
	private Date birthDate;
	
	public Guest() {
		super();
	}
	public Guest(String name, char gender, String hp, Date birthDate) {
		super(name);
		this.gender = gender;
		this.hp = hp;
		this.birthDate = birthDate;
	}
	
	public char getGender() {
		return gender;
	}
	public String getHp() {
		return hp;
	}
	public Date getBirthDate() {
		return birthDate;
	}
	
	@Override
	public String toString() {
		return super.toString() + "Guest [gender=" + gender + ", hp=" + hp + ", birthDate=" + birthDate + "]";
	}
}