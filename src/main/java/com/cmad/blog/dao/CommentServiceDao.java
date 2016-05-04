package com.cmad.blog.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.cmad.blog.model.Comment;
import com.cmad.blog.model.User;

public class CommentServiceDao {
	
	public Comment getCommentDao (Integer commentId){
		Session ses = HibernateUtil.currentSession();
		try {
			Criteria crit =  ses.createCriteria(User.class);
			crit.add(Restrictions.idEq(commentId));
			Comment c = (Comment)crit.uniqueResult();
			return c;
		} finally {
			HibernateUtil.closeSession();
		}
	}
	
	public List<Comment> getCommentsDao() {
		Session ses = HibernateUtil.currentSession();
		try {
			return ses.createCriteria(Comment.class).list();
		} finally {
			HibernateUtil.closeSession();
		}
	}
	
	public void createComment (Comment c) {
		System.out.println("Creating comment: "+c.getContent()+ " entered on "+c.getEntryDate());
		Session ses = HibernateUtil.currentSession();
		try {
			Transaction tx = ses.beginTransaction();
			ses.save(c);
			tx.commit();
		}finally{
			HibernateUtil.closeSession();
		}
	}

}
