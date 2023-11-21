package kr.spring.controller;


import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpSession;
import kr.spring.entity.Chatting;
import kr.spring.service.ChatService;

@RestController
public class ChatRestController {
	
	@Autowired
	ChatService chatService;
	
	
	@PostMapping("/CreateChatting")
    public void createChatting(@RequestBody Chatting chatting, HttpSession session) {
        // 채팅방 생성 로직
		System.out.print("채팅 생성 컨트롤러로 들어옴. ");
		chatService.createChatting(chatting, session);
    }
	
	@GetMapping("/GetChatting")
    public List<Chatting> getChatting(@RequestParam UUID room_uuid) {
		
		System.out.println("[ ChatRestController ] [ getChatting ] [ room_uuid : " + room_uuid + "]");
		List<Chatting> chattings = chatService.getChattings(room_uuid);
		System.out.println("chattings[0] : " + chattings);
		
        return chattings; // 채팅 목록 반환
    }
	
	

}
