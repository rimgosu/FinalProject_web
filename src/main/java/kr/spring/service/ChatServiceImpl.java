package kr.spring.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.config.DriverConfigLoader;
import com.datastax.oss.driver.api.core.cql.PreparedStatement;
import com.datastax.oss.driver.api.core.cql.ResultSet;
import com.datastax.oss.driver.api.core.cql.Row;

import jakarta.servlet.http.HttpSession;
import kr.spring.entity.ChatRoomNotification;
import kr.spring.entity.Chatting;
import kr.spring.entity.Info;
import kr.spring.entity.Interaction;

@Service
public class ChatServiceImpl implements ChatService {

	@Autowired
	DBService dbService;

	@Override
	public void createChatting(Chatting chatting, HttpSession session) {

		/*
		 * 채팅 생성할 때 필요한 것들 1. room_uuid : (TODO 추후 방을 클릭하면 room_uuid를 가져오는 식으로 코딩을 할 것임)
		 * 2. chatted_at : 자바에서 걍 만들면 된다 3. chat_uuid : 상대방 아이디를 좋아요 정보를 통해 가져오면 됨 (아직
		 * 구현되지 않았음) ※ chat_cotent : 발화 내용은 자바스크립트에서 chatting에 담겨서 올 것임.
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
	public List<Chatting> getChattings(UUID room_uuid) {
		// TODO Auto-generated method stub
		System.out.println("[ChatServiceImpl][getChattings]");

		Map<String, Object> colval = new HashMap<String, Object>();
		colval.put("room_uuid", room_uuid);

		DriverConfigLoader loader = dbService.getConnection();
		List<Chatting> chattings = dbService.findAllByColumnValues(loader, Chatting.class, colval);

		return chattings;
	}

	@Override
	public List<ChatRoomNotification> getChatRoomInteractions(String username) {
		System.out.println("[ChatServiceImpl][getChatRoomInteractions][username:" + username + "]");
		Map<String, Object> interactionColumnValues = new HashMap<>();
		interactionColumnValues.put("from_to", "from");
		interactionColumnValues.put("type", "chatting");
		interactionColumnValues.put("my_username", username);
        
		System.out.println("[interactionColumnValues.toString()]" + interactionColumnValues.toString());
        
        DriverConfigLoader loader = dbService.getConnection();
        
        List<Interaction> interactions = dbService.findAllByColumnValues(loader, Interaction.class, interactionColumnValues);
        
        List<ChatRoomNotification> chatRoomNotifications = new ArrayList<ChatRoomNotification>();
        
        for (Interaction interaction : interactions) {
        	Map<String, Object> chatColumnValues = new LinkedHashMap<>();
        	chatColumnValues.put("room_uuid", interaction.getType_uuid());
        	chatColumnValues.put("read_status", false);
        	
        	List<Chatting> chattings = dbService.findAllByColumnValues(loader, Chatting.class, chatColumnValues);
        	ChatRoomNotification chatRoomNotification = new ChatRoomNotification(interaction);
        	chatRoomNotification.setNotification_count(chattings.size());
        	chatRoomNotifications.add(chatRoomNotification);
        	System.out.println(chatRoomNotification.toString());
        }
        
        
        
        return chatRoomNotifications;
	
	}


	@Override
	public void insertChatting(Chatting chatting) {
		DriverConfigLoader loader = dbService.getConnection();
		dbService.save(loader, Chatting.class, chatting);
	}

}