package kr.spring.service;

import java.util.List;

import jakarta.servlet.http.HttpSession;
import kr.spring.entity.ChatRoom;
import kr.spring.entity.Chatting;

public interface ChatService {

	void createChatRoom(ChatRoom chatRoom, HttpSession session);

	void createChatting(Chatting chatting, HttpSession session);

	List<ChatRoom> findAllChatRooms();

	
	
	
}
