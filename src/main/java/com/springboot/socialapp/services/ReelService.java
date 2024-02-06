package com.springboot.socialapp.services;

import java.util.List;

import com.springboot.socialapp.models.Reels;
import com.springboot.socialapp.models.User;

public interface ReelService {

	public Reels createReel(Reels reel,Integer userId)throws Exception;
	
	public List<Reels> findAllReels();
	
	public List<Reels> findUserReel(Integer userId) throws Exception;
}
