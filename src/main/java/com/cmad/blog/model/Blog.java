package com.cmad.blog.model;

import java.util.Date;
import java.util.Set;

public class Blog {
	
	
	private Integer blogId;
	private String title;
	private Date entryDate=new Date();
	private String content;
	private Integer rank;
	private User user;
	private Set<Comment> comments;
	private Set<Tag> tags;
	
	private Blog(){
		user = new User();
	}
	
	public Integer getBlogId() {
		return blogId;
	}
	public void setBlogId(Integer blogId) {
		this.blogId = blogId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Date getEntryDate() {
		return entryDate;
	}
	public void setEntryDate(Date entryDate) {
		this.entryDate = entryDate;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Integer getRank() {
		return rank;
	}
	public void setRank(Integer rank) {
		this.rank = rank;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Set<Comment> getComments() {
		return comments;
	}
	public void setComments(Set<Comment> comments) {
		this.comments = comments;
	}
	public Set<Tag> getTags() {
		return tags;
	}
	public void setTags(Set<Tag> tags) {
		this.tags = tags;
	}
	@Override
	public String toString() {
		return "Blog [blogId=" + blogId + ", title=" + title + ", entryDate=" + entryDate + ", content=" + content
				+ ", rank=" + rank + ", user=" + user + ", comments=" + comments + ", tags=" + tags + "]";
	}

}
