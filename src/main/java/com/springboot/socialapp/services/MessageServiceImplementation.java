package com.springboot.socialapp.services;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.socialapp.models.Chat;
import com.springboot.socialapp.models.Message;
import com.springboot.socialapp.models.User;
import com.springboot.socialapp.repository.ChatRepository;
import com.springboot.socialapp.repository.MessageRepository;

@Service
public class MessageServiceImplementation implements MessageService{

	@Autowired
	private MessageRepository messageRepository;
	
	@Autowired
	private ChatRepository chatRepository;
	
	@Autowired
	private ChatService chatService;
	
	@Override
	public Message createMessage(User user, Integer chatId, Message req) throws Exception {
		Message message= new Message();
		Chat chat=chatService.findChatById(chatId);
		message.setChat(chat);
		message.setContent(req.getContent());
		message.setImage(req.getImage());
		message.setUser(user);
		message.setTimestamp(LocalDateTime.now());
		Message savedMessage= messageRepository.save(message);
		chat.getMessages().add(savedMessage);
		chatRepository.save(chat);
		return savedMessage;
	}

	@Override
	public List<Message> findChatMessages(Integer chatId) throws Exception {
		Chat chat=chatService.findChatById(chatId);
		return messageRepository.findByChatId(chatId);
	}

}
