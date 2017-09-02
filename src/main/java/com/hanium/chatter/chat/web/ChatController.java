package com.hanium.chatter.chat.web;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import com.hanium.chatter.chat.domain.SendMessage;

@Controller
public class ChatController {
	private SimpMessagingTemplate messagingTemplate; 

    @Autowired
    public void setMessagingTemplate(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }
	    
	@MessageMapping("hello")
	public void hello(SendMessage message) throws Exception {
		Thread.sleep(100);
		message.setSendDate(new Date());
		messagingTemplate.convertAndSend("/chat/hello/" + message.getRoomName(), message);
	}
	
	@MessageMapping("bye")
	public void bye(SendMessage message) throws Exception {
		Thread.sleep(100);
		message.setSendDate(new Date());
		messagingTemplate.convertAndSend("/chat/bye/" + message.getRoomName(), message);
	}
	
	@MessageMapping("detail")
	public void detail(SendMessage message) throws Exception {
		Thread.sleep(100);
		message.setSendDate(new Date());
		messagingTemplate.convertAndSend("/chat/detail/" + message.getRoomName(), message);
	}
}
