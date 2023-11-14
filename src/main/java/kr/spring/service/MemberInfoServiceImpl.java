package kr.spring.service;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;
import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.config.DriverConfigLoader;

import com.datastax.oss.driver.api.core.cql.PreparedStatement;
import com.datastax.oss.driver.api.core.cql.ResultSet;
import com.datastax.oss.driver.api.core.cql.Row;

import kr.spring.entity.MemberInfo;

@Service
public class MemberInfoServiceImpl implements MemberInfoService {
	
	//회원가입 시, 정보 넣는 메서드
	@Override
	public void InsertMemberInfo(String nickname, String username, String password) {
		Path configPath = Paths.get("c:/keys/keyspace/application.conf");
		DriverConfigLoader loader = DriverConfigLoader.fromPath(configPath);

		try (CqlSession session = CqlSession.builder().withConfigLoader(loader).build()) {

			String cql = """
					insert into member.info (
						nickname, username, password, register_date
					) values (
						?, ?, ?, toTimestamp(now())
					)""";
			PreparedStatement preparedStatement = session.prepare(cql);
			session.execute(preparedStatement.bind(nickname, username, password));

		}
	}
	
	//로그인
	@Override
	public MemberInfo login(MemberInfo m) {
		Path configPath = Paths.get("c:/keys/keyspace/application.conf");
		DriverConfigLoader loader = DriverConfigLoader.fromPath(configPath);
		String username = m.getUsername();
		String password = m.getPassword();

		try (CqlSession session = CqlSession.builder().withConfigLoader(loader).build()) {

			String cql = "select username, password from member.info where username =? ";
			PreparedStatement preparedStatement = session.prepare(cql);
			ResultSet resultSet = session.execute(preparedStatement.bind(username));

			String username_db = null;
			String password_db = null;

			// Process the results
			for (Row row : resultSet) {
				// Access the columns in the result set using row.getXXX() methods
				username_db = row.getString("username");
				password_db = row.getString("password");
			}

			if (username_db.equals(username) && password_db.equals(password)) {
				System.out.println("로그인 완료");
				return m;
			} else {
				System.out.println("실패");
				System.out.println(username_db);
				System.out.println(password_db);
				return m;
			}

		}
	}
	
	// 회원가입 후, 추가정보 넣는 메서드
	@Override
	public MemberInfo InsertMemberInfoAdditional(MemberInfo m, String username_session) {
		// TODO Auto-generated method stub
		Path configPath = Paths.get("c:/keys/keyspace/application.conf");
		DriverConfigLoader loader = DriverConfigLoader.fromPath(configPath);
		
		try (CqlSession session = CqlSession.builder().withConfigLoader(loader).build()) {

			String cql = """
					update member.info
					set age =?,phone=?,address=?,interest=?,mbti=?,
					    sport=?,smoking=?,drinking=?,job=?,school=?,
					    role=?,aboutme=?
					where username = ?;
					""";
			PreparedStatement preparedStatement = session.prepare(cql);

			session.execute(preparedStatement.bind(m.getAge(), m.getPhone(), m.getAddress(),
					m.getInterest(), m.getMbti(), m.getSport(), m.getSmoking(), m.getDrinking(),
					m.getJob(), m.getSchool(), m.getRole(), m.getAboutme(),username_session));
			System.out.println(m);

		}
		return m;
	}
	
		// 데이터베이스에 사진 있는지 확인하는 메서드
		@Override
		public  Map<Integer, String> selectMemPhoto(String username_session) {
			Path configPath = Paths.get("c:/keys/keyspace/application.conf");
			DriverConfigLoader loader = DriverConfigLoader.fromPath(configPath);
			
			//DB에서 받아오는 photo를 담아올 photomap 변수 선언 및 초기화
			Map<Integer, String> photoMap = new HashMap<>(); 
			
			try (CqlSession session = CqlSession.builder().withConfigLoader(loader).build()) {
				String cql = "select photo from member.info where username =? ";
				PreparedStatement preparedStatement = session.prepare(cql);
				ResultSet resultSet = session.execute(preparedStatement.bind(username_session));
				for(Row row : resultSet) {
					photoMap = row.getMap("photo", Integer.class, String.class);
					if(photoMap!=null) {
						photoMap.putAll(photoMap);
					}
				}
		        } catch (Exception e) {
		            e.printStackTrace(); 
		            return Collections.emptyMap(); // 비어있는 photoMap 반환
		        }
			return photoMap;
	}
		@Override
		public void fileUpload(Map<Integer, String> additionalFile, String username_session) {
			Path configPath = Paths.get("c:/keys/keyspace/application.conf");
			DriverConfigLoader loader = DriverConfigLoader.fromPath(configPath);
			
			try (CqlSession session = CqlSession.builder().withConfigLoader(loader).build()) {

				String cql = """
						update member.info
						set photo=?
						where username = ?;
						""";
				PreparedStatement preparedStatement = session.prepare(cql);
				session.execute(preparedStatement.bind(additionalFile,username_session));
			}
		}
		
		
		//전체 데이터 불러올 수 있음.(세션 값 있어야함)
		@Override
	    public MemberInfo SelectMemberInfo(String username_session) {
			Path configPath = Paths.get("c:/keys/keyspace/application.conf");
			DriverConfigLoader loader = DriverConfigLoader.fromPath(configPath);
			
			try (CqlSession session = CqlSession.builder().withConfigLoader(loader).build()) {
				System.out.println("SelectMemberInfo 서비스에 들어왔음.");
				String cql = "SELECT * FROM member.info WHERE username = ?";
				PreparedStatement preparedStatement = session.prepare(cql);
				ResultSet resultSet = session.execute(preparedStatement.bind(username_session));
				
				if (resultSet.one() != null) {
					System.out.println("SelectMemberInfo 서비스에 row에 들어왔음.");
					Row row = resultSet.one();
					MemberInfo m = new MemberInfo();
	                m.setUsername(row.getString("username"));
	                m.setPassword(row.getString("password"));
	                m.setNickname(row.getString("nickname"));
		            m.setPhone(row.getString("phone"));
		            m.setAge(row.getInt("age"));
		            m.setInterest(row.getString("interest"));
		            m.setMbti(row.getString("mbti"));
		            m.setSport(row.getString("sport"));
		            m.setDrinking(row.getString("drinking"));
		            m.setSmoking(row.getString("smoking"));
		            m.setJob(row.getString("job"));
		            m.setSchool(row.getString("school"));
		            m.setAboutme(row.getString("aboutme"));
		            return m;
		            } else {
		            	// 적절한 예외 처리 또는 null 반환
		            	return null;
		            	}
				} catch (Exception e) {
	             // 예외 처리 로직
					return null;
					}
			}

		
		
		}
			

/*
 * @Override public void InsertMemberInfo(String username) { // TODO
 * Auto-generated method stub
 * 
 * Path configPath = Paths.get("c:/keys/keyspace/application.conf");
 * DriverConfigLoader loader = DriverConfigLoader.fromPath(configPath); //Use
 * DriverConfigLoader to load your configuration file try (CqlSession session =
 * CqlSession.builder() .withConfigLoader(loader) .build()) {
 * 
 * 
 * String cql = "insert into member.info (username) values (?)";
 * PreparedStatement preparedStatement = session.prepare(cql);
 * session.execute(preparedStatement.bind(username));
 * 
 * }
 * 
 * }
 */
