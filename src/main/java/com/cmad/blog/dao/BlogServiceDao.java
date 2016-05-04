package com.cmad.blog.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.cmad.blog.model.*;

public class BlogServiceDao {
	
	private TagDao tagDao;
	public BlogServiceDao(){
		tagDao = new TagDao();
	}
	
	/**
	 * Get the blog details based on the blog ID passed as parameter
	 * 
	 * @param blogId of the blog for which details are to be fetched
	 * @return Blog object if it is found for the blog ID that is passed as parameter
	 * @throws IllegalArgumentException in case the blog Id passed is <code>Null</code>
	 */
	
	public Blog getBlogDao (Integer blogId){
		if (blogId == null) throw new IllegalArgumentException("Invalid blog Id passed, expected value, actual null");
		Session ses = HibernateUtil.currentSession();
		try {
			Criteria crit =  ses.createCriteria(Blog.class);
			crit.add(Restrictions.idEq(blogId));
			Blog b = (Blog)crit.uniqueResult();
			return b;
		} finally {
			HibernateUtil.closeSession();
		}
	}
	
	/**
	 * Get the blog details based on the Title passed as parameter
	 * 
	 * @param blogId of the blog for which details are to be fetched
	 * @return Blog object if it is found for the Title that is passed as parameter
	 * @throws IllegalArgumentException in case the title passed is <code>Null</code>
	 */
	
	public List<Blog> getBlogByTitleDao (String title){
		if (title == null) throw new IllegalArgumentException("Invalid title passed, expected value, actual null");
		Session ses = HibernateUtil.currentSession();
		try {
			Criteria crit =  ses.createCriteria(Blog.class);
			crit.add(Restrictions.eq("title", title).ignoreCase());
			List<Blog> b = crit.list();
			return b;
		} finally {
			HibernateUtil.closeSession();
		}
	}
	
	/**
	 * Get the list of blogs details based on the keyword string passed as parameter
	 * 
	 * @param Search keystring provided while creating the blog for which the blog/blogs are to be fetched
	 * @return List of Blog objects if it is found for the search keystring that is passed as parameter
	 * @throws IllegalArgumentException in case the search keystring passed is <code>Null</code>
	 */
	
	public List<Blog> getSearchBlogDao (String searchkey){
		if (searchkey == null) throw new IllegalArgumentException("Invalid searchKey passed, expected value, actual null");
		int KeyId = tagDao.getKeyId(searchkey);
		Session ses = HibernateUtil.currentSession();
		try {
			Criteria crit =  ses.createCriteria(Tag.class);
			crit.add(Restrictions.eq("keyId", KeyId));
			List<Blog> b = crit.list();
			return b;
		} finally {
			HibernateUtil.closeSession();
		}
	}
	
	
	
	/**
	 * Get the list of all blogs in the database
	 * 
	 * @return A list of all blogs in the database
	 */
	
	public List<Blog> getBlogsDao() {
		Session ses = HibernateUtil.currentSession();
		try {
			return ses.createCriteria(Blog.class).list();
		} finally {
			HibernateUtil.closeSession();
		}
	}
	
	public Integer createBlog (Blog b) {
		System.out.println("Creating blog with title : "+b.getTitle()+" on "+b.getEntryDate());
		int id=0;
		Session ses = HibernateUtil.currentSession();
		try {
			Transaction tx = ses.beginTransaction();
			ses.save(b);
			id=b.getBlogId();
			tx.commit();
		}finally{
			HibernateUtil.closeSession();
		}
		return id;
	}

}
