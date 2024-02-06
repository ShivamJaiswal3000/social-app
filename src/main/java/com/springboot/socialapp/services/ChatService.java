package com.springboot.socialapp.services;

import java.util.List;

import com.springboot.socialapp.models.Chat;
import com.springboot.socialapp.models.User;

public interface ChatService {

	public Chat createChat(User reqUser, User user2);
	
	public Chat findChatById(Integer chatId) throws Exception;
	
	public List<Chat> findUsersChat(Integer userId);
}
