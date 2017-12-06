package com.prj.web.dao;

public interface InfoLikeDao {
	int insert(String infoId, String writerId);
	int updateLikeCount(String infoId);
	int check(String infoId, String writerId);
	int Count(String infoId, String writerId);
}
