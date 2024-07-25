package com.blogApp.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.blogApp.config.AppConstants;
import com.blogApp.entity.Role;
import com.blogApp.entity.User;
import com.blogApp.exceptions.ResourceNotFoundException;
import com.blogApp.payload.CommentDto;
import com.blogApp.payload.UserDto;
import com.blogApp.repositories.RoleRepo;
import com.blogApp.repositories.userRepo;
import com.blogApp.services.UserService;

@Service
public class UserServiceImpl  implements UserService {

	@Autowired
	private userRepo userRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private RoleRepo roleRepo;
	
	@Override
	public UserDto createUser(UserDto userDto) {
		
		User user = this.dtoToUser(userDto);
		
		User savedUser = this.userRepo.save(user);
	
		return this.userToDto(savedUser);
	}

	@Override
	public UserDto updateUser(UserDto userDto, Integer userId) {

		User user = this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","Id",userId));	
		
		user.setName(userDto.getName());
		user.setEmail(userDto.getPassword());
		user.setPassword(userDto.getPassword());
		user.setAbout(userDto.getAbout());
		
		User updatedUser = this.userRepo.save(user);
		
		return this.userToDto(updatedUser);
	}

	@Override
	public UserDto getUserById(Integer userId) {
	
		User user = this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("user", "Id", userId));

		return this.userToDto(user);
	}

	@Override
	public List<UserDto> getAllUsers() {
		
		List<User> users = this.userRepo.findAll();
		
		List<UserDto> userDtos = users.stream().map(user->this.userToDto(user)).collect(Collectors.toList());
		
		return userDtos;
	}

	@Override
	public void deleteUser(Integer userId) {
		
		  User user = this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("user", "Id", userId));
		
		  this.userRepo.delete(user);
	}
	
	
	// method for converting userDto to user || and user to userDto
	
	// userDto to user
	
	private User dtoToUser(UserDto userDto) {
		
	    User user =this.modelMapper.map(userDto, User.class); 
	    
//	    user.setId(userDto.getId());
//	    user.setName(userDto.getName());
//	    user.setEmail(userDto.getPassword());
//	    user.setPassword(userDto.getPassword());
//	    user.setAbout(userDto.getAbout());
	    return user;
	}
	
	// user to userDto
	
	private UserDto userToDto(User user) {
		
		UserDto userDto=this.modelMapper.map(user, UserDto.class);
		
//		userDto.setId(user.getId());
//		userDto.setName(user.getName());
//		userDto.setEmail(user.getEmail());
//		userDto.setPassword(user.getPassword());
//		userDto.setAbout(user.getAbout());
		
		return userDto;
	}

	@Override
	public UserDto registerNewUser(UserDto userDto) {
		
		User user = this.modelMapper.map(userDto, User.class);
		
		// encode the password
		user.setPassword(this.passwordEncoder.encode(user.getPassword()));
		
		//set roles(default as NORMAL_USER) 
		Role role = this.roleRepo.findById(AppConstants.NORMAL_USER).get();
		
		user.getRoles().add(role);
		User saveUser = this.userRepo.save(user);
		
		return this.modelMapper.map(saveUser, UserDto.class);
	}

	
	
}
