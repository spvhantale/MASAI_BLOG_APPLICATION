package com.swapnil.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.swapnil.exception.CurrentUserSessionException;
import com.swapnil.exception.UserException;
import com.swapnil.model.CurrentUserSession;
import com.swapnil.model.User;
import com.swapnil.repository.CurrentUserDAO;
import com.swapnil.repository.UserDAO;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserDAO uDao;
	@Autowired
	private CurrentUserDAO cDao;
	
	
	@Override
	public User registerUser(User user) throws UserException {
		
		User us=uDao.findByMobile(user.getMobile());
		if(us!=null) {
			throw new UserException("User already exit");
		}
		User u=uDao.save(user);
		return u;
	}

	@Override
	public User updateUser(User user, String key) throws UserException, CurrentUserSessionException {
		
		Optional<User> us=uDao.findById(user.getUserId());
		if(us.isPresent()) {
			CurrentUserSession cUser=cDao.findByUuid(key);
			if(cUser.getUserId()==user.getUserId()) {
				User u=uDao.save(user);
				return u;
			}else {
				throw new CurrentUserSessionException("not same User");
			}
		}else {
			throw new UserException("User not found");
		}
		
		
	}

}
