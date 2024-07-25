package com.blogApp.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.blogApp.entity.User;
import com.blogApp.exceptions.ResourceNotFoundException;
import com.blogApp.repositories.userRepo;

@Service
public class CustomUserDetailService implements UserDetailsService{

	@Autowired
	private userRepo userRepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	
		// loading user from database by username
		
		User user = this.userRepo.findByEmail(username).orElseThrow(()->new ResourceNotFoundException("user", "email: "+username, 0));
		
		return new CustomUserDetail(user);
	}

	
	
}
