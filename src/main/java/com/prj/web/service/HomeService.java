package com.prj.web.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.prj.web.dao.UserDao;
import com.prj.web.entity.User;

public class HomeService {
	
	@Autowired
	private UserDao userDao;

	public int setUser(User user) {
		return userDao.setUser(user);
	}
	
	
}
