package com.blogApp.services;

import com.blogApp.payload.CommentDto;

public interface CommentService {

	CommentDto createComment(CommentDto commentDto,Integer postId) ;
	
	void deleteComment(Integer commentId);
}
