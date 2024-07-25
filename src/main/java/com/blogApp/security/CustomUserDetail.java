package com.blogApp.security;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.blogApp.entity.Role;
import com.blogApp.entity.User;

public class CustomUserDetail implements UserDetails {

	private User user;

	public CustomUserDetail(User user) {
		super();
		this.user = user;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {

		
		List<SimpleGrantedAuthority> authorities = this.user.getRoles().stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
		return authorities;
		 

//		SimpleGrantedAuthority grantedAuthority = new SimpleGrantedAuthority(new Role().getName());
//	    return List.of(grantedAuthority);
	}

	@Override
	public String getPassword() {

		return this.user.getPassword();
	}

	@Override
	public String getUsername() {

		return this.user.getEmail();
	}

}
