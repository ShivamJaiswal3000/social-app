package com.springboot.socialapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.springboot.socialapp.models.User;
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

	public User findByEmail(String email);
	
	@Query("select u from User u where u.fName LIKE %:query% OR u.lName LIKE %:query% OR u.email LIKE %:query%")
	public List<User> searchUser(@Param("query")String query);
}
