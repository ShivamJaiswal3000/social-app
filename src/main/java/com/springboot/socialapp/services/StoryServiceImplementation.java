package com.springboot.socialapp.services;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.socialapp.models.Story;
import com.springboot.socialapp.models.User;
import com.springboot.socialapp.repository.StoryRepository;
@Service
public class StoryServiceImplementation implements StoryService {
	
	@Autowired
	private StoryRepository storyRepository;
	
	@Autowired
	private UserService userService;

	@Override
	public Story createStory(Story story, User user) {
		Story createdStory= new Story();
		createdStory.setCaptions(story.getCaptions());
		createdStory.setImage(story.getImage());
		createdStory.setVideo(story.getVideo());
		createdStory.setUser(user);
		createdStory.setTimestamp(LocalDateTime.now());
		return storyRepository.save(createdStory);
	}

	@Override
	public List<Story> findStoryByUserId(Integer userId)throws Exception {
		User user= userService.findUserById(userId);
		return storyRepository.findByUserId(userId);
	}

}
