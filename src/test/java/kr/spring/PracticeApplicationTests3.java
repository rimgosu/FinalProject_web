package kr.spring;

import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.datastax.oss.driver.api.core.config.DriverConfigLoader;

import jnr.ffi.annotations.In;
import kr.spring.entity.Chatting;
import kr.spring.entity.Info;
import kr.spring.entity.Interaction;
import kr.spring.service.DBService;

@SpringBootTest
class PracticeApplicationTests3 {
	
	@Autowired
	DBService dbService;

	
	
	  @Test void myTest() {
		
        DriverConfigLoader loader = dbService.getConnection();
        
        List<Interaction> interactions = dbService.findAllByColumnValue(loader, Interaction.class, "interaction_uuid", UUID.fromString("0fde1138-c23e-4b18-8d32-276013bcf6bf"));
        for (Interaction interaction : interactions) {
        	System.out.println(interaction.toString());
        }
			
			
//		  DriverConfigLoader loader = dbService.getConnection();
//		  
//		  List<Interaction> interactions = dbService.findAll(loader, Interaction.class);
//		  
//		  for (Interaction interaction : interactions) {
//			  System.out.println(interaction.toString()); 
//		  }
			 
			 
			/*
			 * DriverConfigLoader loader = dbService.getConnection(); List<Info> infos =
			 * dbService.findAll(loader, Info.class);
			 * 
			 * for (Info info : infos) { System.out.println(info.toString()); }
			 */
	  
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
