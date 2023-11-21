package kr.spring.messagingstompwebsocket;

import java.time.Instant;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

import kr.spring.entity.Chatting;
import kr.spring.service.ChatService;

@Controller
public class GreetingController {
	
	@Autowired
	ChatService chatService;

	/*
	 * "/app/hello" : 클라이언트 -> 서버 메시지 받음.
	 * "/topic/greetings" : 서버 -> 구독하는 모든 클라이언트에게 전송.
	 */
	
	@MessageMapping("/hello")
	@SendTo("/topic/greetings")
	public Greeting greeting(Chatting chatting) throws Exception {
		System.out.println("[GreetingController][@MessageMapping(\"/hello\")][chatting]"+ chatting.toString());
		
		chatting.setChat_uuid(UUID.randomUUID());
		chatting.setChatted_at(Instant.now());
		
		chatService.insertChatting(chatting);
		
		Thread.sleep(1000);
		// Greeting 객체를 만들고 반환함
		return new Greeting(HtmlUtils.htmlEscape(chatting.getChat_content()));
	}

}
