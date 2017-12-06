package com.prj.web.dao;

public interface TipLikeDao {
	int insert(String tipId, String writerId);
	int updateLikeCount(String tipId);
	int check(String tipId, String writerId);
	int Count(String tipId, String writerId);
}
