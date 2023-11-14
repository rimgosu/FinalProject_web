package kr.spring.controller;

import java.lang.reflect.Member;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartRequest;

import com.datastax.oss.driver.api.core.config.DriverConfigLoader;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import kr.spring.entity.ChatRoom;
import kr.spring.entity.Info;
import kr.spring.entity.MemberInfo;
import kr.spring.service.DBService;
import kr.spring.service.MemberInfoService;

@Controller
public class MainController {
	
	@Autowired
	private MemberInfoService memberInfoService;
	@Autowired
	private DBService dbService;
	
	
	@GetMapping("/index")
	public String showMainPage() {
		System.out.println("main으로 들어왔음.");
		return "index";
	}
	
	@GetMapping("/recommend")
	public String showRecommendPage() {
		System.out.println("추천화면으로 들어왔음.");
		return "recommend";
	}
	
	@GetMapping("/like")
	public String showLikePage() {
		System.out.println("좋아요로 들어왔음.");
		return "like";
	}
	
	
	@GetMapping("/login")
	public String showLoginPage() {
		System.out.println("로그인으로 들어왔음.");
		return "login";
	}
	
	@PostMapping("/login")
    public String showLoginPage(Info info, HttpSession session) {
		System.out.println("로그인 페이지로 들어왔음" + info.toString());
		
		Map<String, Object> columnValues = new HashMap<>();
		columnValues.put("username", info.getUsername());
		columnValues.put("password", info.getPassword());
		
		DriverConfigLoader loader = dbService.getConnection();
		List<Info> listInfo = dbService.findAllByColumnValues(loader, Info.class, columnValues);
		
		if ( listInfo.size() == 0 ) {
			return "redirect:login";
		} else {
			session.setAttribute("mvo", listInfo.get(0));
			return "redirect:index";
		}
		
   }

	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/index";
	}
	
	@GetMapping("/join")
	public String showJoinPage() {
		System.out.println("회원가입 들어옴.");
		return "join";
	}
	
	@PostMapping("/join")
	public String showJoinPage(@RequestParam("nickname") String nickname,@RequestParam("username") String username, @RequestParam("password") String password ) {
		memberInfoService.InsertMemberInfo(nickname, username, password);
		return "redirect:/login";
	}
	
	@GetMapping("/info")
	public String showInfoPage() {
		System.out.println("정보입력으로 들어왔음.");
		return "info";
	}
	
	@PostMapping("/info")
	   public String showInfoPage(MemberInfo info, HttpSession session, HttpServletRequest request) {
	      //파일 업로드를 할 수 있게 도와주는 MultipartRequest 객체 생성. 
	      MultipartRequest multi = null;
	      //저장경로 
	      /* String savePath =request.getRealPath("resources/upload"); */
	      int fileMaxSize = 10*1024*1024; //파일 최대 크기 
	      String username_session = ((MemberInfo)session.getAttribute("mvo")).getUsername();
	      
	      MemberInfo mvo = memberInfoService.SelectMemberInfo(username_session);
	      System.out.println(mvo);
	      
	      
//	        Map<Integer, String> oldImg =
//	        memberInfoService.selectMemPhoto(username_session);
//	        
//	        MemberInfo mvo = memberInfoService.InsertMemberInfoAdditional(info,username_session);
	      

	      return "redirect:/index";
	   
	   
	   }


	
	@GetMapping("/profile")
	public String showProfilePage() {
		System.out.println("마이페이지로 들어왔음.");
		return "profile";
	}
	
	@PostMapping("/update")
	public String showUpdatePage() {
		System.out.println("수정페이지로 들어왔음.");
		return "update";
	}
	
	
	@GetMapping("/test")
	public String showTestPage() {
		System.out.println("테스트페이지로 들어옴.");
		return "test";
	}
	
	/*
	 * @GetMapping("/test2") //지협님이 하신 테스트 public String
	 * showTest2Page(@RequestParam("username") String username) {
	 * System.out.println("테스트페이지2로 들어옴.");
	 * memberInfoService.InsertMemberInfo(username);
	 * 
	 * return "test"; }
	 */
	
	
	
}
