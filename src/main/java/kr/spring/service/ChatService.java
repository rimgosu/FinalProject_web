package kr.spring.service;

import java.util.List;
import java.util.UUID;

import jakarta.servlet.http.HttpSession;
import kr.spring.entity.ChatRoomNotification;
import kr.spring.entity.Chatting;

public interface ChatService {

	void createChatting(Chatting chatting, HttpSession session);

	List<Chatting> getChattings(UUID room_uuid);

	List<ChatRoomNotification> getChatRoomInteractions(String username);

	void insertChatting(Chatting chatting);

	
	
	
}
