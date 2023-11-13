package kr.spring.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import kr.spring.entity.ChatRoom;
import kr.spring.service.ChatService;

@RestController
public class ChatRoomController {
	
	@Autowired
	ChatService chatService;
	
	
	@PostMapping("/CreateChatRoom")
    public void createChatRoom(@RequestBody ChatRoom chatRoom, HttpSession session) {
        // 채팅방 생성 로직
		System.out.println("채팅방 생성 컨트롤러로 들어옴");
		chatService.createChatRoom(chatRoom, session);
    }

}
