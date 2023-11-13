package kr.spring.service;

import com.datastax.oss.driver.api.core.config.DriverConfigLoader;

import kr.spring.entity.ChatRoom;

public interface DBService {
	
	// 카산드라 데이터베이스에 접속
	public DriverConfigLoader getConnection();
	public void save(DriverConfigLoader loader, ChatRoom chatRoom);

}