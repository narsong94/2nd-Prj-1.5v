package com.prj.web.entity;

import java.util.Date;

public class Advice {
	private int id;
	private String title;
	private String content;
	private int hit;
	private Date date;
	private String writerId;
	
	public Advice() {
	}
	
	public Advice(int id, String title, String content, int hit, Date date, String writerId) {
		super();
		this.id = id;
		this.title = title;
		this.content = content;
		this.hit = hit;
		this.date = date;
		this.writerId = writerId;
	}
	public Advice(String title, String content, String writerId) {
		this.title = title;
		this.content = content;
		this.writerId = writerId;
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
	public int getHit() {
		return hit;
	}
	public void setHit(int hit) {
		this.hit = hit;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getWriterId() {
		return writerId;
	}

	public void setWriterId(String writerId) {
		this.writerId = writerId;
	}
	
	
}
