package com.blogApp.payload;

import lombok.Data;

@Data
public class JwtAuthRequest {

	private String username; // username is email
	
	private String password;
	
}
