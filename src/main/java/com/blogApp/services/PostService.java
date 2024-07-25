package com.blogApp.services;

import java.util.List;

import com.blogApp.entity.Post;
import com.blogApp.payload.PostDto;
import com.blogApp.payload.PostResponse;

public interface PostService {
	
	PostDto createPost(PostDto postDto,Integer userId,Integer categoryID);
	
	// postResponse for pagination and soring 
	PostResponse getAllPost(Integer pageNumber,Integer pageSize,String sortBy,String sortDir);
	
	PostDto getPostById(Integer postId);
	
	PostDto updatePost(PostDto postDto,Integer postId);
	
	void deletePost(Integer postId);

	// get all post by category
	List<PostDto> getAllPostByCategory(Integer categoryId);
	
	// get all post by user
	List<PostDto> getAllPostByUser(Integer userId);
	
	// search post
	List<PostDto> searchPosts(String keyword);
}
