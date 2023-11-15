package kr.spring.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartRequest;

import com.datastax.oss.driver.api.core.config.DriverConfigLoader;
import com.datastax.oss.driver.api.core.session.Request;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import kr.spring.entity.ChatRoom;
import kr.spring.entity.Info;
import kr.spring.service.ChatService;
import kr.spring.service.DBService;
import kr.spring.service.InfoService;

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
	public String showChatRoom(Model model, HttpSession session, @RequestParam UUID room_uuid) {
		System.out.println("채팅방 보여주기 컨트롤러로 들어왔음.");
		
		Info info = (Info) session.getAttribute("mvo");
		
		Map<String, Object> columnValues = new HashMap<>();
		columnValues.put("room_maker", info.getUsername());
		columnValues.put("room_uuid", room_uuid);
		
		DriverConfigLoader loader = dbService.getConnection();
		List<ChatRoom> chatRooms = dbService.findAllByColumnValues(loader, ChatRoom.class, columnValues);
		
		model.addAttribute("chatRooms", chatRooms);
		
		return "/chat/showChatRoom";
	}
	
	
	
}
