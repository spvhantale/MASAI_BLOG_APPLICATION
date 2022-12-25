package com.swapnil.service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.swapnil.dto.LoginDTO;
import com.swapnil.exception.CurrentUserSessionException;
import com.swapnil.exception.UserException;
import com.swapnil.model.CurrentUserSession;
import com.swapnil.model.User;
import com.swapnil.repository.CurrentUserDAO;
import com.swapnil.repository.UserDAO;

import net.bytebuddy.utility.RandomString;

@Service
public class LoginServiceImpl implements LoginService{

	@Autowired
	private CurrentUserDAO cDao;
	@Autowired
	private UserDAO uDao;
	@Override
	public CurrentUserSession loginUser(LoginDTO login) throws UserException,CurrentUserSessionException {
		
		User us=uDao.findByMobile(login.getMobile());
		if(us!=null) {
		Optional<CurrentUserSession>	cUser=cDao.findById(us.getUserId());
			
			if(cUser.isPresent()) {
				throw new CurrentUserSessionException("User already login");
			}else {
				if(us.getPassword().equals(login.getPassword())) {
					
					String key=RandomString.make(6);
					CurrentUserSession cUserl=new CurrentUserSession(us.getUserId(), key, LocalDateTime.now());
					CurrentUserSession u=cDao.save(cUserl);
					return u;
				}else {
					throw new UserException("Password is wrong");
				}
			}
		}else {
			throw new UserException("User Not found");
		}
	}

	@Override
	public String logoutUser(String key) throws CurrentUserSessionException {

		CurrentUserSession user=cDao.findByUuid(key);
		if(user==null) {
			throw new CurrentUserSessionException("User not login");
		}else {
			cDao.delete(user);
			return "logout successfully"+user.getUserId();
		}
		
	}

}
