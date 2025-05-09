package com.gh.rsv;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import com.gh.child.Owner;
/**
 * <pre>
 * {@code
 * FoodShop 클래스는 조식을 결정하기 위해 오너가 신청을 받아 처리하는 시스텀의
 * 정보를 정의합니다.
 * 
 * 신청 내역을 활용해서 조식 수요를 파악하고 제공할 식사를 결정하는 데 활용됩니다.
 * }
 * </pre>
 */
public class FoodShop {

	private HashMap<Integer, Integer> foodMap;
	private Owner owner;

	public FoodShop() {
	}
	
	public FoodShop(HashMap<Integer, Integer> foodMap, Owner owner) {
		super();
		this.foodMap = foodMap;
		this.owner = owner;
	}
	
	public HashMap<Integer, Integer> getFoodMap() {
		return foodMap;
	}

	public void setFoodMap(HashMap<Integer, Integer> foodMap) {
		this.foodMap = foodMap;
	}

	public Owner getOwner() {
		return owner;
	}

	public void setOwner(Owner owner) {
		this.owner = owner;
	}

	@Override
	public String toString() {
		return "FoodShop [foodMap=" + foodMap + ", owner=" + owner + "]";
	}
}