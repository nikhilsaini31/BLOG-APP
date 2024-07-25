package com.blogApp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blogApp.entity.Role;

public interface RoleRepo extends JpaRepository<Role, Integer>{

	
}
