package kr.spring.service;

import java.util.Map;

import jakarta.servlet.http.HttpSession;
import kr.spring.entity.MemberInfo;

public interface MemberInfoService {

	/* public void InsertMemberInfo(String username); */

	public void InsertMemberInfo(String nickname, String username, String password);

	public MemberInfo login(MemberInfo m);
	
	public MemberInfo SelectMemberInfo(String username_session); //세션값 있어야 불러올 수 있음.

	public MemberInfo InsertMemberInfoAdditional(MemberInfo m, String username_session);

	public Map<Integer, String> selectMemPhoto(String username_session);
	
	public void fileUpload(Map<Integer, String> additionalFile, String username_session);

	
	
	
}
