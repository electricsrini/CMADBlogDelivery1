package com.cmad.blog.dao;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.cmad.blog.model.Blog;
import com.cmad.blog.model.User;


public class UserServiceDao {
	
	/**
	 * Get User details based on the user ID that is passed a parameter
	 * 
	 * @param userId of the user whose details are to be fetched
	 * @return User object if found for the user Id passed as parameter
	 * @throws IllegalArgumentException in case the user Id passed is <code>Null</code>
	 */
	public User getUserDao (Integer userId){
		if (userId == null) throw new IllegalArgumentException("Invalid user Id passed, expected value, actual null");
		Session ses = HibernateUtil.currentSession();
		try {
			Criteria crit =  ses.createCriteria(User.class);
			crit.add(Restrictions.idEq(userId));
			User u = (User)crit.uniqueResult();
			return u;
		} finally {
			HibernateUtil.closeSession();
		}
	}
	
	
	/**
	 * Get all users in the database
	 * @return List of User objects present in the database
	 */
	public List<User> getUsersDao() {
		Session ses = HibernateUtil.currentSession();
		try {
			return ses.createCriteria(User.class).list();
		} finally {
			HibernateUtil.closeSession();
		}
	}
	
	/**
	 * Gets the user detail based on the email ID of the user that is passed as parameter
	 * 
	 * @param emailId of the user for whom the details are to be retrieved
	 * @return User object containing the user information in case the user is found for the given email Id.
	 * @throws IllegalArgumentException in case emailId is <code>Null</code>
	 */
	
	public User getUserDao (String emailId){
		if (emailId == null) throw new IllegalArgumentException("Invalid email ID passed, expected value, actual null");
		Session ses = HibernateUtil.currentSession();
		try {
			Criteria crit =  ses.createCriteria(User.class);	
			crit.add(Restrictions.eq("emailId", emailId).ignoreCase());
			User u = (User)crit.uniqueResult();
			return u;
		} finally {
			HibernateUtil.closeSession();
		}
	}
	
	/**
	 * Get user Id from the email Id that is passed as parameter 
	 * 
	 * @param emailId
	 * @return User Id of the user who is associated with the email Id shared as the parameter
	 * @throws IllegalArgumentException in case the email ID provided is <code>Null</code>
	 */
	
	public Integer getUserId (String emailId){
		if (emailId == null) throw new IllegalArgumentException("Invalid email ID passed, expected value, actual null");
		Session ses = HibernateUtil.currentSession();
		try{
			Criteria crit =  ses.createCriteria(User.class);	
			crit.add(Restrictions.eq("emailId", emailId).ignoreCase());
			User u = (User)crit.uniqueResult();
			return u.getUserId();
		} finally {
			HibernateUtil.closeSession();
		}
	}
	
	/**
	 * Gets all the blogs created by the user, for the user whose email ID is provided as the parameter
	 * 
	 * @param emailId
	 * @return Blogs created by the user in case there are blogs created for the user identified by the Email ID passes asa parameter
	 * @throws IllegalArgumentException in case the email ID provided is <code>Null</code>
	 */
	
	public List<Blog> getBlogsDao (String emailId){
		if (emailId == null) throw new IllegalArgumentException("Invalid email ID passed, expected value, actual null");
		Session ses = HibernateUtil.currentSession();
		try {
			int uid = getUserId(emailId);
			Criteria crit =  ses.createCriteria(Blog.class);	
			crit.add(Restrictions.idEq(uid));
			List<Blog> b = crit.list();
			return b;
		} finally {
			HibernateUtil.closeSession();
		}
	}
	
	/**
	 * Post / create a new user. The user Id is auto-generated and so in join date. Parameters even if provided will be ignored / overriddgen.
	 *  
	 * @param u
	 * @return userId of the object that is created.
	 */
	
	public Integer createUser (User u) {
		
		int id=0;
		System.out.println("Creating user: "+u.getFirstName()+" "+u.getLastName()+" from "+u.getCity()+", "+u.getState()+", "+u.getCountry()+" with EmailID :"+u.getEmailId()+" and Age: "+u.getAge());
		Session ses = HibernateUtil.currentSession();
		try {
			Transaction tx = ses.beginTransaction();
			ses.save(u);
			id=u.getUserId();
			tx.commit();
		}finally{
			HibernateUtil.closeSession();
		}
		return id;
	}

}
