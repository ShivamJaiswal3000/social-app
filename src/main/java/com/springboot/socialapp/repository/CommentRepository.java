package com.springboot.socialapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.socialapp.models.Comment;

public interface CommentRepository extends JpaRepository<Comment, Integer>{

}
