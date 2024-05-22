package com.springboot.socialapp.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.socialapp.config.JwtProvider;
import com.springboot.socialapp.exception.UserException;
import com.springboot.socialapp.models.User;
import com.springboot.socialapp.repository.UserRepository;
@Service
public class UserServiceImplementation implements UserService {

	@Autowired
	UserRepository userRepository;
	
	@Override
	public User registerUser(User user) {
		User newUser = new User();
		newUser.setEmail(user.getEmail());
		newUser.setfName(user.getfName());
		newUser.setlName(user.getlName());
		newUser.setPassword(user.getPassword());
		newUser.setId(user.getId());
		
		User savedUser=userRepository.save(newUser);
		return savedUser;
	}

	@Override
	public User findUserById(Integer userid) throws UserException {
		Optional<User> user=userRepository.findById(userid);
		
		if(user.isPresent()) {
			return user.get();
		}
		throw new UserException("user not exist with userid : "+userid);

	}

	@Override
	public User findUserByEmail(String email) {
		User user=userRepository.findByEmail(email);
		return user;
	}

	@Override
	public User followUser(Integer reqUserId, Integer userId2) throws UserException {
		User reqUser= findUserById(reqUserId);
		
		User user2= findUserById(userId2);
		
		   // Check if reqUser is already following user2
	    if (reqUser.getFollowings().contains(user2.getId()) && user2.getFollowers().contains(reqUser.getId())) {
	        // If already following, remove the entries
	        reqUser.getFollowings().remove(user2.getId());
	        user2.getFollowers().remove(reqUser.getId());
	    } else {
	        // If not already following, add the entries
	        user2.getFollowers().add(reqUser.getId());
	        reqUser.getFollowings().add(user2.getId());
	    }
		
		userRepository.save(reqUser);
		userRepository.save(user2);
		
		return reqUser;
	}

	@Override
	public User updateUser(User user, Integer userId) throws UserException {
		Optional<User> user1=userRepository.findById(userId);
		if(user1.isEmpty()) {
			throw new UserException("user not exist with id"+userId);
		}
		User oldUser=user1.get();
		
		if(user.getfName()!=null) {
			oldUser.setfName(user.getfName());
		}
		if(user.getlName()!=null) {
			oldUser.setlName(user.getlName());
		}
		if(user.getEmail()!=null) {
			oldUser.setEmail(user.getEmail());
		}
		if(user.getGender()!=null) {
			oldUser.setGender(user.getGender());
		}
		if(user.getBio()!=null) {
			oldUser.setBio(user.getBio());
		}
		if(user.getPfp()!=null) {
			oldUser.setPfp(user.getPfp());
		}
		if(user.getCoverPic()!=null) {
			oldUser.setCoverPic(user.getCoverPic());
		}
		User updateUser= userRepository.save(oldUser);
		
		
		return updateUser;
	}

	@Override
	public List<User> searchUser(String query) {
		
		return userRepository.searchUser(query);
	}

	@Override
	public User findUserByJwt(String jwt) {
		String email= JwtProvider.getEmailFromJwtToken(jwt);
		User user= userRepository.findByEmail(email);
		return user;
	}

}
