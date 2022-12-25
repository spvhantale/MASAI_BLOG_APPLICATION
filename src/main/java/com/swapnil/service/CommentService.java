package com.swapnil.service;

import java.util.List;

import com.swapnil.dto.CommentDTO;
import com.swapnil.exception.CommentException;
import com.swapnil.exception.CurrentUserSessionException;
import com.swapnil.exception.PostException;
import com.swapnil.model.Comment;

public interface CommentService {

	public Comment registerComment(Comment comment,Integer postId,String key) throws PostException,CurrentUserSessionException;
	public Comment updateComment(Comment comment,Integer postId,Integer commentId,String key) throws PostException,CurrentUserSessionException,CommentException;
	public Comment deleteComment(Integer postId,Integer commentId,String key) throws PostException,CurrentUserSessionException,CommentException;
	public Comment viewComment(Integer postId,Integer commentId) throws PostException,CurrentUserSessionException,CommentException;

	public List<CommentDTO> viewAll(Integer postId) throws PostException,CurrentUserSessionException;
}
