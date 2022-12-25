package com.swapnil.exception;

import java.time.LocalDateTime;
import java.time.LocalTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.swapnil.model.Post;

@ControllerAdvice
public class GlobalException {

	
	@ExceptionHandler(PostException.class)
	public ResponseEntity<MyError> postException(PostException p,WebRequest req){
		
		MyError myerror=new MyError(p.getMessage(),  LocalDateTime.now(),req.getDescription(false));
		
		return new ResponseEntity<MyError>(myerror, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(CommentException.class)
	public ResponseEntity<MyError> commentException(CommentException p,WebRequest req){
		
		MyError myerror=new MyError(p.getMessage(),  LocalDateTime.now(),req.getDescription(false));
		
		return new ResponseEntity<MyError>(myerror, HttpStatus.BAD_REQUEST);
	}
	@ExceptionHandler(UserException.class)
	public ResponseEntity<MyError> userException(UserException p,WebRequest req){
		
		MyError myerror=new MyError(p.getMessage(),  LocalDateTime.now(),req.getDescription(false));
		
		return new ResponseEntity<MyError>(myerror, HttpStatus.BAD_REQUEST);
	}
	@ExceptionHandler(CurrentUserSessionException.class)
	public ResponseEntity<MyError> currentUserException(CurrentUserSessionException p,WebRequest req){
		
		MyError myerror=new MyError(p.getMessage(),  LocalDateTime.now(),req.getDescription(false));
		
		return new ResponseEntity<MyError>(myerror, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<MyError> methodException(MethodArgumentNotValidException m){
		
		MyError myerror=new MyError(m.getMessage(),  LocalDateTime.now(),m.getBindingResult().getFieldError().getDefaultMessage());
		
		return new ResponseEntity<MyError>(myerror, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(NoHandlerFoundException.class)
	public ResponseEntity<MyError> noException(NoHandlerFoundException p,WebRequest req){
		
		MyError myerror=new MyError(p.getMessage(),  LocalDateTime.now(),req.getDescription(false));
		
		return new ResponseEntity<MyError>(myerror, HttpStatus.BAD_REQUEST);
	}
	
}
