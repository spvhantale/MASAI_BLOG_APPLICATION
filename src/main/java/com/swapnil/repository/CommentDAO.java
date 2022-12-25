package com.swapnil.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.swapnil.model.Comment;
@Repository
public interface CommentDAO extends JpaRepository<Comment, Integer>{

}
