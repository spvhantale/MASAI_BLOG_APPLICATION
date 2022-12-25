package com.swapnil.service;

import com.swapnil.dto.LoginDTO;
import com.swapnil.exception.CurrentUserSessionException;
import com.swapnil.exception.UserException;
import com.swapnil.model.CurrentUserSession;

public interface LoginService {

	public CurrentUserSession loginUser(LoginDTO login) throws UserException,CurrentUserSessionException;
	public String logoutUser(String key) throws CurrentUserSessionException;
}
