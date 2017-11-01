package com.prj.web.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.prj.web.entity.Free;

public interface FreeDao {
	List<Free> getList(@Param("page") int page);
	Free getFree(String id);
	Free getFreePrev(String id);
	Free getFreeNext(String id);
}
