package com.springboot.socialapp.controller;
import org.apache.catalina.authenticator.SpnegoAuthenticator.AuthenticateAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.socialapp.config.JwtProvider;
import com.springboot.socialapp.models.User;
import com.springboot.socialapp.repository.UserRepository;
import com.springboot.socialapp.request.LoginRequest;
import com.springboot.socialapp.response.AuthResponse;
import com.springboot.socialapp.services.CustomUserDetailsService;
import com.springboot.socialapp.services.UserService;

@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private CustomUserDetailsService customUserDetails;
	
	@PostMapping("/signup")
	public AuthResponse createUser(@RequestBody User user) throws Exception {
		
		User isExist=userRepository.findByEmail(user.getEmail());
		if(isExist!=null) {
			throw new Exception("this email is already used with another account");
		}
		
		User newUser = new User();
		
		newUser.setEmail(user.getEmail());
		newUser.setfName(user.getfName());
		newUser.setlName(user.getlName());
        newUser.setGender(user.getGender());
		newUser.setPassword(passwordEncoder.encode(user.getPassword()));
		
		User savedUser=userRepository.save(newUser);
		
		Authentication authentication=new UsernamePasswordAuthenticationToken(savedUser.getEmail(), savedUser.getPassword());
		
		String token=JwtProvider.generateToken(authentication);
		
		AuthResponse res= new AuthResponse(token,"Register Success");
		
		return res ;
	}
	@PostMapping("/signin")
	public AuthResponse signin(@RequestBody LoginRequest loginRequest) {
		try {
			Authentication authentication=authenticate(loginRequest.getEmail(), loginRequest.getPassword());
	        
			String token=JwtProvider.generateToken(authentication);
			
			AuthResponse res= new AuthResponse(token,"Login Success");
			
			return res ;
		}
		catch(Exception e){
			System.out.println("error "+e);
			String token=null;
			AuthResponse res= new AuthResponse(token,"Login failure");

			return res;
		}
		}
	private Authentication authenticate(String email, String password) {

		UserDetails userDetails=customUserDetails.loadUserByUsername(email);
		if(userDetails==null) {
			throw new BadCredentialsException("invalid username");
		}
		if(!passwordEncoder.matches(password, userDetails.getPassword())) {
			throw new BadCredentialsException("password not matched");
		}
		return new UsernamePasswordAuthenticationToken(userDetails,null, userDetails.getAuthorities());
	}
}
