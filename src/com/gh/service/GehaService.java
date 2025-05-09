package com.gh.service;

import com.gh.util.Date;

public interface GehaService extends SearchService,DMLService {
	/**
	 * 특정 일자에 파티를 신청한 Guest를 바탕으로 파티를 배정하는 기능
	 * @param rsvDate
	 */
	void makeParty(Date rsvDate);
	
	/**
	 * 특정 일자에 조식을 신청한 Guest를 바탕으로 조식 최소 가격을 계산하는 기능
	 * 그리디 알고리즘 사용
	 * 참고한 백준 문제 번호 : BOJ
	 * 솔루션 패턴 등 자세한 주석 달기
	 * @param rsvDate
	 * @return 조식 인당 가격 반환
	 */
	int makeBreakfast(Date rsvDate);
}