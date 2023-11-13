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
		if (mvo != null) {
			session.setAttribute("mvo", mvo);
			System.out.println(mvo.getUsername());
			
			/* 로그인할 떄 세션에 값이 들어오는지 테스트 한 코드
			 * String username1 = (String)session.getAttribute("username");
			 * System.out.println(username1);
			 */
			
			// 전체 memberinfo불러올때 테스트 한 코드 
			// 
			 String username_session =((MemberInfo)session.getAttribute("mvo")).getUsername();			 
			 MemberInfo mvo1 = memberInfoService.SelectMemberInfo(username_session);
			 session.setAttribute("mvo1", mvo1);
			 			
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
	public String showInfoPage(MemberInfo info, @RequestParam("file") MultipartFile file, HttpSession session, HttpServletRequest request) {
		//파일 업로드를 할 수 있게 도와주는 MultipartRequest. 
		//저장경로 
		String savePath =request.getServletContext().getRealPath("resources/upload"); 
//		int fileMaxSize = 10*1024*1024; //파일 최대 크기 
		String username_session = ((MemberInfo)session.getAttribute("mvo")).getUsername();		
			    
		    try {
		    	String uploadedFilePath = null;
		    	//업로드된 파일 처리
		    	if(!file.isEmpty()) {
		    		String originalFilename = file.getOriginalFilename();
		    		//파일 저장 경로 및 이름 설정 (File.separator는 경로 구분자/를 구분해줌)
		    		String filePath = savePath+ File.separator + originalFilename;
		    		File dest = new File(filePath);
		    		//파일 저장
		    		file.transferTo(dest);
		    		//파일 경로 저장
		    		uploadedFilePath = filePath;
		    	}
		    	//Map에 파일 경로 추가
		    	Map<Integer, String> additionalInfo = new HashMap<>();

		    	//database에서 기존에 사진 정보 유무 조회
				  Map<Integer, String> fotosDb = memberInfoService.selectMemPhoto(username_session);
				  // Map에서 key와 value를 분리하여 사용할 List들
				  List<Integer> photoNums = new ArrayList<>(fotosDb.keySet());
				  List<String> photoPaths = new ArrayList<>(fotosDb.values());
				  int count=0;
				  for (int i=0; i< photoPaths.size(); i++) {
					  count += 1;
				  }
		    
				  int photoNum;
				  String photoPath;
				 

				    if (!photoNums.isEmpty() && !photoPaths.isEmpty()) {
				        photoNum = count+1;
				        }else {
				        	photoNum = 0;
				        	photoPath = null;
				        }
				    additionalInfo.put(photoNum, uploadedFilePath);
				    //MemberInfo객체에 Map 추가
				    info.setPhoto(additionalInfo);
		 
		    	
		    }catch(IOException e) {
		    	e.printStackTrace();
		    }	  
		        
		  MemberInfo mvo = memberInfoService.InsertMemberInfoAdditional(info,username_session);
		

		return "redirect:/index";
	
	
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
