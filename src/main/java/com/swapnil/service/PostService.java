package com.swapnil.service;

import java.util.List;

import com.swapnil.dto.PostDTO;
import com.swapnil.exception.CommentException;
import com.swapnil.exception.CurrentUserSessionException;
import com.swapnil.exception.PostException;
import com.swapnil.model.Post;

public interface PostService {

	
	public Post registerPost(Post post,String key) throws CurrentUserSessionException;
	
	public Post updatePost(Post post,Integer postId,String key) throws PostException,CommentException,CurrentUserSessionException;
	
	public Post deletePost(Integer postId,String key) throws PostException,CurrentUserSessionException;
	
	public Post viewPost(Integer postId)throws PostException;
	
	public List<PostDTO> viewAllPost() throws PostException;
}
