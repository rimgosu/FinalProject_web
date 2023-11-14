package kr.spring.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.config.DriverConfigLoader;
import com.datastax.oss.driver.api.core.cql.BoundStatement;
import com.datastax.oss.driver.api.core.cql.PreparedStatement;
import com.datastax.oss.driver.api.core.cql.ResultSet;
import com.datastax.oss.driver.api.core.cql.Row;

import kr.spring.TableColumnsValues;
import kr.spring.entity.ChatRoom;

@Service
public class DBServiceImpl implements DBService{

	@Override
	public DriverConfigLoader getConnection() {
		Path configPath = Paths.get("c:/keys/keyspace/application.conf");
        DriverConfigLoader loader = DriverConfigLoader.fromPath(configPath);
        
        return loader;
	}
	

	@Override
	public <T> void save(DriverConfigLoader loader, Class<T> entityClass, T entity) {
		
	    TableColumnsValues.Result<T> result = TableColumnsValues.extractData(entityClass, entity);
	    try (CqlSession session = CqlSession.builder()
	            .withConfigLoader(loader)
	            .build()) {

	        String columns = String.join(", ", result.columnNames);
	        String placeholders = String.join(", ", Collections.nCopies(result.columnNames.length, "?"));
	        String cql = String.format("INSERT INTO %s (%s) VALUES (%s)", "member."+result.tableName, columns, placeholders);

	        PreparedStatement preparedStatement = session.prepare(cql);
	        session.execute(preparedStatement.bind((Object[]) result.values));
	    } catch (Exception e) {
	        // 오류 처리 로직
	        System.out.println("save Error: " + e);
	    }
	}


	@Override
	public List<ChatRoom> findAll(DriverConfigLoader loader, Class<ChatRoom> class1, ChatRoom chatroom) {
	    List<ChatRoom> chatRooms = new ArrayList<>();
	    try (CqlSession session = CqlSession.builder()
	            .withConfigLoader(loader)
	            .build()) {

	        String cql = String.format("SELECT * FROM %s", "member.chatroom");

	        PreparedStatement preparedStatement = session.prepare(cql);
	        ResultSet resultSet = session.execute(preparedStatement.bind());
	        for (Row row : resultSet) {
	            // 예시로, ChatRoom 객체를 생성하고 속성을 설정합니다.
	            // ChatRoom 클래스의 구조에 맞게 필드를 설정해야 합니다.
	            ChatRoom chatRoom = new ChatRoom();
	            chatRoom.setRoom_describe(row.getString("room_describe"));
	            chatRoom.setRoom_joined(row.getString("room_joined"));
	            chatRoom.setRoom_maker(row.getString("room_maker"));
	            chatRoom.setRoom_uuid(row.getUuid(("room_uuid")));
	            chatRoom.setRoom_regdate(row.getInstant("room_regdate"));
	            chatRoom.setRoom_status(row.getString("room_status"));
	            chatRoom.setRoom_title(row.getString("room_title"));

	            
	            // 리스트에 추가
	            chatRooms.add(chatRoom);
	        }

	    } catch (Exception e) {
	        // 오류 처리 로직
	        System.out.println("Error: " + e);
	    }

	    return chatRooms;
	}



	
}