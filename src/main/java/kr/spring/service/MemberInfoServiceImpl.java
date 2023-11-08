package kr.spring.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.config.DriverConfigLoader;
import com.datastax.oss.driver.api.core.cql.PreparedStatement;

@Service
public class MemberInfoServiceImpl implements MemberInfoService{

	@Override
	public void InsertMemberInfo(String username) {
		// TODO Auto-generated method stub
		
		Path configPath = Paths.get("c:/keys/keyspace/application.conf");
        DriverConfigLoader loader = DriverConfigLoader.fromPath(configPath);
		//Use DriverConfigLoader to load your configuration file
        try (CqlSession session = CqlSession.builder()
                .withConfigLoader(loader)
                .build()) {
        	
			
			String cql = "insert into member.info (username) values (?)";
			PreparedStatement preparedStatement = session.prepare(cql);
			session.execute(preparedStatement.bind(username));

        }
		
	}
	
}
// get connection 