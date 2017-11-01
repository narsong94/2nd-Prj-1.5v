package com.prj.web.dao.spring;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import com.prj.web.dao.FreeDao;
import com.prj.web.entity.Free;

public class SpringFreeDao implements FreeDao {

	@Autowired
	private JdbcTemplate template;

	@Override
	public List<Free> getList(int page) {
		String sql = "select * from Free limit ?,10";

		List<Free> list = template.query(sql, new Object[] { (page - 1) * 10 },
				BeanPropertyRowMapper.newInstance(Free.class));

		return list;
	}

	@Override
	public Free getFree(String id) {
		String sql = "select * from Notice where id = ?";

		Free free = template.queryForObject(sql, new Object[] { id }, BeanPropertyRowMapper.newInstance(Free.class));
		return free;
	}

	@Override
	public Free getFreePrev(String id) {
		String sql = "select * from Free where id < CAST(? as UNSIGNED) order by regDate DESC limit 1";

		Free prev = template.queryForObject(sql, new Object[] { id }, BeanPropertyRowMapper.newInstance(Free.class));
		return prev;
	}

	@Override
	public Free getFreeNext(String id) {
		String sql = "select * from Free where id > CAST(? as UNSIGNED) order by regDate ASC limit 1";

		Free next = template.queryForObject(sql, new Object[] { id }, BeanPropertyRowMapper.newInstance(Free.class));
		return next;
	}

}
