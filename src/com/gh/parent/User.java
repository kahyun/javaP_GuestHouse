package com.gh.parent;
/**
 * <pre>
 * {@code
 * User 클래스는 프로젝트의 사용자 정보를 가지고 있는 클래스
 * }
 * </pre>
 */
public abstract class User {
	/**
	 * User(Guest) 의 이름
	 */
	private String name;

	public User() {}
	public User(String name) {
		super();
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	@Override
	public String toString() {
		return "User [name=" + name + "] ";
	}
}