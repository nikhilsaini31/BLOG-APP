package com.blogApp.controllers;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.blogApp.config.AppConstants;
import com.blogApp.payload.ApiResponse;
import com.blogApp.payload.PostDto;
import com.blogApp.payload.PostResponse;
import com.blogApp.services.FileService;
import com.blogApp.services.PostService;


import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/")
public class PostController {

	@Autowired
	private PostService postService;
	
	@Autowired
	private FileService fileService;
	
	@Value("${project.image}")
	private String path;
	
	// create 
	
	@PostMapping("/user/{userId}/category/{categoryId}/post/create")
	public ResponseEntity<PostDto> createPost(@Valid @RequestBody PostDto postDto,@PathVariable Integer userId,@PathVariable Integer categoryId) {
		
		PostDto post = this.postService.createPost(postDto, userId, categoryId);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(post);
	}
	
	// get all posts with pagination------
	
	@GetMapping("/posts")
	public ResponseEntity<PostResponse> getAllPosts(
			@RequestParam(value = "pageNumber", defaultValue = AppConstants.PAGE_NUMBER ,required = false)Integer pageNumber,
			@RequestParam(value = "pageSize",defaultValue = AppConstants.PAGE_SIZE ,required = false) Integer pageSize,
			@RequestParam(value = "sortBy",defaultValue = AppConstants.SORT_BY,required = false) String sortBy,
			@RequestParam(value = "sortDir",defaultValue = AppConstants.SORT_DIR,required = false)String sortDir
			) {
		
		PostResponse allPost = this.postService.getAllPost(pageNumber,pageSize,sortBy,sortDir);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(allPost);
	}
	
	//  get single post
	
	@GetMapping("/post/get/{id}")
	public ResponseEntity<PostDto> getSinglePost(@PathVariable("id") Integer postId){
		
		PostDto post = this.postService.getPostById(postId);
		return new ResponseEntity<PostDto>(post,HttpStatus.ACCEPTED);
	}
	
	//update post
	
	@PutMapping("/post/update/{id}")
	public ResponseEntity<PostDto> updatePost(@Valid @RequestBody PostDto postDto,@PathVariable("id") Integer postId){
		
		PostDto updatePost = this.postService.updatePost(postDto, postId);
		
		return new ResponseEntity<PostDto>(updatePost,HttpStatus.OK);
	}
	 
	// delete post
	
	@DeleteMapping("/post/delete/{id}")
	public ResponseEntity deletePost(@PathVariable("id") Integer postId){
		
		this.postService.deletePost(postId);
		
		return ResponseEntity.status(HttpStatus.ACCEPTED).body("Post deleted successfully");
	}
	
	// get by user
	
	@GetMapping("/user/{userId}/posts")
	public ResponseEntity<List<PostDto>> getAllPostByUserId(@PathVariable Integer userId){
		
		List<PostDto> allPostByUser = this.postService.getAllPostByUser(userId);
		
		return ResponseEntity.status(HttpStatus.FOUND).body(allPostByUser);
	}
	
	// get by category

	@GetMapping("/category/{categoryId}/posts")
	public ResponseEntity<List<PostDto>> getAllPostByCategoryId(@PathVariable Integer categoryId) {

		List<PostDto> allPostByUser = this.postService.getAllPostByCategory(categoryId);

		return ResponseEntity.status(HttpStatus.FOUND).body(allPostByUser);
	}
		
	// search by title
	@GetMapping("/post/search/title/{keywords}")
	public ResponseEntity<List<PostDto>> searchByTitle(@PathVariable("keywords") String keyword){
		
		List<PostDto> searchPosts = this.postService.searchPosts(keyword);
		
		return ResponseEntity.status(HttpStatus.FOUND).body(searchPosts);
	}
	
	
	// post image upload
	@PostMapping("/post/image/upload/{postId}")
	public ResponseEntity<PostDto> uploadImage(@RequestParam("image") MultipartFile file,@PathVariable("postId") Integer postId) throws IOException{
	
		PostDto postDto = this.postService.getPostById(postId);
		String fileName = this.fileService.uploadImage(path, file);
	
		postDto.setImageName(fileName);
		PostDto updatePost = this.postService.updatePost(postDto, postId);
		
		return ResponseEntity.status(HttpStatus.OK).body(updatePost);
	}
	
	// serving/get image
	
	@GetMapping(value = "post/image/{imageName}")
	public ResponseEntity downloadFile(@PathVariable("imageName") String imageName,HttpServletResponse response)   {
			
		try {
			
			InputStream resource = this.fileService.getResource(path, imageName);
			
			response.setContentType(org.springframework.http.MediaType.IMAGE_JPEG_VALUE);
			
			StreamUtils.copy(resource, response.getOutputStream());
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse("file not found", false));
		}
		
		return ResponseEntity.status(HttpStatus.OK).body("ok");	
	}
	
		
}
