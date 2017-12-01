package com.prj.web.entity;

public class VotingLike {
	private int id;
	private int like1;
	private int like2;
	private int like3;
	
	public VotingLike() {
	}

	public VotingLike(int id, int like1, int like2, int like3) {
		super();
		this.id = id;
		this.like1 = like1;
		this.like2 = like2;
		this.like3 = like3;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getLike1() {
		return like1;
	}

	public void setLike1(int like1) {
		this.like1 = like1;
	}

	public int getLike2() {
		return like2;
	}

	public void setLike2(int like2) {
		this.like2 = like2;
	}

	public int getLike3() {
		return like3;
	}

	public void setLike3(int like3) {
		this.like3 = like3;
	}
	
	
}
