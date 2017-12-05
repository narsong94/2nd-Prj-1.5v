package com.prj.web.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.prj.web.entity.User;
import com.prj.web.service.HomeService;

@Controller
@RequestMapping("/*")
public class HomeController {
	
	@Autowired
	private HomeService service;

	@RequestMapping("index")
	public String index() {
		return "home.index";
	}
	
	@RequestMapping(value="join", method=RequestMethod.POST)
	public String joinDone(User user, Model model) {
		
		int join = service.setUser(user);
		
		return "home.done";
	}
	
	@RequestMapping(value="login", method=RequestMethod.GET)
	public String login() {
		return "home.login";
	}
	
	@RequestMapping("accessDeny")
	public String accessDeny() {
		return "home.accessDeny";
	}
	
}