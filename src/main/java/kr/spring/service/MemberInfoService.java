package kr.spring.service;

import jakarta.servlet.http.HttpSession;
import kr.spring.entity.MemberInfo;

public interface MemberInfoService {

	/* public void InsertMemberInfo(String username); */

	public void InsertMemberInfo(String nickname, String username, String password);

	public int SelectMemberInfo(String username, String password);

	public void InsertMemberInfoAdditional(MemberInfo info, String username_session);

	
	
	
}
