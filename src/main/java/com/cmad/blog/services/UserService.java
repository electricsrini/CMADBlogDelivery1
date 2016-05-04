package com.cmad.blog.services;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.cmad.blog.dao.*;
import com.cmad.blog.model.Blog;
import com.cmad.blog.model.User;


@Path("/user")

public class UserService {
	
	private UserServiceDao userDao;
	private BlogServiceDao blogDao;
	
	public UserService() {
		userDao = new UserServiceDao();
		blogDao = new BlogServiceDao();
	}
	
	/**
	 * Gets the user detail based on the user id that is passed as parameter
	 * 
	 * @param userId ID of the user for whom the details are to be retrieved
	 * @return Users object containing the user information in case user is found for given 
	 * id. 
	 * @throws IllegalArgumentException in case userId is <code>null</code> 
	 */
	
	@GET
	@Path("/{param}")
	@Produces({MediaType.APPLICATION_JSON})	
	public User getUser(@PathParam("param") Integer userId) {
		if (userId == null) throw new IllegalArgumentException("Invalid userid passed, expected value, actual null"); 
		User u = userDao.getUserDao(userId);
		return u;
	}
	
	/**
	 * Gets all the users details that are present in the DB as a List.
	 * 
	 * @return List of all User objects that are present in the DB.
	 */
	
	@GET
	@Produces({MediaType.APPLICATION_JSON})
	public List<User> getUsers() {
		List<User> users = userDao.getUsersDao();		
		return users;
	}
	
	
	/**
	 * Gets the user detail based on the email ID of the user that is passed as parameter
	 * 
	 * @param emailId of the user for whom the details are to be retrieved
	 * @return User object containing the user information in case the user is found for the given email Id.
	 * @throws IllegalArgumentException in case emailId is <code>Null</code>
	 */
	
	@GET
	@Path("/?email={param}")
	@Produces({MediaType.APPLICATION_JSON})
	public User getUser(@PathParam("param") String emailId) {
		if (emailId == null) throw new IllegalArgumentException("Invalid email ID passed, expected value, actual null");
		User u = userDao.getUserDao(emailId);
		return u;
	}
	
	/**
	 * Gets all the blogs created by the user, for the user whose email ID is provided as the parameter
	 * 
	 * @param emailId
	 * @return Blogs created by the user in case there are blogs created for the user identified by the Email ID passes asa parameter
	 * @throws IllegalArgumentException in case the email ID provided is <code>Null</code>
	 */
	
	@GET
	@Path("/blog/?email={param}")
	@Produces({MediaType.APPLICATION_JSON})
	public List<Blog> getBlogs(@PathParam("param") String emailId) {
		if (emailId == null) throw new IllegalArgumentException("Invalid email ID passed, expected value, actual null");
		List<Blog> b = userDao.getBlogsDao(emailId);
		return b;
	}
	
	/**
	 * Post / create a new user. The user Id is auto-generated and so in join date. Parameters even if provided will be ignored / overriddgen.
	 *  
	 * @param u
	 * @return userId of the entry that is created.
	 */
	
	@POST
	@Consumes({MediaType.APPLICATION_JSON})
	public Integer createUser (User u) {
		int id = 0;
		userDao.createUser(u);
		return id;
	}


}
