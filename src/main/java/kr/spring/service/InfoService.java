package kr.spring.service;


import java.util.Map;

import kr.spring.entity.Info;


public interface InfoService {

   /* public void InsertInfo(String username); */

   public void InsertInfo(String nickname, String username, String password);

	public Info login(Info m);
	
	public Info SelectInfo(String username_session); //세션값 있어야 불러올 수 있음.

	public Info InsertInfoAdditional(Info m, String username_session);

	public Map<Integer, String> selectMemPhoto(String username_session);
	
	public void fileUpload(Map<Integer, String> additionalFile, String username_session);


   
   
   
}