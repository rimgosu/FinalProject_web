package kr.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartRequest;

import com.datastax.oss.driver.api.core.session.Request;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import kr.spring.entity.ChatRoom;
import kr.spring.entity.MemberInfo;
import kr.spring.service.ChatService;
import kr.spring.service.MemberInfoService;

@Controller
public class ChatController {
	
	@Autowired
	private ChatService chatService;
	
	@GetMapping("/chat")
	public String showChatPage() {
		System.out.println("채팅으로 들어왔음.");
		return "/chat/chat";
	}

	@GetMapping("/showChatRoom")
	public String showChatRoom(Model model) {
		System.out.println("채팅방 보여주기 컨트롤러로 들어왔음.");
		
		List<ChatRoom> chatRooms = chatService.findAllChatRooms();
		model.addAttribute("chatRooms", chatRooms);
		
		return "/chat/showChatRoom";
	}
	
	
	
}
