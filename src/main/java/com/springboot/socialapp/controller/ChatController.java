package com.springboot.socialapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.socialapp.models.Chat;
import com.springboot.socialapp.models.User;
import com.springboot.socialapp.request.CreateChatRequest;
import com.springboot.socialapp.services.ChatService;
import com.springboot.socialapp.services.UserService;

@RestController
public class ChatController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private ChatService chatService;
	
	@PostMapping("/api/chats")
	public Chat createChat(@RequestHeader("Authorization") String jwt, 
			@RequestBody CreateChatRequest req) throws Exception {
		User reqUser= userService.findUserByJwt(jwt);
		User user2= userService.findUserById(req.getUserId());
		Chat chat = chatService.createChat(reqUser, user2);
		return chat;
	}
	
	@GetMapping("/api/chats")
	public List<Chat> findUsersChat(@RequestHeader("Authorization") String jwt) {
		User user= userService.findUserByJwt(jwt);
		List<Chat> chats = chatService.findUsersChat(user.getId());
		return chats;
	}
}
