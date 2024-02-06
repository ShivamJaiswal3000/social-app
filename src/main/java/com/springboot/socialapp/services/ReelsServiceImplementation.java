package com.springboot.socialapp.services;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.socialapp.models.Post;
import com.springboot.socialapp.models.Reels;
import com.springboot.socialapp.models.User;
import com.springboot.socialapp.repository.ReelsRepository;

@Service
public class ReelsServiceImplementation implements ReelService {

	@Autowired
	private ReelsRepository reelsRepository;
	
	@Autowired
	private UserService userService;
	
	@Override
	public Reels createReel(Reels reel, Integer userId) throws Exception {
		
		User user=userService.findUserById(userId);
		
    Reels newReel=new Reels();
    newReel.setTitle(reel.getTitle());
    newReel.setVideo(reel.getVideo());
    newReel.setUser(user);
    
		return reelsRepository.save(newReel);
//		Reels createReel= new Reels();
//		createReel.setTitle(reel.getTitle());
//		createReel.setUser(reel.getUser());
//		createReel.setVideo(reel.getVideo());
//		
//		return reelsRepository.save(createReel);
	}

	@Override
	public List<Reels> findAllReels() {
		
		return reelsRepository.findAll();
	}

	@Override
	public List<Reels> findUserReel(Integer userId)throws Exception {
		
		userService.findUserById(userId);
		
		return reelsRepository.findByUserId(userId);
	}

}
