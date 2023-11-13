package kr.spring.service;

import javax.servlet.http.HttpSession;

import kr.spring.entity.ChatRoom;

public interface ChatService {

	void createChatRoom(ChatRoom chatRoom, HttpSession session);
	
	
	
}
