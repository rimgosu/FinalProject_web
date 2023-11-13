package kr.spring.service;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartRequest;

import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.config.DriverConfigLoader;
import com.datastax.oss.driver.api.core.cql.BoundStatement;
import com.datastax.oss.driver.api.core.cql.PreparedStatement;
import com.datastax.oss.driver.api.core.cql.ResultSet;
import com.datastax.oss.driver.api.core.cql.Row;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
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
           
            String cql = """
                  insert into member.info ( 
                     nickname, username, password, register_date
                  ) values (
                     ?, ?, ?, toTimestamp(now())
                  )""";
            PreparedStatement preparedStatement = session.prepare(cql);
            session.execute(preparedStatement.bind( nickname, username, password));
         
   } }

   @Override
   public MemberInfo SelectMemberInfo(MemberInfo m) {
      Path configPath = Paths.get("c:/keys/keyspace/application.conf");
        DriverConfigLoader loader = DriverConfigLoader.fromPath(configPath);
        String username = m.getUsername();
        String password = m.getPassword();
        
        
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
                   return m;
                }else {
                   System.out.println("실패");
                   System.out.println(username_db);
                   System.out.println(password_db);
                   return m;
                }
                    
                 
                }
   }

   @Override
   public void InsertMemberInfoAdditional(MemberInfo info, String username_session) {
      // TODO Auto-generated method stub
      Path configPath = Paths.get("c:/keys/keyspace/application.conf");
        DriverConfigLoader loader = DriverConfigLoader.fromPath(configPath);
      /*
       * //파일 업로드를 할 수 있게 도와주는 MultipartRequest 객체 생성. MultipartRequest multi = null;
       * int fileMaxSize = 10*1024*1024; //파일 최대 크기 //저장경로 String savePath =
       * request.getRealPath("resources/upload");
       */
        
        
        try (CqlSession session = CqlSession.builder()
                .withConfigLoader(loader)
                .build()) {
           
            String cql = """
                  update member.info 
                  set age =?,phone=?,address=?,interest=?,mbti=?,
                      sport=?,smoking=?,drinking=?,job=?,school=?,
                      role=?,aboutme=? 
                  where username = ?;
                  """;
            PreparedStatement preparedStatement = session.prepare(cql);

            session.execute(preparedStatement.bind( 
                  info.getAge(),
                    info.getPhone(),
                    info.getAddress(),
                    info.getInterest(),
                    info.getMbti(),
                    info.getSport(),
                    info.getSmoking(),
                    info.getDrinking(),
                    info.getJob(),
                    info.getSchool(),
                    info.getRole(),
                    info.getAboutme(),
                    username_session
                  ));
                  

            System.out.println(info);
         
   } }
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
   
