package com.swapnil.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.swapnil.model.CurrentUserSession;
import com.swapnil.model.User;
@Repository
public interface UserDAO extends JpaRepository<User, Integer>{

	public User findByMobile(String mobile);
}
