package kr.spring.service;

import java.time.Instant;
import java.util.List;
import java.util.UUID;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.datastax.oss.driver.api.core.config.DriverConfigLoader;

import jakarta.servlet.http.HttpSession;
import kr.spring.entity.ChatRoom;
import kr.spring.entity.Chatting;
import kr.spring.entity.Info;

@Service
public class ChatServiceImpl implements ChatService{
	
	@Autowired
	DBService dbService;

	@Override
	public void createChatRoom(ChatRoom chatRoom, HttpSession session) {

		/*
		 * 채팅방 생성할 때 가져와야할 4가지.
		 * 1. room_uuid : 자바에서 걍 만들면된다
		 * 2. room_regdate : 자바에서 걍 만들면 된다 u
		 * 3. room_maker : 세션에서 가져오면 됨
		 * 4. room_joined : 상대방 아이디를 좋아요 정보를 통해 가져오면 됨 (TODO 아직 구현되지 않았음)
		 */
		System.out.println(chatRoom.toString());
		Info membersession = (Info) session.getAttribute("mvo");
		
		chatRoom.setRoom_uuid(UUID.randomUUID());
		chatRoom.setRoom_regdate(Instant.now());
		chatRoom.setRoom_maker(membersession.getUsername());
		
		System.out.println(chatRoom.toString());
		
		DriverConfigLoader loader = dbService.getConnection();
		dbService.save(loader, ChatRoom.class, chatRoom);
		
	}

	@Override
	public void createChatting(Chatting chatting, HttpSession session) {
		
		/*
		 * 채팅 생성할 때 필요한 것들
		 * 1. room_uuid : (TODO 추후 방을 클릭하면 room_uuid를 가져오는 식으로 코딩을 할 것임)
		 * 2. chatted_at : 자바에서 걍 만들면 된다
		 * 3. chat_uuid  : 상대방 아이디를 좋아요 정보를 통해 가져오면 됨 (아직 구현되지 않았음)
		 * ※ chat_cotent : 발화 내용은 자바스크립트에서 chatting에 담겨서 올 것임.
		 */
		
		System.out.println("ChatServiceImpl, createChatting 서비스에 들어옴.");
		System.out.println(chatting.toString());
		Info membersession = (Info) session.getAttribute("mvo");
		
		chatting.setChatted_at(Instant.now());
		chatting.setChat_uuid(UUID.randomUUID());
		chatting.setChat_chatter(membersession.getUsername());
		
		DriverConfigLoader loader = dbService.getConnection();
		dbService.save(loader, Chatting.class, chatting);
		
	}

	@Override
	public List<ChatRoom> findAllChatRooms() {
		// TODO Auto-generated method stub
		DriverConfigLoader loader = dbService.getConnection();
		return dbService.findAll(loader, ChatRoom.class);
	}
	
	
}