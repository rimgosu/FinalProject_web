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
class PracticeApplicationTests4 {
	
	@Autowired
	DBService dbService;

	
	  // interaction 가데이터 집어넣기
	  
	  @Test void interactionSave() {
	  
	  Interaction interaction = new Interaction();
	  
	  UUID uuid1 = UUID.fromString("e493b575-4065-4d7c-9c6a-2c7affbc8135");
	  UUID uuid2 = UUID.fromString("5f57d331-600c-4920-b974-bf7b54ac8803");
	  
	  interaction.setFrom_to("from");
	  interaction.setInteraction_regdate(Instant.now());
	  interaction.setInteraction_uuid(UUID.randomUUID());
	  interaction.setMy_username("b@gmail.com");
	  interaction.setOpponent_username("a@gmail.com");
	  interaction.setType("chatting"); interaction.setType_uuid(uuid1);
	  interaction.setLast_checked(Instant.now()); DriverConfigLoader loader =
	  dbService.getConnection(); dbService.save(loader, Interaction.class,
	  interaction);
	  
	  interaction.setFrom_to("to");
	  interaction.setInteraction_regdate(Instant.now());
	  interaction.setInteraction_uuid(UUID.randomUUID());
	  interaction.setMy_username("b@gmail.com");
	  interaction.setOpponent_username("a@gmail.com");
	  interaction.setType("chatting"); interaction.setType_uuid(uuid1);
	  interaction.setLast_checked(Instant.now()); dbService.save(loader,
	  Interaction.class, interaction);
	  
	  interaction.setFrom_to("from");
	  interaction.setInteraction_regdate(Instant.now());
	  interaction.setInteraction_uuid(UUID.randomUUID());
	  interaction.setMy_username("b@gmail.com");
	  interaction.setOpponent_username("c@gmail.com");
	  interaction.setType("chatting"); interaction.setType_uuid(uuid2);
	  dbService.save(loader, Interaction.class, interaction);
	  interaction.setLast_checked(Instant.now());
	  
	  interaction.setFrom_to("to");
	  interaction.setInteraction_regdate(Instant.now());
	  interaction.setInteraction_uuid(UUID.randomUUID());
	  interaction.setMy_username("b@gmail.com");
	  interaction.setOpponent_username("c@gmail.com");
	  interaction.setType("chatting"); interaction.setType_uuid(uuid2);
	  interaction.setLast_checked(Instant.now()); dbService.save(loader,
	  Interaction.class, interaction);
	  
	  }
	 
	 

}
