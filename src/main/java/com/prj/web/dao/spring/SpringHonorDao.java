package com.prj.web.dao.spring;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import com.prj.web.dao.HonorDao;
import com.prj.web.entity.Honor;

public class SpringHonorDao implements HonorDao{

	@Autowired
	private JdbcTemplate template;
	
	@Override
	public List<Honor> getHonorList(String query) {
		String sql = "select id from Honor where tag like ? order by date DESC";
		
		List<String> list = template.queryForList(sql,new Object[] {query}, String.class);
		
		for(int i=0; i<list.size();i++) {
			System.out.println("list"+list.get(i));

			System.out.println("img : "+ getHonorImgsrc(list.get(i)));
			System.out.println("title : " +getHonorTag(list.get(i)));
			
			//insert
			update(list.get(i),getHonorImgsrc(list.get(i)),getHonorTag(list.get(i)), getLikeNum(list.get(i)));

		}

		String sql2 = "select * from Honor";
		List<Honor> list2 = template.query(sql2,
				BeanPropertyRowMapper.newInstance(Honor.class));
		return list2;
	}
	
	@Override
	public int insert(String content, String writerId) {
		return insert(new Honor(content, writerId));
	}

	@Override
	public int insert(Honor honor) {

		String sql = "insert into Honor(id, content, writerId) values(?, ?, ?);";
		
		int insert = template.update(sql, 
						getNextId2(), 
						honor.getContent(), 
						honor.getWriterId());	
		
		/*String id = Integer.toString(honor.getId());
		
		try {
			update(id,getHonorImgsrc(id),"dd", getLikeNum(id));
		} catch(Exception e) {
			System.out.println("¿©±â³Ä?!?!?!?!");
		}*/
				
		return insert;
	}
	
	private int getNextId2() {
		String sql = "select ifnull(MAX(CAST(id as unsigned)),0)+1 from Honor";
		
		int id = template.queryForObject(sql, Integer.class);
		return id;
	}

	public int getNextId() {
		String sql = "select ifnull(MAX(CAST(id as unsigned)),0) from Honor";
		
		int id = template.queryForObject(sql, Integer.class);
		String id2 = Integer.toString(id);
		
		try {
			update(id2,getHonorImgsrc(id2),"dd", getLikeNum(id2));
		} catch(Exception e) {
			System.out.println("¿©±â³Ä?!?!?!?!");
		}
		return id;
	}
	/*
	public List<Honor> getHonorId() {
		String sql = "select id from Honor where tag like ? order by date DESC";
		
		List<String> list = template.queryForList(sql,String.class);
		
		for(int i=0; i<list.size();i++) {
			System.out.println("list"+list.get(i));

			System.out.println("img : "+ getHonorImgsrc(list.get(i)));
			System.out.println("title : " +getHonorTag(list.get(i)));
			
			//insert
			update(list.get(i),getHonorImgsrc(list.get(i)),getHonorTag(list.get(i)), getLikeNum(list.get(i)));

		}

		String sql2 = "select * from Honor";
		List<Honor> list2 = template.query(sql2,
				BeanPropertyRowMapper.newInstance(Honor.class));
		return list2;
	}*/
	
	private int update(String id, String honorImgsrc, String honorTag, int likeNum) {
		System.out.println("¾÷µ¥ÀÌÆ®");
		String sql = "update Hornor set content=?,tag=?,likeNum=? where id =? ";
		
		template.update(sql, honorImgsrc, honorTag, likeNum, id);
		System.out.println("¾÷µ¥ÀÌÆ® ³¡");
		return 0;
	}

	private int getLikeNum(String id) {
		 
		System.out.println("¶óÀÌÅ©");
		int count = 0;
	     String sql = "select likeNum from Honor where id = ?";
	     try {
	        count = template.queryForObject(sql, new Object[] {id}, Integer.class);
	        System.out.println("¶óÀÌÅ© ³¡");
	        return count;
	     } catch (Exception e) {
	        System.out.println("countÁ¶È¸¿¡ ½ÇÆÐÇÏ¿´½À´Ï´Ù.");
	        return 0;
	     }
	     
	}

	private String getHonorTag(String id) {
		
		System.out.println("ÅÂ±×");
		//±×·³ ¿©±â¼­ ±× id¶û content¶û °°ÀÌ list¿¡ ÀúÀå.
		String sql = "select content from Honor where id= ?";
		String next = template.queryForObject(sql, new Object[] {id}, String.class);
		//content¸¸ °¡²¿¿Í¼­
		Pattern nonValidPattern = Pattern.compile("\\#([0-9a-zA-z°¡-ÆR]*)");	
		int imgCnt = 0;
		String content = "";
		String str = "";
		Matcher matcher = nonValidPattern.matcher(next);
		while (matcher.find()) {
			content = matcher.group(1);
			str += content;
			String sql2 = "update Honor set tag=? where id = ?;";
			template.update(sql2, str, id);
		}
		System.out.println("ÅÂ±× ³¡");
		return str; 
	}

	private String getHonorImgsrc(String id) {
		
		System.out.println("ÀÌ¹ÌÁö");
		//±×·³ ¿©±â¼­ ±× id¶û content¶û °°ÀÌ list¿¡ ÀúÀå.
		String sql = "select content from Honor where id= ?";
		String next = template.queryForObject(sql, new Object[] {id}, String.class);
		//content¸¸ °¡²¿¿Í¼­
		Pattern nonValidPattern = Pattern
		  		.compile("<img[^>]*src=[\\\"']?([^>\\\"']+)[\\\"']?[^>]*>");
		int imgCnt = 0;
		String content = "";
		Matcher matcher = nonValidPattern.matcher(next);
		while (matcher.find()) {
			content = matcher.group(1);
			System.out.println("imgCnt " + imgCnt);
			imgCnt++;
			if(imgCnt == 1){
				break;                                  
			}
		}
		System.out.println("ÀÌ¹ÌÁö ³¡");
		return content; 
	}
}
