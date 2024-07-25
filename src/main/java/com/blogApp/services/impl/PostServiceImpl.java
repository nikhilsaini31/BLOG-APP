package com.blogApp.services.impl;


import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.blogApp.entity.Category;
import com.blogApp.entity.Post;
import com.blogApp.entity.User;
import com.blogApp.exceptions.ResourceNotFoundException;
import com.blogApp.payload.PostDto;
import com.blogApp.payload.PostResponse;
import com.blogApp.repositories.CategoryRepo;
import com.blogApp.repositories.PostRepo;
import com.blogApp.repositories.userRepo;
import com.blogApp.services.PostService;

@Service
public class PostServiceImpl implements PostService {

	@Autowired
	private PostRepo postRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private userRepo userRepo;

	@Autowired
	private CategoryRepo  categoryRepo;
	
	@Override
	public PostDto createPost(PostDto postDto,Integer userId,Integer categoryID) {
		
		// getting user and category
		User user = this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("user", "id", userId));
		Category category = this.categoryRepo.findById(categoryID).orElseThrow(()->new ResourceNotFoundException("category", "id", categoryID));
		
		
		Post post = this.modelMapper.map(postDto, Post.class);
		post.setImageName("default.png");
		post.setAddedDate(new Date());
		post.setUser(user);
		post.setCategory(category);
		
		Post savePost = this.postRepo.save(post);
		
		return this.modelMapper.map(savePost, PostDto.class);
	}

	@Override
	public PostResponse getAllPost(Integer pageNumber,Integer pageSize,String sortBy,String sortDir) {
		
		Sort sort=null;
		if(sortDir.equalsIgnoreCase("asc")) {
			
			sort = Sort.by(sortBy).ascending();
		}
		else {
			sort = Sort.by(sortBy).descending();
		}
		
		Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);// remember page start from 0
		Page<Post> pagePost = this.postRepo.findAll(pageable);

		List<Post> allPosts = pagePost.getContent();

		List<PostDto> postDtos = allPosts.stream().map(p -> this.modelMapper.map(p, PostDto.class)).collect(Collectors.toList());

		PostResponse postResponse = new PostResponse();
		
		postResponse.setContent(postDtos);
		postResponse.setPageNumber(pagePost.getNumber());
		postResponse.setPageSize(pagePost.getSize());
		postResponse.setTotalElements(pagePost.getTotalElements());
		postResponse.setTotalPages(pagePost.getTotalPages());
		postResponse.setLastPage(pagePost.isLast());
		
		return postResponse;
	}

	@Override
	public PostDto getPostById(Integer postId) {
		
		Post post = this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("post", "id", postId));
		
		return this.modelMapper.map(post, PostDto.class);
	}

	@Override
	public PostDto updatePost(PostDto postDto, Integer postId) {
		
		Post post = this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("post", "id", postId));
		
		post.setTitle(postDto.getTitle());
		post.setContent(postDto.getContent());
		post.setImageName(postDto.getImageName());
		
		Post savedPost = this.postRepo.save(post);
		
		return this.modelMapper.map(savedPost, PostDto.class);
	}

	@Override
	public void deletePost(Integer postId) {
		
		Post post = this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("post", "id", postId));
		
		this.postRepo.delete(post);
		
	}

	@Override
	public List<PostDto> getAllPostByCategory(Integer categoryId) {
	
		Category category = this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("category", "id", categoryId));
		
		List<Post> posts = this.postRepo.findByCategory(category);
		
		List<PostDto> postDtos = posts.stream().map(post->this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		
		return postDtos;
	}

	@Override
	public List<PostDto> getAllPostByUser(Integer userId) {
		
		User user = this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("user", "id", userId));
		
		List<Post> posts = this.postRepo.findByUser(user);
		
		List<PostDto> postDtos = posts.stream().map(post->this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		
		return postDtos;
	}

	@Override
	public List<PostDto> searchPosts(String keyword) {
		
		List<Post> posts = this.postRepo.findByTitleContaining(keyword);
		
		List<PostDto> postDtos = posts.stream().map(p->this.modelMapper.map(p, PostDto.class)).collect(Collectors.toList());
		
		return postDtos;
	}
	
	
}
