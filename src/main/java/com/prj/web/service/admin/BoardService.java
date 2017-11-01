package com.prj.web.service.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.prj.web.dao.FreeDao;
import com.prj.web.entity.Free;

public class BoardService {
	
	@Autowired
	private FreeDao freeDao;
	
	public List<Free> getFreeList(int page){
		return freeDao.getList(page);
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
	
}
