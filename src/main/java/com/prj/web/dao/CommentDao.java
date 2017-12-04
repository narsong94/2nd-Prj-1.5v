package com.prj.web.dao;

import java.util.List;

import com.prj.web.entity.Comment;

public interface CommentDao {

	int adviceCommentInsert(String content, String advice_id, String writer_id);

	List<Comment> getAdviceUpdateCommentList(String adviceId, String cId);

	List<Comment> getAdviceCommentList(String id);

}
