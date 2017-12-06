package com.prj.web.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.prj.web.entity.Drama;
import com.prj.web.entity.DramaObject;
import com.prj.web.entity.Dramaview;
import com.prj.web.entity.Imgview;
import com.prj.web.entity.Info;

public interface InfoDao {
	List<Info> getList(@Param("page") int page);

	Info getInfo(String id);

	Info getInfoPrev(String id);

	Info getInfoNext(String id);

	int update(String id, Info info);
	int updateHit(String id);
	
	int delete(String id);

	int insert(String title, String content, String writerId);

	int insert(Info info);

	int getNextId();

	int getInfoCount();

	List<Imgview> getId();

	int getDramaNextId();

	int dramaInsert(String name, String content, String writerId);

	int dramaInsert(Drama drama);

	List<Dramaview> getDramaId();

	List<DramaObject> getDramaList(int page);
}
