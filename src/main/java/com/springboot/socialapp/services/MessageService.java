package com.springboot.socialapp.services;

import java.util.List;

import com.springboot.socialapp.models.Message;
import com.springboot.socialapp.models.User;

public interface MessageService {

	public Message createMessage(User user, Integer chatId, Message req) throws Exception;
	public List<Message> findChatMessages(Integer chatId) throws Exception;
}
