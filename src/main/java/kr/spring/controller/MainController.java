package kr.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;
import kr.spring.entity.MemberInfo;
import kr.spring.service.MemberInfoService;

@Controller
public class MainController {
	
	@Autowired
	private MemberInfoService memberInfoService;
	
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
	
	@GetMapping("/chat")
	public String showChatPage() {
		System.out.println("채팅으로 들어왔음.");
		return "chat";
	}
	
	@GetMapping("/login")
	public String showLoginPage() {
		System.out.println("로그인으로 들어왔음.");
		return "login";
	}
	@PostMapping("/login")
	public String showLoginPage(@RequestParam("username") String username, @RequestParam("password") String password, HttpSession session) {
		int mvo = memberInfoService.SelectMemberInfo(username, password);
		if (mvo ==1) {
			session.setAttribute("mvo", mvo);
			return "redirect:/index";
		}else {
			return "redirect:/login";
		}
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
	public String showInfoPage(MemberInfo info) {
		/* memberInfoService.InsertMemberInfoAdditional(info); */
		
		return "redirect:/main";
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