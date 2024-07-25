package com.blogApp.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blogApp.payload.ApiResponse;
import com.blogApp.payload.UserDto;
import com.blogApp.services.UserService;

import io.swagger.v3.oas.annotations.Hidden;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/users")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	// post- create user
	@Hidden // this is for hidden api in swagger
	@PostMapping("/add")
	public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto dto){
		
		UserDto userDto = this.userService.createUser(dto);
		return new ResponseEntity<>(userDto,HttpStatus.CREATED);
	}
	
	// Get - get all user
	@GetMapping("/")
	public ResponseEntity<List<UserDto>> getAllUsers() {

		List<UserDto> allUsers = this.userService.getAllUsers();

		return ResponseEntity.status(HttpStatus.ACCEPTED).body(allUsers);
	}
	
	// Get - get user by id
	@GetMapping("/get/{id}")
	public ResponseEntity<UserDto> getUserById(@PathVariable("id") Integer id){
		
		UserDto userById = this.userService.getUserById(id);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(userById);
	}
	
	// put- update user
	@PutMapping("/update/{id}")
	public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto dto,@PathVariable("id") Integer id){
		
		UserDto updateUser = this.userService.updateUser(dto, id);
		
		return ResponseEntity.status(HttpStatus.OK).body(updateUser);
	}
	
	// ADMIN
	// Delete- delete uset
	@PreAuthorize("hasRole('ADMIN')") // now only admin can delete the user
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<ApiResponse> deleteUser(@PathVariable("id") Integer id){
		
		this.userService.deleteUser(id);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(new ApiResponse("User deleted successfully", true));
	}
	
	
}
