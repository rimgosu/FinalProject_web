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

public class MemberUpdate {
	public void main(String[] args) {
		//Use DriverConfigLoader to load your configuration file
		Path configPath = Paths.get("c:/keys/keyspace/application.conf");
        DriverConfigLoader loader = DriverConfigLoader.fromPath(configPath);
       
        try (CqlSession session = CqlSession.builder()
                .withConfigLoader(loader)
                .build()) {
        	
			try {
				Resource resource = new ClassPathResource("cqls/MemberUpdate.cql");
				String cql = new String(Files.readAllBytes(Paths.get(resource.getURI())));
				PreparedStatement preparedStatement = session.prepare(cql);
				session.execute(preparedStatement.bind(
		                "또스마스",
		                "서보경123"
		        ));

			} catch (IOException e) {
				e.printStackTrace();
			}
        	

        }
	}
}
