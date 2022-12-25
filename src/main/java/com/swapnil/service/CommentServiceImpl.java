package com.swapnil.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.swapnil.dto.CommentDTO;
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
public class CommentServiceImpl implements CommentService{

	@Autowired
	private CommentDAO coDao;
	@Autowired
	private CurrentUserDAO cDao;
	@Autowired
	private PostDAO pDao;
	
	@Override
	public Comment registerComment(Comment comment, Integer postId, String key)
			throws PostException, CurrentUserSessionException {
		CurrentUserSession cUser=cDao.findByUuid(key);
		if(cUser==null) {
			throw new CurrentUserSessionException("User not login");
		}else {
			Optional<Post> pos=pDao.findById(postId);
			if(pos.isPresent()) {
			
			Post p=pos.get();
			comment.setPost(p);
			Comment c=coDao.save(comment);
			p.getComments().add(c);
			pDao.save(p);
			return c;
			}else {
				throw new PostException("Post is not present");
			}
		}

	}

	@Override
	public Comment updateComment(Comment comment, Integer postId, Integer commentId, String key)
			throws PostException, CurrentUserSessionException, CommentException {
		
		CurrentUserSession cUser=cDao.findByUuid(key);
		if(cUser==null) {
			throw new CurrentUserSessionException("User not login");
		}else {
			Optional<Post> pos=pDao.findById(postId);
			if(pos.isPresent()) {
				Optional<Comment> com=coDao.findById(commentId);
				if(com.isPresent()) {
					if(commentId==com.get().getCommentId()) {
						Comment comm=com.get();
						Comment c=coDao.save(comm);
						Post p=pos.get();
						p.getComments().add(c);
						pDao.save(p);
						return c;
					}else {
						throw new CommentException("Comment id not match");
					}
				}else {
					throw new CommentException("Comment not found");
				}
			}else {
				throw new PostException("Post is not present");
			}

		}
		
	}

	@Override
	public Comment deleteComment(Integer postId, Integer commentId, String key)
			throws PostException, CurrentUserSessionException, CommentException {
		
		CurrentUserSession cUser=cDao.findByUuid(key);
		if(cUser==null) {
			throw new CurrentUserSessionException("User not login");
		}else {
			Optional<Post> pos=pDao.findById(postId);
			if(pos.isPresent()) {
				Optional<Comment> com=coDao.findById(commentId);
				if(com.isPresent()) {
		
					Comment co=com.get();
					coDao.delete(co);
					return co;
					
		}else {
			throw new CommentException("Comment not found");
		}
	}else {
		throw new PostException("Post is not present");
	}
		
		}
	}

	@Override
	public Comment viewComment(Integer postId, Integer commentId)
			throws PostException, CurrentUserSessionException, CommentException {
		// TODO Auto-generated method stub
		
		
			Optional<Post> pos=pDao.findById(postId);
			if(pos.isPresent()) {
				Optional<Comment> com=coDao.findById(commentId);
				if(com.isPresent()) {
		
					Comment co=com.get();
					return co;
					
		}else {
			throw new CommentException("Comment not found");
		}
	}else {
		throw new PostException("Post is not present");
	}
		
		}
	

	@Override
	public List<CommentDTO> viewAll(Integer postId) throws PostException, CurrentUserSessionException {
		
		
			Optional<Post> pos=pDao.findById(postId);
			if(pos.isPresent()) {
					List<Comment> colist=pos.get().getComments();
					
					List<CommentDTO> coDTOlist=new ArrayList<>();
					
					for(Comment c:colist) {
						CommentDTO cdto=new CommentDTO(c.getTitle(), c.getDecription(),c.getCreatedDate());
						coDTOlist.add(cdto);
					}
					
					return coDTOlist;
	}else {
		throw new PostException("Post is not present");
	}
		
		}
}


