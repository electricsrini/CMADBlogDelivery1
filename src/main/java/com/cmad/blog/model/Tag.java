package com.cmad.blog.model;

import java.io.Serializable;

public class Tag implements Serializable {
	
	private Integer tagId;
	private Integer blogId;
	public Integer getTagId() {
		return tagId;
	}
	public void setTagId(Integer tagId) {
		this.tagId = tagId;
	}
	public Integer getBlogId() {
		return blogId;
	}
	public void setBlogId(Integer blogId) {
		this.blogId = blogId;
	}
	@Override
	public String toString() {
		return "Tag [tagId=" + tagId + ", blogId=" + blogId + "]";
	}

}
