package com.cmad.blog.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.cmad.blog.model.Tag;
import com.cmad.blog.model.TagList;

public class TagDao {

	/**
	 * Get the keyword object for the keyId provided.
	 * 
	 * @param keyId for the keyword that needs to be fetched.
	 * @return KeywordSearch object that matches the provided keyword
	 * @throws IllegalArgumentException in case the keyword provided for search is <code>Null</code>
	 */
	
	public Tag getKeywordDao (Integer keyId){
		if (keyId == null) throw new IllegalArgumentException("Invalid keyId passed, expected value, passed Null");
		Session ses = HibernateUtil.currentSession();
		try {
			Criteria crit =  ses.createCriteria(Tag.class);
			crit.add(Restrictions.idEq(keyId));
			Tag k = (Tag)crit.uniqueResult();
			return k;
		} finally {
			HibernateUtil.closeSession();
		}
	}
	
	/**
	 * Get the complete list of all the keywords in the database
	 *
	 * @return List of all KeywordSearch objects in the database
	 */
	
	public List<Tag> getKeywordsDao() {
		Session ses = HibernateUtil.currentSession();
		try {
			return ses.createCriteria(Tag.class).list();
		} finally {
			HibernateUtil.closeSession();
		}
	}
	
	
	/**
	 * Get keyId from the searchKey that is passed as parameter 
	 * 
	 * @param searchKey
	 * @return keyId of the searchKey string which is passed as the parameter
	 * @throws IllegalArgumentException in case the searchKey provided is <code>Null</code>
	 */
	
	public Integer getKeyId (String searchKey){
		if (searchKey == null) throw new IllegalArgumentException("Invalid searchKey passed, expected value, actual null");
		Session ses = HibernateUtil.currentSession();
		
			Criteria crit =  ses.createCriteria(TagList.class);	
			crit.add(Restrictions.eq("searchKey",searchKey).ignoreCase());
			TagList s = (TagList)crit.uniqueResult();
			return s.getTagId();
		 
	}
	
	/**
	 * Get the keyword object for the Search Keystring provided.
	 * 
	 * @param searchKey for the keyword object that needs to be fetched.
	 * @return KeywordSearch object that matches the provided keyword
	 * @throws IllegalArgumentException in case the keyword provided for search is <code>Null</code>
	 */
	
	public TagList getKeywordDao (String searchKey){
		if (searchKey == null) throw new IllegalArgumentException("Invalid searchKey passed, expected value, passed Null");
		int keyId = getKeyId(searchKey);
		Session ses = HibernateUtil.currentSession();
		try {
			Criteria crit =  ses.createCriteria(Tag.class);
			crit.add(Restrictions.idEq(keyId));
			TagList k = (TagList)crit.uniqueResult();
			return k;
		} finally {
			HibernateUtil.closeSession();
		}
	}
	
	/**
	 * Post / create a new a keyword. The keyword Id is auto-generated. Parameters even if provided will be ignored / overridden.
	 *  
	 * @param Keyword object
	 * @return keyId of the entry that is made is returned
	 */
	
	public Integer createKeyword (TagList s) {
		int id = 0;
		System.out.println("Creating keyword entry: "+s.getTagString());
		Session ses = HibernateUtil.currentSession();
		if (getKeyId(s.getTagString()) == null){
			try {
			Transaction tx = ses.beginTransaction();
			ses.save(s);
			id = s.getTagId();
			tx.commit();
		}finally{
			HibernateUtil.closeSession();
		}
		return id;
		}
		id = getKeyId(s.getTagString());
		return id;
		
	}
	
	public Integer createKeyword (Tag k) {
		int id = 0;
		System.out.println("Creating keyword entry: "+k.getTagId()+" and it is associated to "+k.getBlogId());
		Session ses = HibernateUtil.currentSession();
		try {
			Transaction tx = ses.beginTransaction();
			ses.save(k);
			id = k.getTagId();
			tx.commit();
		}finally{
			HibernateUtil.closeSession();
		}
		return id;
	}
	
}
