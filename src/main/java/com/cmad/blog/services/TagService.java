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
import com.cmad.blog.model.Tag;
import com.cmad.blog.model.TagList;

@Path("/keyword")

public class TagService {
	
	private TagDao tagDao;
	
	public TagService() {
		tagDao = new TagDao();
	}
	
	/**
	 * Get the keyword object for the keyId provided.
	 * 
	 * @param keyId for the keyword that needs to be fetched.
	 * @return KeywordSearch object that matches the provided keyword
	 * @throws IllegalArgumentException in case the keyword provided for search is <code>Null</code>
	 */
	
	@GET
	@Path("/{param}")
	@Produces({MediaType.APPLICATION_JSON})
	public Tag getKeyword(@PathParam("param") Integer keyId) {
		if (keyId == null) throw new IllegalArgumentException("Invalid keyId passed, expected value, passed Null");
		Tag k = tagDao.getKeywordDao(keyId);
		return k;
	}
	
	/**
	 * Get the complete list of all the keywords in the database
	 *
	 * @return List of all KeywordSearch objects in the database
	 */
	
	@GET
	@Produces({MediaType.APPLICATION_JSON})
	public List<Tag> getKeywords() {
		List<Tag> keywords = tagDao.getKeywordsDao();
		return keywords;
	}
	
	/**
	 * Get the keyword object for the Search Keystring provided.
	 * 
	 * @param searchKey for the keyword object that needs to be fetched.
	 * @return KeywordSearch object that matches the provided keyword
	 * @throws IllegalArgumentException in case the keyword provided for search is <code>Null</code>
	 */
	
	@GET
	@Path("/?tag={param}")
	@Produces({MediaType.APPLICATION_JSON})
	public TagList getKeyword(@PathParam("param") String searchKey) {
		if (searchKey == null) throw new IllegalArgumentException("Invalid searchKey passed, expected value, passed Null");
		TagList k = tagDao.getKeywordDao(searchKey);
		return k;
	}
	
	/**
	 * Post / create a new a keyword. The keyword Id is auto-generated. Parameters even if provided will be ignored / overridden.
	 *  
	 * @param Keyword object
	 * @return keyId of the entry that is made is returned
	 */
	
	@POST
	@Consumes({MediaType.APPLICATION_JSON})
	public Integer createKeyword (TagList s) {
		int id = 0;
		tagDao.createKeyword(s);
		return id;
	}
	
	public Integer createKeyword (Tag k) {
		int id = 0;
		tagDao.createKeyword(k);
		return id;
	}

}
