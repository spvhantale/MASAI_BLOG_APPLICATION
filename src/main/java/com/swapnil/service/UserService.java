package com.swapnil.service;

import com.swapnil.exception.CurrentUserSessionException;
import com.swapnil.exception.UserException;
import com.swapnil.model.User;

public interface UserService {

	public User registerUser(User user) throws UserException;
	
	public User updateUser(User user,String key) throws UserException,CurrentUserSessionException;
}
