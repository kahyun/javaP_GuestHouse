package com.gh.child;
/**
 * <pre>
 * {@code
 * Owner 클래스는 게스트하우스의 주인 정보를 가지고 있는 클래스
 * 주인이 날마다 조식 타입을 결정한다.
 * }
 * </pre>
 */
import com.gh.parent.User;
import com.gh.util.FoodType;

public class Owner extends User{
	private FoodType foodType;

	public Owner() {
		super();
	}
	public Owner(String name, FoodType foodType) {
		super(name);
		this.foodType = foodType;
	}
	
	public FoodType getFoodType() {
		return foodType;
	}
	public void setFoodType(FoodType foodType) {
		this.foodType = foodType;
	}
	
	@Override
	public String toString() {
		return super.toString() + "Owner [foodType=" + foodType + "]";
	}
}