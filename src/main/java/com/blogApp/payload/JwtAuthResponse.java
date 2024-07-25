package com.blogApp.payload;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
public class JwtAuthResponse {

	private String token;
}
