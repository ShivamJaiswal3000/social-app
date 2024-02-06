package com.springboot.socialapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.socialapp.models.Post;
import com.springboot.socialapp.models.Reels;
import com.springboot.socialapp.models.User;
import com.springboot.socialapp.services.ReelService;
import com.springboot.socialapp.services.UserService;

@RestController
public class ReelsController {

	@Autowired
	private ReelService reelService;
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/api/reels")
	public Reels createReels(@RequestBody Reels reel, @RequestHeader("Authorization") String jwt) throws Exception {
	
		User reqUser= userService.findUserByJwt(jwt);
		Reels createdReels= reelService.createReel(reel, reqUser.getId());
		return createdReels;
	}
	
	@GetMapping("/api/reels")
	public List<Reels> findAllReels() {
		List<Reels> reels= reelService.findAllReels();
		return reels;
	}
	
	@GetMapping("/api/reels/user/{userId}")
	public List<Reels> findUserReels(@PathVariable Integer userId) throws Exception {
		List<Reels> reels= reelService.findUserReel(userId);
		return reels;
	}
}
