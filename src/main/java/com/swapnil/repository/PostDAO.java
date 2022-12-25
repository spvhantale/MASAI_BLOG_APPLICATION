package com.swapnil.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.swapnil.model.Post;

@Repository
public interface PostDAO extends JpaRepository<Post, Integer> {

	
}
