package com.cmad.blog.services;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.cmad.blog.dao.*;
import com.cmad.blog.model.Comment;

@Path("/comment")

public class CommentService {
	
	private CommentServiceDao commentDao;
	
	public CommentService() {
		commentDao = new CommentServiceDao();
	}
	
	@GET
	@Path("/{param}")
	@Produces({MediaType.APPLICATION_JSON})
	public Comment getComment(@PathParam("param") Integer commentId) {
		Comment c = commentDao.getCommentDao(commentId);
		return c;
	}
	
	@GET
	@Produces({MediaType.APPLICATION_JSON})
	public List<Comment> getComments() {
		List<Comment> comments = commentDao.getCommentsDao();
		return comments;
	}
	
	@POST
	@Consumes({MediaType.APPLICATION_JSON})
	public void createComment (Comment c) {
		commentDao.createComment(c);
	}

}
