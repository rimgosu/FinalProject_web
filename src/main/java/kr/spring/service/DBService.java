package kr.spring.service;

import java.util.List;

import com.datastax.oss.driver.api.core.config.DriverConfigLoader;

import kr.spring.entity.ChatRoom;

public interface DBService {
	
	// 카산드라 데이터베이스에 접속
	public DriverConfigLoader getConnection();
	<T> void save(DriverConfigLoader loader, Class<T> entityClass, T entity);
	public List<ChatRoom> findAll(DriverConfigLoader loader, Class<ChatRoom> class1, ChatRoom chatroom);

}