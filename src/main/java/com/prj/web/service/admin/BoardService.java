package com.prj.web.service.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.prj.web.dao.TipDao;
import com.prj.web.dao.FreeDao;
import com.prj.web.entity.Free;
import com.prj.web.entity.Tip;

public class BoardService {
	
	@Autowired
	private FreeDao freeDao;
	
	@Autowired
	private TipDao TipDao;
	
	/*-------------- 자유 게시판 --------------*/
	
	public List<Free> getFreeList(int page, String query){
		return freeDao.getList(page, query);
	}

	public Free getFree(String id) {
		return freeDao.getFree(id);
	}

	public Free getFreePrev(String id) {
		return freeDao.getFreePrev(id);
	}

	public Free getFreeNext(String id) {
		return freeDao.getFreeNext(id);
	}
	
	/*-------------- Tip 게시판 --------------*/

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

	public int insert(String title, String content, String writerId) {
		return TipDao.insert(title, content, writerId);
	}

	public int getTipNextId() {
		return TipDao.getNextId();
	}

	
}
