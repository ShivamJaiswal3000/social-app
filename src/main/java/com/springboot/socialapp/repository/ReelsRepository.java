package com.springboot.socialapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.socialapp.models.Reels;

public interface ReelsRepository extends JpaRepository<Reels, Integer> {

	public List<Reels> findByUserId(Integer userId);
}
