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

public class MemberInsert {
	public void main(String[] args) {
		Path configPath = Paths.get("c:/keys/keyspace/application.conf");
        DriverConfigLoader loader = DriverConfigLoader.fromPath(configPath);
		//Use DriverConfigLoader to load your configuration file
        try (CqlSession session = CqlSession.builder()
                .withConfigLoader(loader)
                .build()) {
        	
			try {
				Resource resource = new ClassPathResource("cqls/MemberInsert.cql");
				String cql = new String(Files.readAllBytes(Paths.get(resource.getURI())));
				PreparedStatement preparedStatement = session.prepare(cql);
				session.execute(preparedStatement.bind(
		                "서보경123",
		                "비밀번호",
		                "닉네임",
		                "전화번호",
		                30, // 나이
		                "관심사",
		                "MBTI",
		                "스포츠",
		                "음주 여부",
		                "흡연 여부",
		                "직업",
		                null,
		                "자기 소개",
		                java.time.Instant.now(), // 등록 날짜
		                java.util.Arrays.asList("역할1", "역할2"), // 역할 목록
		                java.util.Arrays.asList("주소1", "위도", "경도"), // 주소 목록
		                java.util.Collections.singletonMap(1, "사진 경로")
		        ));

			} catch (IOException e) {
				e.printStackTrace();
			}
        	

        }
	}
}
