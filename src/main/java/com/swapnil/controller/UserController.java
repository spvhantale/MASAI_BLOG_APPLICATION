package com.swapnil.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.swapnil.exception.CurrentUserSessionException;
import com.swapnil.exception.UserException;
import com.swapnil.model.User;
import com.swapnil.service.UserService;

@RestController
@RequestMapping("/api")
public class UserController {

	@Autowired
	private UserService uService;
	
	@PostMapping("/user")
	public ResponseEntity<User> registerUser(@Valid @RequestBody User user) throws UserException{
		
		User u=uService.registerUser(user);
		
		return new ResponseEntity<User>(u, HttpStatus.CREATED);
	}
	
	@PutMapping("/user")
	public ResponseEntity<User> updateUser(@Valid @RequestBody User user,@RequestParam String key) throws UserException, CurrentUserSessionException{
		
		User u=uService.updateUser(user,key);
		
		return new ResponseEntity<User>(u, HttpStatus.OK);
	}
}
