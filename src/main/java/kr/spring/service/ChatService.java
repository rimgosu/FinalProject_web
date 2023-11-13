package kr.spring.service;

import javax.servlet.http.HttpSession;

import kr.spring.entity.ChatRoom;
import kr.spring.entity.Chatting;

public interface ChatService {

	void createChatRoom(ChatRoom chatRoom, HttpSession session);

	void createChatting(Chatting chatting, HttpSession session);
	
	
	
}
