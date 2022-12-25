package com.swapnil.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.swapnil.dto.CommentDTO;
import com.swapnil.dto.PostDTO;
import com.swapnil.exception.CommentException;
import com.swapnil.exception.CurrentUserSessionException;
import com.swapnil.exception.PostException;
import com.swapnil.model.Comment;
import com.swapnil.model.CurrentUserSession;
import com.swapnil.model.Post;
import com.swapnil.repository.CommentDAO;
import com.swapnil.repository.CurrentUserDAO;
import com.swapnil.repository.PostDAO;

@Service
public class PostServiceImpl implements PostService{

	@Autowired
	private CurrentUserDAO cDao;
	
	@Autowired
	private PostDAO pDao;
	
	@Autowired
	private CommentDAO coDao;
	
	
	@Override
	public Post registerPost(Post post, String key) throws CurrentUserSessionException {
		
		CurrentUserSession cUser=cDao.findByUuid(key);
		if(cUser==null) {
			throw new CurrentUserSessionException("Login First");
		}else {
			List<Comment> comments=post.getComments();
			
			for(Comment c:comments) {
				c.setPost(post);
				coDao.save(c);
			}
			Post pps=pDao.save(post);
			return pps;
		}
	}

	@Override
	public Post updatePost(Post post, Integer postId,String key)
			throws PostException, CommentException, CurrentUserSessionException {
		
		CurrentUserSession cUser=cDao.findByUuid(key);
		if(cUser==null) {
			throw new CurrentUserSessionException("Login First");
		}else {
			if(post.getPostId()==postId) {
				Optional<Post> pos=pDao.findById(postId);
				if(pos.isPresent()) {
					List<Comment> comments=post.getComments();
					for(Comment c:comments) {
						c.setPost(post);
						coDao.save(c);
					}
					Post p=pDao.save(post);
					return p;
				}else {
					throw new PostException("post is not present");
				}
			}else {
				throw new PostException("Id not same");
			}
		}
	}

	@Override
	public Post deletePost(Integer postId, String key) throws PostException, CurrentUserSessionException {
		CurrentUserSession cUser=cDao.findByUuid(key);
		if(cUser==null) {
			throw new CurrentUserSessionException("Login First");
		}else {
			Optional<Post> po=pDao.findById(postId);
			
			if(po.isPresent()) {
				Post post=po.get();
				pDao.delete(post);
				return post;
			}else {
				throw new PostException("Post not present");
			}
		}
	}

	@Override
	public Post viewPost(Integer postId) throws PostException {
			Optional<Post> po=pDao.findById(postId);
			if(po.isPresent()) {
				Post post=po.get();
				return post;
			}else {
				throw new PostException("Post not present");
			}
		
	}

	@Override
	public List<PostDTO> viewAllPost() throws PostException {
		
		List<Post> plist=pDao.findAll();
		if(plist.isEmpty()) {
			throw new PostException("posts not present");
		}
		List<PostDTO> pdto=new ArrayList<>();
		for(Post p:plist) {
			List<CommentDTO> cdto=new ArrayList<>();
			for(Comment c:p.getComments()) {
				CommentDTO cdt=new CommentDTO(c.getTitle(), c.getDecription(), c.getCreatedDate());
				cdto.add(cdt);
			}
			PostDTO postDTO=new PostDTO(p.getTitle(), p.getDescription(), p.getCreatedDate(),cdto);
			pdto.add(postDTO);
		}
		return pdto;
	}

}
