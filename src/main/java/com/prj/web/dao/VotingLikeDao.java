package com.prj.web.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.prj.web.entity.Voting;
import com.prj.web.entity.VotingLike;

public interface VotingLikeDao {

	int getVotingLike(String id, String num);

	int getVoteUser(String userId, String vId);

	int setVotingUserLike(String id, String userId, String num);

	VotingLike getVotingLike(String id);


}
