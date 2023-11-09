package kr.spring.service;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.stereotype.Service;
import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.config.DriverConfigLoader;
import com.datastax.oss.driver.api.core.cql.BoundStatement;
import com.datastax.oss.driver.api.core.cql.PreparedStatement;
import com.datastax.oss.driver.api.core.cql.ResultSet;
import com.datastax.oss.driver.api.core.cql.Row;

import kr.spring.entity.MemberInfo;

@Service
public class MemberInfoServiceImpl implements MemberInfoService{

	@Override
	public void InsertMemberInfo(String nickname, String username, String password) {
		Path configPath = Paths.get("c:/keys/keyspace/application.conf");
        DriverConfigLoader loader = DriverConfigLoader.fromPath(configPath);
        
        try (CqlSession session = CqlSession.builder()
                .withConfigLoader(loader)
                .build()) {
        	
				String cql = "insert into member.info ( nickname, username, password) values (?,?,?)";
				PreparedStatement preparedStatement = session.prepare(cql);
				session.execute(preparedStatement.bind( nickname, username, password));
			
	} }

	@Override
	public int SelectMemberInfo(String username, String password) {
		Path configPath = Paths.get("c:/keys/keyspace/application.conf");
        DriverConfigLoader loader = DriverConfigLoader.fromPath(configPath);
        
        try (CqlSession session = CqlSession.builder()
                .withConfigLoader(loader)
                .build()) {
        	
				String cql = "select username, password from member.info where username =? ";
				PreparedStatement preparedStatement = session.prepare(cql);				
				ResultSet resultSet = session.execute(preparedStatement.bind(username));
				
				String username_db = null;
				String password_db= null;
                // Process the results
                for (Row row : resultSet) {
                    // Access the columns in the result set using row.getXXX() methods
                    username_db = row.getString("username");
                    password_db = row.getString("password"); }
                
                if (username_db.equals(username) && password_db.equals(password)) {
                	System.out.println("로그인 완료");
                	return 1;
                }else {
                	System.out.println("실패");
                	System.out.println(username_db);
                	System.out.println(password_db);
                	return 0;
                }
                    
                 
                }
	}
}

//	  @Override
//	  public void InsertMemberInfoAdditional(MemberInfo info) { // TODO
//		  Auto-generated method stub Path configPath =
//		  Paths.get("c:/keys/keyspace/application.conf"); DriverConfigLoader loader =
//		  DriverConfigLoader.fromPath(configPath);
//		  
//		  try (CqlSession session = CqlSession.builder() .withConfigLoader(loader)
//		  .build()) {
//		  
//		  String cql = "insert into member.info () values (?,?,?,?,?,?,?,?,?,?,?,?)";
//		  PreparedStatement preparedStatement = session.prepare(cql); BoundStatement
//		  boundStatement = preparedStatement.bind( info.getField1(), info.getField2(),
//		  // 나머지 필드에 대한 바인딩 );
//		  
//		  
//		  session.execute(preparedStatement.bind(info)); System.out.println(info);
//		  
//		  } } }
 

	
	

	
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
	

