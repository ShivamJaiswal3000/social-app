package com.springboot.socialapp.services;

import java.util.List;

import com.springboot.socialapp.models.Story;
import com.springboot.socialapp.models.User;

public interface StoryService {

	public Story createStory(Story story, User user);
	
	public List<Story>findStoryByUserId(Integer userId) throws Exception;
}
