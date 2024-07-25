package com.blogApp.services.impl;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;

import com.blogApp.entity.Comment;
import com.blogApp.entity.Post;
import com.blogApp.exceptions.ResourceNotFoundException;
import com.blogApp.payload.CommentDto;
import com.blogApp.payload.PostDto;
import com.blogApp.repositories.CommentRepo;
import com.blogApp.repositories.PostRepo;
import com.blogApp.services.CommentService;

@Service
public class CommnetServiceImpl implements CommentService {

	@Autowired
	private PostRepo postRepo;
	
	@Autowired
	private CommentRepo commentRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public CommentDto createComment(CommentDto commentDto, Integer postId) {
		
		 Post post = this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("post", "id", postId));
		 
		 Comment comment = this.modelMapper.map(commentDto,Comment.class);
		 comment.setPost(post);
		 Comment saveCommnet = this.commentRepo.save(comment);
		
		return this.modelMapper.map(saveCommnet, CommentDto.class);
	}

	@Override
	public void deleteComment(Integer commentId) {
		
		Comment comment = this.commentRepo.findById(commentId).orElseThrow(()->new ResourceNotFoundException("commnet", "id", commentId));

		this.commentRepo.delete(comment);
	}

}
