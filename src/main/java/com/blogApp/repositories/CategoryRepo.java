package com.blogApp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blogApp.entity.Category;

public interface CategoryRepo extends JpaRepository<Category, Integer>  {

	
	
}
