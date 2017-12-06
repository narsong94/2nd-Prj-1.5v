package com.prj.web.dao.spring;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import com.prj.web.dao.TipCommentDao;
import com.prj.web.dao.TipDao;
import com.prj.web.dao.TipLikeDao;
import com.prj.web.entity.Comment;
import com.prj.web.entity.Free;
import com.prj.web.entity.Tip;

public class SpringTipDao implements TipDao, TipCommentDao, TipLikeDao {

	@Autowired
	private JdbcTemplate template;

	@Override
	public List<Tip> getList(int page, String query) {
		String sql = "select * from Tip where title like ? order by date DESC limit ?,10";

		List<Tip> list = template.query(sql, new Object[] { "%"+query+"%", (page - 1) * 10 },
				BeanPropertyRowMapper.newInstance(Tip.class));

		return list;
	}

	@Override
	public Tip getTip(String id) {
		String sql = "select * from Tip where id = ?";

		Tip tip = template.queryForObject(sql, new Object[] { id }, BeanPropertyRowMapper.newInstance(Tip.class));
		return tip;
	}

	@Override
	public Tip getTipPrev(String id) {
		String sql = "select * from Tip where id < CAST(? as UNSIGNED) order by date DESC limit 1";

		try {
			return template.queryForObject(sql, new Object[] { id }, BeanPropertyRowMapper.newInstance(Tip.class));
		} catch(EmptyResultDataAccessException e) {			
			return null;
		}
	}

	@Override
	public Tip getTipNext(String id) {
		String sql = "select * from Tip where id > CAST(? as UNSIGNED) order by date ASC limit 1";

		try {
			return template.queryForObject(sql, new Object[] { id }, BeanPropertyRowMapper.newInstance(Tip.class));
		} catch(EmptyResultDataAccessException e) {			
			return null;
		}
	}

	@Override
	public int getTipCount() {
		String sql = "SELECT COUNT(id) as count FROM Tip";
		
		int count = template.queryForObject(sql, Integer.class);
		return count;
	}

	@Override
	public int update(String id, Tip tip) {
		String sql = "update Tip set title = ?, content = ? where id = ?;";

		int result = template.update(sql, tip.getTitle(), tip.getContent(), id);
		
		return result;
	}
	
	@Override
	public int updateHit(String id) {
		String sql = "update Tip set hit = ifnull(hit,0)+1 where id = ?;";
		
		return template.update(sql, id);
	}

	@Override
	public int insert(String title, String content, String writerId) {
		return insert(new Tip(title, content, writerId));
	}

	@Override
	public int insert(Tip tip) {

		String sql = "insert into Tip(id, title, content, writerId, hit) values(?, ?, ?, ?, 0);";
		
		int insert = template.update(sql, 
						getNextId(), 
						tip.getTitle(), 
						tip.getContent(), 
						tip.getWriterId());		
				
		return insert;
	}

	@Override
	public int getNextId() {
		String sql = "select ifnull(MAX(CAST(id as unsigned)),0)+1 from Tip";
		
		int nextId = template.queryForObject(sql, Integer.class);
		System.out.println(nextId);
		
		return nextId;
	}

	@Override
	public int delete(String id) {
		String sql = "delete from Tip where id = ?";
		
		int del = template.update(sql, id);
		
		return del;
	}

	@Override
	public int commentInsert(String content, String tipId, String writerId) {
		String sql = "insert into TipComment(content,tipId,writerId) values(?,?,?)";
	      int result = template.update(sql,content,tipId,writerId);
	      
	      return result;
	}

	@Override
	public List<Comment> getCommentList(String tipId) {
		String sql = "select * from TipComment where tipId = ? order by id asc";
	      List<Comment> list = template.query(sql, new Object[] {tipId},
	            BeanPropertyRowMapper.newInstance(Comment.class));
	      return list;
	}

	@Override
	public List<Comment> getUpdateCommentList(String tipId) {
		String sql = "select * from TipComment where tipId = ? order by id asc";
	      List<Comment> list = template.query(sql, new Object[] {tipId},
	            BeanPropertyRowMapper.newInstance(Comment.class));
	      return list;
	}

	@Override
	public int insert(String tipId, String writerId) {
		System.out.println("insert - tipId , writerId : "+tipId+","+writerId);
		String sql = "insert into TipLikeWriterId(tipId,WriterId) values(?,?)";
		int result = template.update(sql, tipId, writerId);
		return result;
	}

	@Override
	public int updateLikeCount(String tipId) {
		String sql = "update Tip set likeNum=likeNum+1 where id =?";
		int result = template.update(sql, tipId);
		return result;
	}

	@Override
	public int check(String tipId, String writerId) {
		System.out.println("check - tipId , writerId : "+tipId+","+writerId);
		int id = 0;
		String sql = "select id from TipLikeWriterId where writerId = ? and tipId = ?";
		try {
			id = template.queryForObject(sql, new Object[] {writerId, tipId}, Integer.class);
			return id;
		} catch (EmptyResultDataAccessException e) {
			return 0;
		}catch (Exception e) {
			return -10;
		}
	}

	@Override
	public int Count(String tipId, String writerId) {
		int count = 0;
		String sql = "select likeNum from Tip where id = ?";
		try {
			count = template.queryForObject(sql, new Object[] {tipId}, Integer.class);
			return count;
		} catch (Exception e) {
			System.out.println("count조회에 실패하였습니다.");
			return 0;
		}
	}

}
