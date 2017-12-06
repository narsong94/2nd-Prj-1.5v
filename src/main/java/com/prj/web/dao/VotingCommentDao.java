package com.prj.web.dao;

import java.util.List;

import com.prj.web.entity.Comment;

public interface VotingCommentDao {

	int commentInsert(String content, String infoId, String writerId);

	List<Comment> getCommentList(String infoId);

	List<Comment> getUpdateCommentList(String infoId);

}
