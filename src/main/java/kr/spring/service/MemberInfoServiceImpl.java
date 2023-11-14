package kr.spring.service;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.config.DriverConfigLoader;

import com.datastax.oss.driver.api.core.cql.PreparedStatement;
import com.datastax.oss.driver.api.core.cql.ResultSet;
import com.datastax.oss.driver.api.core.cql.Row;

import kr.spring.entity.Info;
import kr.spring.entity.MemberInfo;

@Service
public class MemberInfoServiceImpl implements MemberInfoService {
	
	@Autowired
	DBService dbservice;

	@Override
	public void InsertMemberInfo(String nickname, String username, String password) {

		Info info = new Info();
		info.setNickname(nickname);
		info.setUsername(username);
		info.setPassword(password);
		
		
		
		DriverConfigLoader loader = dbservice.getConnection();
		
		dbservice.save(loader, Info.class, info);
		
		
    }


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
      }
   }

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