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
import com.datastax.oss.driver.api.core.cql.PreparedStatement;
import com.datastax.oss.driver.api.core.cql.ResultSet;
import com.datastax.oss.driver.api.core.cql.Row;

public class SelectMember {
	public void main(String[] args) {
		//Use DriverConfigLoader to load your configuration file
		Path configPath = Paths.get("c:/keys/keyspace/application.conf");
        DriverConfigLoader loader = DriverConfigLoader.fromPath(configPath);
        try (CqlSession session = CqlSession.builder()
                .withConfigLoader(loader)
                .build()) {
        	
			try {
				Resource resource = new ClassPathResource("cqls/SelectMember.cql");
				String cql = new String(Files.readAllBytes(Paths.get(resource.getURI())));
				PreparedStatement preparedStatement = session.prepare(cql);
				
				ResultSet resultSet = session.execute(preparedStatement.bind());
				
				
                // Process the results
                for (Row row : resultSet) {
                    // Access the columns in the result set using row.getXXX() methods
                    String username = row.getString("username");
                    String password = row.getString("password");
                    String nickname = row.getString("nickname");

                    // Do something with the retrieved data
                    System.out.println("Username: " + username);
                    System.out.println("Password: " + password);
                    System.out.println("Nickname: " + nickname);
                }


			} catch (IOException e) {
				e.printStackTrace();
			}
        	

        }
	}
}
