package com.gh.service;

import com.gh.util.Date;

public interface GehaService extends SearchService,DMLService {
	/**
	 * 특정 일자에 파티를 신청한 Guest를 바탕으로 파티를 배정하는 기능
	 * @param rsvDate
	 */
	void makeParty(Date rsvDate);
	
	/**
	 * 특정 일자에 조식을 신청한 Guest 수를 바탕으로 조식의 최소 준비 가격을 계산하는 기능
	 * 그리디 알고리즘 (탐욕법) 사용
	 * 최적의 답을 선택하는 과정을 반복하여 최적에 근사한 결과를 도출하는 알고리즘
	 * 참고한 백준 문제 번호
	 * 1) BOJ2839 설탕 배달 https://www.acmicpc.net/problem/2839
	 * 2) BOJ11047 동전 0 https://www.acmicpc.net/problem/11047
	 * 주문 수량이 늘어날수록 낱개의 거래 가격이 적어지는 단체 주문의 특징을 활용하여
	 * FoodShop과의 거래에서 적은 비용으로 조식을 준비할 수 있다.
	 * 거래 가격에 따른 개당 가격은 아래와 같이 설정한다.
	 * 1) 1개 거래시 개당 4000원
	 * 2) 10개 거래시 개당 3300원
	 * 3) 15개 거래시 개당 3000원
	 * @param rsvDate
	 * @return 조식 인당 가격 반환
	 */
	int makeBreakfast(Date rsvDate);
}