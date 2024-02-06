package com.springboot.socialapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.socialapp.models.Comment;
import com.springboot.socialapp.models.User;
import com.springboot.socialapp.services.CommentService;
import com.springboot.socialapp.services.UserService;

@RestController
public class CommentController {

	@Autowired
	private CommentService commentService;
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/api/comments/post/{postId}")
	public Comment createComment(@RequestBody Comment comment, @RequestHeader("Authorization") String jwt,@PathVariable("postId") Integer postId) throws Exception {

		User user= userService.findUserByJwt(jwt);
		Comment createdComment=commentService.createComment(comment, postId, user.getId());

		return createdComment;
	}
	
	@PutMapping("/api/comments/like/{commentId}")
	public Comment likeComment(@RequestHeader("Authorization") String jwt,@PathVariable Integer commentId) throws Exception {

		User user= userService.findUserByJwt(jwt);
		Comment likedComment=commentService.likeComment(commentId, user.getId());
				return likedComment;
	}
}
