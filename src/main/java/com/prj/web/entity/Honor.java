package com.prj.web.entity;

import java.util.Date;

public class Honor {
	private int id;
	private String content;
	private String tag;
	private int likeNum;
	private String writerId;
	private Date date;
	
	public Honor() {
		// TODO Auto-generated constructor stub
	}
	
	public Honor(int id, String content, String tag, int likeNum, String writerId, Date date) {
		super();
		this.id = id;
		this.content = content;
		this.tag = tag;
		this.likeNum = likeNum;
		this.writerId = writerId;
		this.date = date;
	}
	public Honor(int id, String content, int likeNum, String writerId) {
		super();
		this.id = id;
		this.content = content;
		this.likeNum = likeNum;
		this.writerId = writerId;
	}
	public Honor(String content, String writerId) {
		this.content = content;
		this.writerId = writerId;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getLikeNum() {
		return likeNum;
	}
	public void setLikeNum(int likeNum) {
		this.likeNum = likeNum;
	}
	public String getWriterId() {
		return writerId;
	}
	public void setWriterId(String writerId) {
		this.writerId = writerId;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}
	
	
}
