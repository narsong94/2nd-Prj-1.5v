package com.prj.web.service.admin;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.prj.web.dao.TipDao;
import com.prj.web.dao.AdviceDao;
import com.prj.web.dao.FreeDao;
import com.prj.web.dao.InfoDao;
import com.prj.web.dao.VotingDao;
import com.prj.web.dao.VotingLikeDao;
import com.prj.web.dao.VotingCommentDao;
import com.prj.web.dao.AdviceCommentDao;
import com.prj.web.dao.InfoCommentDao;
import com.prj.web.dao.TipCommentDao;
import com.prj.web.dao.AdviceLikeDao;
import com.prj.web.dao.TipLikeDao;
import com.prj.web.dao.InfoLikeDao;
import com.prj.web.dao.UserDao;
import com.prj.web.entity.Voting;
import com.prj.web.entity.VotingLike;
import com.prj.web.entity.Advice;
import com.prj.web.entity.Comment;
import com.prj.web.entity.DramaObject;
import com.prj.web.entity.Dramaview;
import com.prj.web.entity.Free;
import com.prj.web.entity.Imgview;
import com.prj.web.entity.Info;
import com.prj.web.entity.Tip;
import com.prj.web.entity.User;

public class BoardService {
	
	@Autowired
	private FreeDao freeDao;
	
	@Autowired
	private TipDao TipDao;
	
	@Autowired
	private InfoDao InfoDao;
	
	@Autowired
	private VotingDao VotingDao;
	
	@Autowired
	private AdviceDao AdviceDao;
	
	@Autowired
	private VotingLikeDao VotingLikeDao;
	
	@Autowired
	private VotingCommentDao VotingCommentDao;
	@Autowired
	private AdviceCommentDao AdviceCommentDao;
	@Autowired
	private InfoCommentDao InfoCommentDao;
	@Autowired
	private TipCommentDao TipCommentDao;
	
	@Autowired
	private AdviceLikeDao AdviceLikeDao;
	@Autowired
	private InfoLikeDao InfoLikeDao;
	@Autowired
	private TipLikeDao TipLikeDao;
	
	@Autowired
	private UserDao UserDao;
	
	
	/*--------------------------------- 자유 게시판 ---------------------------------*/
	
	public List<Free> getFreeList(int page, String query){
		return freeDao.getList(page, query);
	}

	public Free getFree(String id) {
		return freeDao.getFree(id);
	}
	
	public int getFreeCount() {
		return freeDao.getFreeCount();
	}

	public Free getFreePrev(String id) {
		return freeDao.getFreePrev(id);
	}

	public Free getFreeNext(String id) {
		return freeDao.getFreeNext(id);
	}

	public int freeUpdate(String id, Free free) {
		return freeDao.update(id, free);
	}

	public int getFreeNextId() {
		return freeDao.getNextId();
	}

	public int freeInsert(String title, String content, String writerId) {
		return freeDao.insert(title, content, writerId);
	}

	public int freeDel(String id) {
		return freeDao.delete(id);
	}
	
	public int updateFreeHit(String id) {
		return freeDao.updateHit(id);
	}
	
	/*--------------------------------- Tip 게시판 ---------------------------------*/

	public List<Tip> getTipList(int page, String query) {
		return TipDao.getList(page, query);
	}

	public Tip getTip(String id) {
		return TipDao.getTip(id);
	}

	public Tip getTipPrev(String id) {
		return TipDao.getTipPrev(id);
	}

	public Tip getTipNext(String id) {
		return TipDao.getTipNext(id);
	}

	public int getTipCount() {
		return TipDao.getTipCount();
	}
	
	public int tipUpdate(String id, Tip tip) {
		return TipDao.update(id, tip);
	}

	public int tipInsert(String title, String content, String writerId) {
		return TipDao.insert(title, content, writerId);
	}

	public int getTipNextId() {
		return TipDao.getNextId();
	}

	public int tipDel(String id) {
		return TipDao.delete(id);
	}

	public int updateTipHit(String id) {
		return TipDao.updateHit(id);
	}
	
	// 댓글 부분
	public int tipCommentInsert(String content,String tipId ,String writerId) {
		   return TipCommentDao.commentInsert(content, tipId, writerId);
	}
			  
	public List<Comment> getTipCommentList(String tipId) {
		return TipCommentDao.getCommentList(tipId);
	} 
			   
	public List<Comment> getTipUpdateCommentList(String tipId) {
		   return TipCommentDao.getUpdateCommentList(tipId);
	}
	
	// 좋아요 부분
	public Integer tipLikeCheck(String tip_id, String writer_id) {
		return  TipLikeDao.check(tip_id,writer_id);
	}

	public int tipLikeCountUp(String tip_id) {
		return TipLikeDao.updateLikeCount(tip_id);
	}

	public int tipLikeInsert(String tip_id, String writer_id) {
		return TipLikeDao.insert(tip_id,writer_id);
	}

	public int tipLikecount(String tip_id, String writer_id) {
		return  TipLikeDao.Count(tip_id,writer_id);
	}

	
	/*--------------------------------- Info 게시판 ---------------------------------*/

	public List<Info> getInfoList(int page) {
		return InfoDao.getList(page);
	}

	public Info getInfo(String id) {
		return InfoDao.getInfo(id);
	}

	public Info getInfoPrev(String id) {
		return InfoDao.getInfoPrev(id);
	}

	public Info getInfoNext(String id) {
		return InfoDao.getInfoNext(id);
	}

	public int infoUpdate(String id, Info info) {
		return InfoDao.update(id, info);
	}

	public int infoDel(String id) {
		return InfoDao.delete(id);
	}

	public int infoInsert(String title, String content, String writerId) {
		return InfoDao.insert(title, content, writerId);
	}

	public int getInfoNextId() {
		return InfoDao.getNextId();
	}

	public int getInfoCount() {
		return InfoDao.getInfoCount();
	}
	
	public int updateInfoHit(String id) {
		return InfoDao.updateHit(id);	
	}
	
	// 댓글 부분
	public int infoCommentInsert(String content,String infoId ,String writerId) {
	    return InfoCommentDao.commentInsert(content, infoId, writerId);
	}
		  
	public List<Comment> getInfoCommentList(String infoId) {
		return InfoCommentDao.getCommentList(infoId);
	} 
		   
	public List<Comment> getInfoUpdateCommentList(String infoId) {
	    return InfoCommentDao.getUpdateCommentList(infoId);
	}
	
	// 지워니가 한 부분
	public List<Imgview> getId() {
		return InfoDao.getId();
	}

	public int getDramaNextId() {
		return InfoDao.getDramaNextId();
	}

	public int infoDramaInsert(String name, String content, String writerId) {
		return InfoDao.dramaInsert(name, content, writerId);
	}

	public List<Dramaview> getdramaId() {
		return InfoDao.getDramaId();
	}

	public List<DramaObject> getDramaList(int page) {
		return InfoDao.getDramaList(page);
	}
	
	// 좋아요 부분

	public int infoLikeCountUp(String info_id) {
		return InfoLikeDao.updateLikeCount(info_id);
	}

	public int infoLikeInsert(String info_id, String writer_id) {
		return InfoLikeDao.insert(info_id,writer_id);
	}

	public Integer infoLikeCheck(String info_id, String writer_id) {
		return  InfoLikeDao.check(info_id,writer_id);
	}

	public int infoLikecount(String info_id, String writer_id) {
		return  InfoLikeDao.Count(info_id,writer_id);
	}
	
	/*--------------------------------- Voting 게시판 ---------------------------------*/

	public List<Voting> getVotingList(int page, String query) {
		return VotingDao.getList(page, query);
	}

	public Voting getVoting(String id) {
		return VotingDao.getVoting(id);
	}

	public Voting getVotingPrev(String id) {
		return VotingDao.getVotingPrev(id);
	}

	public Voting getVotingNext(String id) {
		return VotingDao.getVotingNext(id);
	}

	public int getVotingCount() {
		return VotingDao.getVotingCount();
	}
	
	public int votingUpdate(String id, Voting voting) {
		return VotingDao.update(id, voting);
	}

	public int votingInsert(String title, String content, String writerId) {
		return VotingDao.insert(title, content, writerId);
	}

	public int getVotingNextId() {
		return VotingDao.getNextId();
	}

	public int votingDel(String id) {
		return VotingDao.delete(id);
	}
	
	public int updateVotingHit(String id) {
		return VotingDao.updateHit(id);	
	}
	
	public List<String> getVotingImgs(String id) {
		return VotingDao.getStringImgs(id);	
	}
	
	public List<String> getVotingImgs(int id) {
		return VotingDao.getImgs(id);	
	}
	
/*	public VotingLike votingLikeUp(String id) {
		return VotingLikeDao.updateLike(id);
	}*/

	/*public VotingLike votingLikeUp(String id, String num) {
		return VotingLikeDao.updateLike(id, num);
	}*/
	
	public int getVotingLike(String id, String num) {
		return VotingLikeDao.getVotingLike(id,num);
	}
	
	public VotingLike getVotingLike(String id) {
		return VotingLikeDao.getVotingLike(id);
	}

	public int getVoteUser(String userId, String vId) {
		return VotingLikeDao.getVoteUser(userId, vId);
	}

	public int setVotingUserLike(String id, String userId, String num) {
		return VotingLikeDao.setVotingUserLike(id, userId, num);
	}
	
	// 댓글 부분
	public int votingCommentInsert(String content,String votingId ,String writerId) {
	    return VotingCommentDao.commentInsert(content, votingId, writerId);
	}
	  
	public List<Comment> getVotingCommentList(String votingId) {
		return VotingCommentDao.getCommentList(votingId);
	} 
	   
	public List<Comment> getVotingUpdateCommentList(String votingId) {
	    return VotingCommentDao.getUpdateCommentList(votingId);
	}

	/*--------------------------------- Advice 게시판 ---------------------------------*/

	public List<Advice> getAdviceList(int page, String query) {
		return AdviceDao.getList(page, query);
	}

	public Advice getAdvice(String id) {
		return AdviceDao.getAdvice(id);
	}

	public Advice getAdvicePrev(String id) {
		return AdviceDao.getAdvicePrev(id);
	}

	public Advice getAdviceNext(String id) {
		return AdviceDao.getAdviceNext(id);
	}

	public int getAdviceCount() {
		return AdviceDao.getAdviceCount();
	}
	
	public int adviceUpdate(String id, Advice advice) {
		return AdviceDao.update(id, advice);
	}

	public int adviceInsert(String title, String content, String writerId) {
		return AdviceDao.insert(title, content, writerId);
	}

	public int getAdviceNextId() {
		return AdviceDao.getNextId();
	}

	public int adviceDel(String id) {
		return AdviceDao.delete(id);
	}

	public int updateAdviceHit(String id) {
		return AdviceDao.updateHit(id);
	}

	public List<Advice> getPrevAdviceList(String id, Date date) {
		return AdviceDao.getPrevAdviceList(id, date);
	}

	public User getWriterUser(String id) {
		return VotingDao.getWriterUser(id);
	}

	// 댓글 부분
	public int adviceCommentInsert(String content,String adviceId ,String writerId) {
	    return AdviceCommentDao.commentInsert(content, adviceId, writerId);
	}
		  
	public List<Comment> getAdviceCommentList(String adviceId) {
		return AdviceCommentDao.getCommentList(adviceId);
	} 
		   
	public List<Comment> getAdviceUpdateCommentList(String adviceId) {
	    return AdviceCommentDao.getUpdateCommentList(adviceId);
	}

	// 좋아요 부분
	public int adviceLikecount(String advice_id, String writer_id) {
		return AdviceLikeDao.count(advice_id,writer_id);
	}

	public int adviceLikeCountUp(String advice_id) {
		return AdviceLikeDao.updateLikeCount(advice_id);
	}

	public int adviceLikeInsert(String advice_id, String writer_id) {
		return AdviceLikeDao.insert(advice_id,writer_id);
	}

	public int  adviceLikeCheck(String advice_id, String writer_id) {
		return AdviceLikeDao.check(advice_id,writer_id);
	}


}
