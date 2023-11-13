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
					m.getJob(), m.getSchool(), m.getRole(), m.getAboutme(), username_session));

			System.out.println(m);

		}
		return m;
	}
	
		// 데이터베이스에 사진 있는지 확인하는 메서드
		@Override
		public  Map<Integer, String> selectMemPhoto(String username_session) {
			Path configPath = Paths.get("c:/keys/keyspace/application.conf");
			DriverConfigLoader loader = DriverConfigLoader.fromPath(configPath);
	
			try (CqlSession session = CqlSession.builder().withConfigLoader(loader).build()) {
	
				String cql = "select photo from member.info where username =? ";
				PreparedStatement preparedStatement = session.prepare(cql);
				ResultSet resultSet = session.execute(preparedStatement.bind(username_session));
				 Map<Integer, String> photoMap = new HashMap<>();
		            for (Row row : resultSet) {
		                // Access the columns in the result set using row.getXXX() methods
		                int photoNum = row.getInt("photo");
		                String photoPath = row.getString("photo");
		                photoMap.put(photoNum, photoPath);
		             
		            }
		            return photoMap;
		        } catch (Exception e) {
		            e.printStackTrace(); // Handle the exception appropriately in your application
		            return Collections.emptyMap(); // Return an empty map in case of an exception
		        }
	}
		
		//전체 데이터 불러올 수 있음.(세션 값 있어야함)
		@Override
		public MemberInfo SelectMemberInfo(String username_session) {
			Path configPath = Paths.get("c:/keys/keyspace/application.conf");
			DriverConfigLoader loader = DriverConfigLoader.fromPath(configPath);
			MemberInfo m = new MemberInfo();
			
			try (CqlSession session = CqlSession.builder().withConfigLoader(loader).build()) {

				String cql = "select * from member.info where username =? ";
				PreparedStatement preparedStatement = session.prepare(cql);
				ResultSet resultSet = session.execute(preparedStatement.bind(username_session));

				String usernameDb = null;
				String passwordDb = null;
				String nicknameDb = null;
				String phoneDb = null;
				int ageDb = 0;
				String interestDb = null;
				String mbtiDb = null;
				String sportDb = null;
				String drinkingDb = null;
				String smokingDb = null;
				String jobDb = null;
				String schoolDb = null;
				String aboutmeDb = null;
				String registerDateDb = null;
				String roleDb = null;
				String addressDb = null;
				String photoDb = null;

				// Process the results
				for (Row row : resultSet) {
					// Access the columns in the result set using row.getXXX() methods
					usernameDb = row.getString("username");
					passwordDb = row.getString("password");
					nicknameDb = row.getString("nickname");
					phoneDb = row.getString("phone");
					ageDb = row.getInt("age");
					interestDb = row.getString("interest");
					mbtiDb = row.getString("mbti");
					sportDb = row.getString("sport");
					drinkingDb = row.getString("drinking");
					smokingDb = row.getString("smoking");
					jobDb = row.getString("job");
					schoolDb = row.getString("school");
					aboutmeDb = row.getString("aboutme");

					
					
				}
				m.setPassword(passwordDb);
				m.setNickname(nicknameDb);
				m.setPhone(phoneDb);
				m.setAge(ageDb);
				m.setInterest(interestDb);
				m.setMbti(mbtiDb);
				m.setSport(sportDb);
				m.setDrinking(drinkingDb);
				m.setSmoking(smokingDb);
				m.setJob(jobDb);
				m.setSchool(schoolDb);
				

				
			
				
				return m;

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
