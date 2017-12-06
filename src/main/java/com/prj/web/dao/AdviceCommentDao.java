package com.prj.web.dao;

import java.util.List;

import com.prj.web.entity.Comment;

public interface AdviceCommentDao {

	int commentInsert(String content, String adviceId, String writerId);

	List<Comment> getCommentList(String adviceId);

	List<Comment> getUpdateCommentList(String adviceId);

}
