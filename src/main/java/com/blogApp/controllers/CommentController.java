package com.blogApp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blogApp.payload.ApiResponse;
import com.blogApp.payload.CommentDto;
import com.blogApp.services.CommentService;

@RestController
@RequestMapping("/api/")
public class CommentController {

	@Autowired
	private CommentService commentService;
	
	@PostMapping("/post/{postId}/comment/create")
	public ResponseEntity<CommentDto> createComment(@RequestBody CommentDto commentDto,@PathVariable("postId") Integer postId){
		
		CommentDto comment = this.commentService.createComment(commentDto, postId);
	    
		return new ResponseEntity<CommentDto>(comment,HttpStatus.CREATED);
	}
	
	@DeleteMapping("/post/comment/delete/{commentId}")
	public ResponseEntity<ApiResponse> createComment(@PathVariable("commentId") Integer commentId){
		
	 this.commentService.deleteComment(commentId);
	    
		return  ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Comment deleted successfully", true));
	}
	
}
