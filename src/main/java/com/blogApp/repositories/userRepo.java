package com.blogApp.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blogApp.entity.User;

public interface userRepo extends JpaRepository<User, Integer> {

	Optional<User> findByEmail(String username);
	
}
