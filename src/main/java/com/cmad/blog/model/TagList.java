package com.cmad.blog.model;

import java.util.Set;

public class TagList {
	
	private Integer tagId;
	private String tagString;
	private Set<Tag> tags;
	public Integer getTagId() {
		return tagId;
	}
	public void setTagId(Integer tagId) {
		this.tagId = tagId;
	}
	public String getTagString() {
		return tagString;
	}
	public void setTagString(String tagString) {
		this.tagString = tagString;
	}
	public Set<Tag> getTags() {
		return tags;
	}
	public void setTags(Set<Tag> tags) {
		this.tags = tags;
	}
	@Override
	public String toString() {
		return "TagList [tagId=" + tagId + ", tagString=" + tagString + ", tags=" + tags + "]";
	}
	
	

}
