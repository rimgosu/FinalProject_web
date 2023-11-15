package kr.spring.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpSession;
import kr.spring.entity.ChatRoom;
import kr.spring.entity.Chatting;
import kr.spring.service.ChatService;

@RestController
public class ChatRestController {
	
	@Autowired
	ChatService chatService;
	
	
	@PostMapping("/CreateChatRoom")
    public void createChatRoom(@RequestBody ChatRoom chatRoom, HttpSession session) {
        // 채팅방 생성 로직
		System.out.println("채팅방 생성 컨트롤러로 들어옴.");
		chatService.createChatRoom(chatRoom, session);
    }
	
	@PostMapping("/CreateChatting")
    public void createChatting(@RequestBody Chatting chatting, HttpSession session) {
        // 채팅방 생성 로직
		System.out.print("채팅 생성 컨트롤러로 들어옴. ");
		chatService.createChatting(chatting, session);
    }
	
	

}
