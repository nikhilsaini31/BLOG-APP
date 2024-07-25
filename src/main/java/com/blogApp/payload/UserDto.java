package com.blogApp.payload;

import java.util.HashSet;
import java.util.Set;

import com.blogApp.entity.Role;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// class just for data transfer

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserDto {

	private int id;

	@NotEmpty
	@Size(min = 3, message = "Username must be minimum of 3 characters")
	private String name;

	@Email(message = "Enter valid email address")
	@NotEmpty
	@Pattern(regexp = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])",message = "please Enter a valid Email Adderss")
	private String email;

	@NotEmpty
	@Size(min = 6, message = "password must be minimum of 6 letters")
	private String password;

	@NotEmpty
	private String about;
	
	
	private Set<RoleDto> roles = new HashSet<>();

}
