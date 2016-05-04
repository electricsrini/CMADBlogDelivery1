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
import com.cmad.blog.model.Blog;


@Path("/blog")
public class BlogService {
	
	private BlogServiceDao blogDao;
	private UserServiceDao userDao;
	
	public BlogService() {
		blogDao = new BlogServiceDao();
		userDao = new UserServiceDao();
	}
	
	/**
	 * Get the blog details based on the blog ID passed as parameter
	 * 
	 * @param blogId of the blog for which details are to be fetched
	 * @return Blog object if it is found for the blog ID that is passed as parameter
	 * @throws IllegalArgumentException in case the blog Id passed is <code>Null</code>
	 */
	@GET
	@Path("/{param}")
	@Produces({MediaType.APPLICATION_JSON})
	public Blog getBlog(@PathParam("param") Integer blogId) {
		if (blogId == null) throw new IllegalArgumentException("Invalid blog Id passed, expected value, actual null");
		Blog b = blogDao.getBlogDao(blogId);
		return b;
	}
	
	/**
	 * Get the blog details based on the Title passed as parameter
	 * 
	 * @param title of the blog / list of blogs for which details are to be fetched
	 * @return List of Blog objects if it is found for the Title that is passed as parameter
	 * @throws IllegalArgumentException in case the title passed is <code>Null</code>
	 */
	
	@GET
	@Path("/?title={param}")
	@Produces({MediaType.APPLICATION_JSON})
	public List<Blog> getBlogByTitle(@PathParam("param") String title) {
		if (title == null) throw new IllegalArgumentException("Invalid Title passed, expected value, actual null");
		List<Blog> b = blogDao.getBlogByTitleDao(title);
		return b;
	}
	
	/**
	 * Get the list of blogs details based on the keyword string passed as parameter
	 * 
	 * @param Search keystring provided while creating the blog for which the blog/blogs are to be fetched
	 * @return List of Blog objects if it is found for the search keystring that is passed as parameter
	 * @throws IllegalArgumentException in case the search keystring passed is <code>Null</code>
	 */
	
	@GET
	@Path("/?tag={param}")
	@Produces({MediaType.APPLICATION_JSON})
	public List<Blog> getSearchBlog(@PathParam("param") String searchKey) {
		if (searchKey == null) throw new IllegalArgumentException("Invalid searchKey passed, expected value, actual null");
		List<Blog> b = blogDao.getSearchBlogDao(searchKey);
		return b;
	}
	
	/**
	 * Get the list of all blogs in the database
	 * 
	 * @return A list of all blogs in the database
	 */
	
	@GET
	@Produces({MediaType.APPLICATION_JSON})
	public List<Blog> getBlogs() {
		List<Blog> blogs = blogDao.getBlogsDao();
		return blogs;
	}
	
	/**
	 * Post / create a new a blog. The blog Id is auto-generated and so in entry date. Parameters even if provided will be ignored / overridden.
	 *  
	 * @param Blog object
	 * @return blogId of the entry that is made is returned
	 */
	
	@POST
	@Consumes({MediaType.APPLICATION_JSON})
	public Integer createBlog (Blog b) {
		int id = 0;
		
		System.out.println("createBlog :: blog :" + b);
		id = blogDao.createBlog(b);
		
		return id;
	}

}
