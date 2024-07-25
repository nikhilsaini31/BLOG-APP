package com.blogApp.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blogApp.entity.Post;
import com.blogApp.entity.User;
import com.blogApp.entity.Category;


public interface PostRepo  extends JpaRepository<Post, Integer>{

	// custom methods
	
	public List<Post> findByUser(User user);
	
	public List<Post> findByCategory(Category category);
	
	// for searching 
	public List<Post> findByTitleContaining(String title);
	
}
