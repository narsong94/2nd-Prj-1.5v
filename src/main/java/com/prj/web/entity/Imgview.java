package com.prj.web.entity;

public class Imgview {

	
	private int id;
	private String title;
	private String content;
	private int likeNum;
	
	public Imgview() {
		
	}
	
	
	public Imgview(int id, String title, String content, int likeNum) {
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
