package kr.spring.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

import com.datastax.oss.driver.api.core.session.Request;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import jakarta.servlet.http.HttpServletRequest;
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
	public String showLoginPage(MemberInfo m, HttpSession session) {
		MemberInfo mvo = memberInfoService.login(m);
		// login하면 if절로 들어옴.
		if (mvo != null) {
			session.setAttribute("mvo", mvo);
			System.out.println(mvo.getUsername());
			/*
			 * 로그인할 떄 세션에 값이 들어오는지 테스트 한 코드 String username1 =
			 * (String)session.getAttribute("username"); System.out.println(username1);
			 */

			// 전체 memberinfo불러올때 테스트 한 코드
			String username_session = ((MemberInfo) session.getAttribute("mvo")).getUsername();
			MemberInfo mvo1 = memberInfoService.SelectMemberInfo(username_session);
			session.setAttribute("mvo1", mvo1);
			return "redirect:/index";
		} else {
			return "redirect:/login";
		}
	}

	@GetMapping("/join")
	public String showJoinPage() {
		System.out.println("회원가입 들어옴.");
		return "join";
	}

	@PostMapping("/join")
	public String showJoinPage(@RequestParam("nickname") String nickname, @RequestParam("username") String username,
			@RequestParam("password") String password) {
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
		String username_session = ((MemberInfo) session.getAttribute("mvo")).getUsername();
		memberInfoService.InsertMemberInfoAdditional(info, username_session);
		return "redirect:/index";

	}
	
	//파일 업로드
	@PostMapping("/fileUpload")
	public String fileUpload(MemberInfo info, @RequestParam("file") MultipartFile file, HttpSession session, HttpServletRequest request) {
		System.out.println("사진 업로드함.");
		// 파일 업로드를 할 수 있게 도와주는 MultipartRequest.
//	    String savePath =request.getServletContext().getRealPath("/");  절대경로 찾는 코드
		String username_session = ((MemberInfo) session.getAttribute("mvo")).getUsername();
		System.out.println(username_session);
		// 추가 정보를 담을 Map선언
		Map<Integer, String> additionalFile = new HashMap<>();
		try {
			String uploadedFilePath = null;
			// 업로드된 파일 처리
			if (!file.isEmpty()) {
				String originalFilename = file.getOriginalFilename();
				// 파일 저장 경로 및 이름 설정 (File.separator는 경로 구분자/를 구분해줌) savePath+ File.separator
//				    		String relativePath ="webapp/";
				String filePath = request.getServletContext().getRealPath("/" + originalFilename);
				File dest = new File(filePath);

				// 파일 저장
				file.transferTo(dest);
				// 파일 경로에서 역슬래시 바꾸는 곳.
				System.out.println(dest);
				filePath = filePath.replace("\\\\", "/");
				uploadedFilePath = filePath.replace("\\", "/");

			}			
			// database에서 기존에 사진 정보 조회
			Map<Integer, String> photosDb = memberInfoService.selectMemPhoto(username_session);
			// 새로운 사진 정보 추가
			int nextNum = photosDb.size() + 1;
			additionalFile.put(nextNum, uploadedFilePath);
		} catch (IOException e) {
			e.printStackTrace();
		}
		memberInfoService.fileUpload(additionalFile, username_session);
		return "redirect:/index";
	}

	
	@GetMapping("/profile")
	public String showProfilePage() {
		System.out.println("마이페이지로 들어왔음.");
		return "profile";
	}

	@GetMapping("/update")
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
