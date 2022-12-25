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

import com.swapnil.dto.CommentDTO;
import com.swapnil.exception.CommentException;
import com.swapnil.exception.CurrentUserSessionException;
import com.swapnil.exception.PostException;
import com.swapnil.model.Comment;
import com.swapnil.service.CommentService;

@RestController
@RequestMapping("/api")
public class CommentController {

	@Autowired
	private CommentService cService;
	
	
	@PostMapping("/posts/{postId}/comments")
	public ResponseEntity<Comment> registerComment(@Valid @RequestBody Comment comment,@PathVariable("postId") Integer postId,@RequestParam String uUid) throws PostException, CurrentUserSessionException{
		Comment c=cService.registerComment(comment, postId, uUid);
		
		return new ResponseEntity<Comment>(c,HttpStatus.CREATED);
	}
	@PutMapping("/posts/{postId}/comments/{commentId}")
	public ResponseEntity<Comment> updateComment(@Valid @RequestBody Comment comment,@PathVariable("postId") Integer postId,@PathVariable("commentId") Integer commentId,@RequestParam String uUid) throws PostException, CurrentUserSessionException, CommentException{
		Comment c=cService.updateComment(comment, postId, commentId, uUid);
		
		return new ResponseEntity<Comment>(c,HttpStatus.OK);
	}
	
	@DeleteMapping("/posts/{postId}/comments/{commentId}")
	public ResponseEntity<Comment> deleteComment(@PathVariable("postId") Integer postId,@PathVariable("commentId") Integer commentId,@RequestParam String uUid) throws PostException, CurrentUserSessionException, CommentException{
		Comment c=cService.deleteComment(postId, commentId, uUid);
		
		return new ResponseEntity<Comment>(c,HttpStatus.OK);
	}
	@GetMapping("/posts/{postId}/comments/{commentId}")
	public ResponseEntity<Comment> viewComment(@PathVariable("postId") Integer postId,@PathVariable("commentId") Integer commentId) throws PostException, CurrentUserSessionException, CommentException{
		Comment c=cService.viewComment(postId, commentId);
		
		return new ResponseEntity<Comment>(c,HttpStatus.OK);
	}
	@GetMapping("/posts/{postId}/comments")
	public ResponseEntity<List<CommentDTO>> viewAllComment(@PathVariable("postId") Integer postId) throws PostException, CurrentUserSessionException, CommentException{
		List<CommentDTO> c=cService.viewAll(postId);
		
		return new ResponseEntity<List<CommentDTO>>(c,HttpStatus.OK);
	}
	
	
}
