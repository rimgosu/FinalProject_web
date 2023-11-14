package kr.spring.service;

import java.io.IOException;
import java.lang.reflect.Field;
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
	            ChatRoom chatRoom = new ChatRoom();

	            for (Field field : ChatRoom.class.getDeclaredFields()) {
	                field.setAccessible(true); // 필드 접근 허용

	                try {
	                    setFieldValue(field, chatRoom, row);
	                } catch (IllegalAccessException e) {
	                    System.out.println("Field access error: " + e.getMessage());
	                    // 적절한 예외 처리
	                }
	            }

	            chatRooms.add(chatRoom);
	        }


	    } catch (Exception e) {
	        // 오류 처리 로직
	        System.out.println("Error: " + e);
	    }

	    return chatRooms;
	}
	
	
	public void setFieldValue(Field field, ChatRoom chatRoom, Row row) throws IllegalAccessException {
	    String columnName = field.getName();

	    if (field.getType().equals(String.class)) {
	        field.set(chatRoom, row.getString(columnName));
	    } else if (field.getType().equals(UUID.class)) {
	        field.set(chatRoom, row.getUuid(columnName));
	    } else if (field.getType().equals(Instant.class)) {
	        field.set(chatRoom, row.getInstant(columnName));
	    } else if (field.getType().equals(Integer.class)) {
	        field.set(chatRoom, row.getInt(columnName));
	    } else if (field.getType().equals(Long.class)) {
	        field.set(chatRoom, row.getLong(columnName));
	    } else if (field.getType().equals(Double.class)) {
	        field.set(chatRoom, row.getDouble(columnName));
	    } else if (field.getType().equals(List.class)) {
	        field.set(chatRoom, row.getList(columnName, String.class));
	    } else if (field.getType().equals(Map.class)) {
	        field.set(chatRoom, row.getMap(columnName, Instant.class, String.class));
	    }
	    // 추가적인 타입에 대한 처리는 여기에 추가
	}



	
}