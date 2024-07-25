package com.blogApp.payload;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.blogApp.entity.Category;
import com.blogApp.entity.Comment;
import com.blogApp.entity.User;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PostDto {

	private Integer id; 
	
	@NotEmpty
	private String title;
	
	@NotEmpty
	private String content;
	
	private String imageName;
	
	private Date addedDate;
	
	private CategoryDto category;
	
	private UserDto user;
	
	private Set<CommentDto> comments=new HashSet<>();

	
}

