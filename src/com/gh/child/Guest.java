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
	private char gender;
	private String hp;
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