package com.prj.web.dao;

import java.util.List;

import com.prj.web.entity.Honor;

public interface HonorDao {

	List<Honor> getHonorList(String query);

	int insert(Honor Honor);

	int insert(String content, String writerId);

	int getNextId();

}
