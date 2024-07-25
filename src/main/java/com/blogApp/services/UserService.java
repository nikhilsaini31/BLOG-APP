package com.blogApp.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.blogApp.payload.UserDto;


public interface UserService {

	UserDto registerNewUser(UserDto userDto);
	
	// method with no access modifyer , we can add private , public.....
	UserDto createUser(UserDto userDto);
	
	UserDto updateUser(UserDto userDto,Integer userId);
	
	UserDto getUserById(Integer userId); 
	
	List<UserDto> getAllUsers();
	
	void deleteUser(Integer userId);
}
