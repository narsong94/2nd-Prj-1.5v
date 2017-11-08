package com.prj.web.dao.spring;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import com.prj.web.dao.InfoDao;
import com.prj.web.entity.Free;
import com.prj.web.entity.Info;
import com.prj.web.entity.Tip;

public class SpringInfoDao implements InfoDao{

	@Autowired
	private JdbcTemplate template;
	
	@Override
	public List<Info> getList(int page) {
		String sql = "select * from Info order by date DESC limit ?,10";

		List<Info> list = template.query(sql, new Object[] { (page - 1) * 10 },
				BeanPropertyRowMapper.newInstance(Info.class));

		return list;
	}

	@Override
	public Info getInfo(String id) {
		String sql = "select * from Info where id = ?";

		Info info = template.queryForObject(sql, new Object[] { id }, BeanPropertyRowMapper.newInstance(Info.class));
		return info;
	}

	@Override
	public Info getInfoPrev(String id) {
		String sql = "select * from Info where id < CAST(? as UNSIGNED) order by date DESC limit 1";
		
		try {
			return template.queryForObject(sql, new Object[] { id }, BeanPropertyRowMapper.newInstance(Info.class));
		} catch(EmptyResultDataAccessException e) {			
			return null;
		}
	}

	@Override
	public Info getInfoNext(String id) {
		String sql = "select * from Info where id > CAST(? as UNSIGNED) order by date ASC limit 1";

		try {
			return template.queryForObject(sql, new Object[] { id }, BeanPropertyRowMapper.newInstance(Info.class));
		} catch(EmptyResultDataAccessException e) {			
			return null;
		}
	}
	
	@Override
	public int getInfoCount() {
		String sql = "SELECT COUNT(id) as count FROM Info";
		
		int count = template.queryForObject(sql, Integer.class);
		return count;
	}

	@Override
	public int update(String id, Info info) {
		String sql = "update Info set title = ?, content = ? where id = ?;";

		int result = template.update(sql, info.getTitle(), info.getContent(), id);
		
		return result;
	}

	@Override
	public int insert(Info info) {
		String sql = "insert into Info(id, title, content, writerId, hit) values(?, ?, ?, ?, 0);";
		
		int insert = template.update(sql, 
						getNextId(), 
						info.getTitle(), 
						info.getContent(), 
						info.getWriterId());		
				
		return insert;
	}
	
	@Override
	public int insert(String title, String content, String writerId) {
		return insert(new Info(title, content, writerId));
	}

	@Override
	public int delete(String id) {
		String sql = "delete from Info where id = ?";
		
		int del = template.update(sql, id);
		
		return del;
	}

	@Override
	public int getNextId() {
		String sql = "select ifnull(MAX(CAST(id as unsigned)),0)+1 from Info";
		
		int nextId = template.queryForObject(sql, Integer.class);
		System.out.println(nextId);
		
		return nextId;
	}


}
