package kr.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import jakarta.servlet.http.HttpSession;
import kr.spring.entity.ChatRoomNotification;
import kr.spring.entity.Info;
import kr.spring.service.ChatService;
import kr.spring.service.DBService;

@Controller
public class ChatController {
	
	@Autowired
	private ChatService chatService;
	@Autowired
	private DBService dbService;
	
	@GetMapping("/chat")
	public String showChatPage() {
		System.out.println("채팅으로 들어왔음.");
		return "/chat/chat";
	}

	@GetMapping("/showChatRoom")
	public String showChatRoom(Model model, HttpSession session) {
		System.out.println("[ChatController][@GetMapping][/showChatRoom]");
		
		Info info = (Info) session.getAttribute("mvo");
		List<ChatRoomNotification> chatRoomNotifications = chatService.getChatRoomInteractions(info.getUsername());
		model.addAttribute("chatRoomNotifications", chatRoomNotifications);
		
		return "/chat/showChatRoom";
	}
	
	
	
}
