package kr.spring;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.datastax.oss.driver.api.core.config.DriverConfigLoader;

import kr.spring.entity.Chatting;
import kr.spring.entity.Info;
import kr.spring.entity.Interaction;
import kr.spring.service.DBService;

@SpringBootTest
class PracticeApplicationTests {
	
	@Autowired
	DBService dbService;

	
		@Test
	   void myTest() {
	  
	  String room_uuid_ab = "5f57d331-600c-4920-b974-bf7b54ac8803";
	  
	  Chatting chatting = new Chatting(); 
	  DriverConfigLoader loader = dbService.getConnection();
	  
	  chatting.setChat_chatter("a@gmail.com");
	  chatting.setChat_content("첫번째 채팅입니다."); chatting.setChat_emoticon(null);
	  chatting.setChat_uuid(UUID.randomUUID());
	  chatting.setChatted_at(Instant.now());
	  chatting.setRoom_uuid(UUID.fromString(room_uuid_ab));
	  chatting.setRead_status(true);
	  
	  System.out.println("chatting" + chatting.toString());
	  
	  
	  dbService.save(loader, Chatting.class, chatting);
	  
	  chatting.setChat_chatter("a@gmail.com");
	  chatting.setChat_content("두 번째 채팅입니다."); chatting.setChat_emoticon(null);
	  chatting.setChat_uuid(UUID.randomUUID());
	  chatting.setChatted_at(Instant.now());
	  chatting.setRoom_uuid(UUID.fromString(room_uuid_ab));
	  chatting.setRead_status(true);
	  
	  dbService.save(loader, Chatting.class, chatting);
	  
	  chatting.setChat_chatter("a@gmail.com");
	  chatting.setChat_content("세 번째 채팅입니다."); chatting.setChat_emoticon(null);
	  chatting.setChat_uuid(UUID.randomUUID());
	  chatting.setChatted_at(Instant.now());
	  chatting.setRoom_uuid(UUID.fromString(room_uuid_ab));
	  chatting.setRead_status(true);
	  
	  dbService.save(loader, Chatting.class, chatting);
	  
	  chatting.setChat_chatter("a@gmail.com");
	  chatting.setChat_content("네 번째 채팅입니다."); chatting.setChat_emoticon(null);
	  chatting.setChat_uuid(UUID.randomUUID());
	  chatting.setChatted_at(Instant.now());
	  chatting.setRoom_uuid(UUID.fromString(room_uuid_ab));
	  chatting.setRead_status(true);
	  
	  dbService.save(loader, Chatting.class, chatting);
	  
	  chatting.setChat_chatter("a@gmail.com");
	  chatting.setChat_content("다섯 번째 채팅입니다."); chatting.setChat_emoticon(null);
	  chatting.setChat_uuid(UUID.randomUUID());
	  chatting.setChatted_at(Instant.now());
	  chatting.setRoom_uuid(UUID.fromString(room_uuid_ab));
	  chatting.setRead_status(true);
	  
	  dbService.save(loader, Chatting.class, chatting);
	  
	  // b@gmail.com
	  chatting.setChat_chatter("a@gmail.com");
	  chatting.setChat_content("첫번째 채팅입니다."); chatting.setChat_emoticon(null);
	  chatting.setChat_uuid(UUID.randomUUID());
	  chatting.setChatted_at(Instant.now());
	  chatting.setRoom_uuid(UUID.fromString(room_uuid_ab));
	  chatting.setRead_status(true);
	  
	  System.out.println("chatting" + chatting.toString());
	  
	  
	  dbService.save(loader, Chatting.class, chatting);
	  
	  chatting.setChat_chatter("b@gmail.com");
	  chatting.setChat_content("두 번째 채팅입니다."); chatting.setChat_emoticon(null);
	  chatting.setChat_uuid(UUID.randomUUID());
	  chatting.setChatted_at(Instant.now());
	  chatting.setRoom_uuid(UUID.fromString(room_uuid_ab));
	  chatting.setRead_status(true);
	  
	  dbService.save(loader, Chatting.class, chatting);
	  
	  chatting.setChat_chatter("b@gmail.com");
	  chatting.setChat_content("세 번째 채팅입니다."); chatting.setChat_emoticon(null);
	  chatting.setChat_uuid(UUID.randomUUID());
	  chatting.setChatted_at(Instant.now());
	  chatting.setRoom_uuid(UUID.fromString(room_uuid_ab));
	  chatting.setRead_status(true);
	  
	  dbService.save(loader, Chatting.class, chatting);
	  
	  chatting.setChat_chatter("b@gmail.com");
	  chatting.setChat_content("네 번째 채팅입니다."); chatting.setChat_emoticon(null);
	  chatting.setChat_uuid(UUID.randomUUID());
	  chatting.setChatted_at(Instant.now());
	  chatting.setRoom_uuid(UUID.fromString(room_uuid_ab));
	  chatting.setRead_status(false);
	  
	  dbService.save(loader, Chatting.class, chatting);
	  
	  chatting.setChat_chatter("b@gmail.com");
	  chatting.setChat_content("다섯 번째 채팅입니다."); chatting.setChat_emoticon(null);
	  chatting.setChat_uuid(UUID.randomUUID());
	  chatting.setChatted_at(Instant.now());
	  chatting.setRoom_uuid(UUID.fromString(room_uuid_ab));
	  chatting.setRead_status(false);
	  
	  dbService.save(loader, Chatting.class, chatting);
	  
	  
	  
	  }
	 
	
	
	/*
	 * // interaction 가데이터 집어넣기
	 * 
	 * @Test void interactionSave() {
	 * 
	 * Interaction interaction = new Interaction();
	 * 
	 * UUID uuid1 = UUID.randomUUID(); UUID uuid2 = UUID.randomUUID();
	 * 
	 * 
	 * interaction.setFrom_to("from");
	 * interaction.setInteraction_regdate(Instant.now());
	 * interaction.setInteraction_uuid(UUID.randomUUID());
	 * interaction.setMy_username("a@gmail.com");
	 * interaction.setOpponent_username("b@gmail.com");
	 * interaction.setType("chatting"); interaction.setType_uuid(uuid1);
	 * interaction.setLast_checked(Instant.now()); DriverConfigLoader loader =
	 * dbService.getConnection(); dbService.save(loader, Interaction.class,
	 * interaction);
	 * 
	 * interaction.setFrom_to("to");
	 * interaction.setInteraction_regdate(Instant.now());
	 * interaction.setInteraction_uuid(UUID.randomUUID());
	 * interaction.setMy_username("a@gmail.com");
	 * interaction.setOpponent_username("b@gmail.com");
	 * interaction.setType("chatting"); interaction.setType_uuid(uuid1);
	 * interaction.setLast_checked(Instant.now()); dbService.save(loader,
	 * Interaction.class, interaction);
	 * 
	 * interaction.setFrom_to("from");
	 * interaction.setInteraction_regdate(Instant.now());
	 * interaction.setInteraction_uuid(UUID.randomUUID());
	 * interaction.setMy_username("a@gmail.com");
	 * interaction.setOpponent_username("c@gmail.com");
	 * interaction.setType("chatting"); interaction.setType_uuid(uuid2);
	 * dbService.save(loader, Interaction.class, interaction);
	 * interaction.setLast_checked(Instant.now());
	 * 
	 * interaction.setFrom_to("to");
	 * interaction.setInteraction_regdate(Instant.now());
	 * interaction.setInteraction_uuid(UUID.randomUUID());
	 * interaction.setMy_username("a@gmail.com");
	 * interaction.setOpponent_username("c@gmail.com");
	 * interaction.setType("chatting"); interaction.setType_uuid(uuid2);
	 * interaction.setLast_checked(Instant.now()); dbService.save(loader,
	 * Interaction.class, interaction);
	 * 
	 * }
	 */
	 

}
