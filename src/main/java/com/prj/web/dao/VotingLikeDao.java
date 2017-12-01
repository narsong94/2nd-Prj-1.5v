package com.prj.web.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.prj.web.entity.Voting;
import com.prj.web.entity.VotingLike;

public interface VotingLikeDao {

	//VotingLike updateLike(String id);

	int getVotingLike(String id, String num);

	//int setVotingLike(int id);

	//VotingLike updateLike(String id, String num);

	int getVoteUser(String userId);

	int setVotingUserLike(String id, String userId, String num);

	VotingLike getVotingLike(String id);


}
