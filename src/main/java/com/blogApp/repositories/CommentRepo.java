package com.blogApp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blogApp.entity.Comment;

public interface CommentRepo extends JpaRepository<Comment, Integer> {

}
