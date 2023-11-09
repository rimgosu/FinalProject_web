package kr.spring.service;

import kr.spring.entity.MemberInfo;

public interface MemberInfoService {

	/* public void InsertMemberInfo(String username); */

	public void InsertMemberInfo(String nickname, String username, String password);

	public int SelectMemberInfo(String username, String password);

	/*
	 * public void InsertMemberInfoAdditional(MemberInfo info);
	 */
	
	
	
}