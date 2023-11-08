package kr.spring.cassandra;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.config.DriverConfigLoader;
import com.datastax.oss.driver.api.core.cql.BoundStatement;
import com.datastax.oss.driver.api.core.cql.PreparedStatement;
import com.datastax.oss.driver.api.core.cql.ResultSet;
import com.datastax.oss.driver.api.core.cql.Row;

public class App {
	public void main(String[] args) {
		//Use DriverConfigLoader to load your configuration file
		Path configPath = Paths.get("c:/keys/keyspace/application.conf");
        DriverConfigLoader loader = DriverConfigLoader.fromPath(configPath);
       
        try (CqlSession session = CqlSession.builder()
                .withConfigLoader(loader)
                .build()) {
        	
			try {
				Resource resource = new ClassPathResource("cqls/insert-query.cql");
				String cql = new String(Files.readAllBytes(Paths.get(resource.getURI())));
				PreparedStatement preparedStatement = session.prepare(cql);
	        	BoundStatement boundStatement = preparedStatement.bind(UUID.randomUUID(), "2023-11-06-test1", 100, "Doe");
	        	session.execute(boundStatement);
			} catch (IOException e) {
				e.printStackTrace();
			}
        	

        	ResultSet rs = session.execute("SELECT * FROM cycling.cyclist_category");
        	Row row = rs.one();

        	if (row != null) {
        	    String category = row.getString("category"); // 문자열 데이터 타입인 경우
        	    int points = row.getInt("points");           // 정수 데이터 타입인 경우
        	    UUID id = row.getUuid("id");                 // UUID 데이터 타입인 경우
        	    String lastname = row.getString("lastname"); // 문자열 데이터 타입인 경우

        	    // 각 열의 값을 콘솔에 출력합니다.
        	    System.out.println("Category: " + category);
        	    System.out.println("Points: " + points);
        	    System.out.println("ID: " + id);
        	    System.out.println("Last Name: " + lastname);
        	} else {
        	    System.out.println("결과가 없습니다.");
        	}
        }
	}
}
