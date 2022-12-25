package com.swapnil.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.swapnil.dto.LoginDTO;
import com.swapnil.exception.CurrentUserSessionException;
import com.swapnil.exception.UserException;
import com.swapnil.model.CurrentUserSession;
import com.swapnil.repository.CurrentUserDAO;
import com.swapnil.service.LoginService;

@RestController
@RequestMapping("/api")
public class LoginController {

	@Autowired
	private LoginService lService;
	
	@PostMapping("/login")
	public ResponseEntity<CurrentUserSession> login(@RequestBody LoginDTO login) throws UserException, CurrentUserSessionException{
		
		CurrentUserSession c=lService.loginUser(login);
		
		return new ResponseEntity<CurrentUserSession>(c, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/login")
	public ResponseEntity<String> logout(@RequestParam String uUid) throws UserException, CurrentUserSessionException{
		
		String c=lService.logoutUser(uUid);
		
		return new ResponseEntity<String>(c, HttpStatus.CREATED);
	}
}
