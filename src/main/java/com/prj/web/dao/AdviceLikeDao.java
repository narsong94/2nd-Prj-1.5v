package com.prj.web.dao;

public interface AdviceLikeDao {
	int insert(String adviceId, String writerId);
	int updateLikeCount(String adviceId);
	int check(String adviceId, String writerId);
	int count(String adviceId, String writerId);
}
