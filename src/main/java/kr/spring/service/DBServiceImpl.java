package kr.spring.service;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.config.DriverConfigLoader;
import com.datastax.oss.driver.api.core.cql.BoundStatement;
import com.datastax.oss.driver.api.core.cql.PreparedStatement;

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


	
}