package com.cmad.blog.model;


import java.util.Date;


public class Comment {
	
	private User user;
	public Comment(){
		user = new User();
	}

	private Integer commentId;
	private Date entryDate=new Date();
	private String content="";
	private Integer parentId;
	private Integer rank;
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Integer getCommentId() {
		return commentId;
	}
	public void setCommentId(Integer commentId) {
		this.commentId = commentId;
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
	public Integer getParentId() {
		return parentId;
	}
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}
	public Integer getRank() {
		return rank;
	}
	public void setRank(Integer rank) {
		this.rank = rank;
	}
	
	@Override
	public String toString() {
		return "Comment [user=" + user + ", commentId=" + commentId + ", entryDate=" + entryDate
				+ ", content=" + content + ", parentId=" + parentId + ", rank=" + rank + "]";
	}
	
	
	
	

	
	
	

}
