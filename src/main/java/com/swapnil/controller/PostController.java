package com.swapnil.controller;

import java.util.List;

import javax.validation.Valid;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.swapnil.dto.PostDTO;
import com.swapnil.exception.CommentException;
import com.swapnil.exception.CurrentUserSessionException;
import com.swapnil.exception.PostException;
import com.swapnil.model.Post;
import com.swapnil.service.PostService;

@RestController
@RequestMapping("/api")
public class PostController {

	@Autowired
	private PostService pService;
	
	@PostMapping("/posts")
	public ResponseEntity<Post> registerPost(@Valid @RequestBody Post post,@RequestParam String uUid) throws CurrentUserSessionException{
		
		Post p=pService.registerPost(post,uUid);
		return new ResponseEntity<Post>(p, HttpStatus.CREATED);
	}
	@PutMapping("/posts/{postId}")
	public ResponseEntity<Post> updatePost(@Valid @RequestBody Post post,@RequestParam String uUid,@PathVariable("postId") Integer postId) throws CurrentUserSessionException, PostException, CommentException{
		
		Post p=pService.updatePost(post,postId,uUid);
		return new ResponseEntity<Post>(p, HttpStatus.OK);
	}
	@DeleteMapping("/posts/{postId}")
	public ResponseEntity<Post> deletePost(@RequestParam String uUid,@PathVariable("postId") Integer postId) throws CurrentUserSessionException, PostException, CommentException{
		Post p=pService.deletePost(postId, uUid);
		return new ResponseEntity<Post>(p, HttpStatus.OK);
	}
	@GetMapping("/posts/{postId}")
	public ResponseEntity<Post> viewPost(@PathVariable("postId") Integer postId) throws PostException, CommentException{
		Post p=pService.viewPost(postId);
		return new ResponseEntity<Post>(p, HttpStatus.OK);
	}
	@GetMapping("/posts")
	public ResponseEntity<List<PostDTO>> viewAllPost() throws CurrentUserSessionException, PostException, CommentException{
		List<PostDTO> p=pService.viewAllPost();
		return new ResponseEntity<List<PostDTO>>(p, HttpStatus.OK);
	}
	
}
