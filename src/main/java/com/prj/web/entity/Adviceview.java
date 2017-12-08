package com.prj.web.entity;

public class Adviceview {

	
	private int id;
	private String title;
	private int likeNum;
	private String content;
	
	public Adviceview() {
		
	}
	
	
	public Adviceview(int id, String title, String content, int likeNum) {
		super();
		this.id = id;
		this.title = title;
		this.content = content;
		this.likeNum = likeNum;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
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
	
	
}
