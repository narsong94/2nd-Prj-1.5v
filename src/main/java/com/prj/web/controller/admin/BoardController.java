package com.prj.web.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.prj.web.entity.Free;
import com.prj.web.service.admin.BoardService;

@Controller
@RequestMapping("/admin/board/*")
public class BoardController {

	@Autowired
	private BoardService service;
	
	@RequestMapping("free")
	public String advice(@RequestParam(value = "p", defaultValue = "1") int page, Model model) {
		List<Free> list = service.getFreeList(page);
		
		model.addAttribute("list", list);
		return "admin.board.free.list";
	}
	
	@RequestMapping("free/{id}")
	public String freeDetail(@PathVariable("id") String id, Model model) {

		model.addAttribute("f", service.getFree(id));
		model.addAttribute("prev", service.getFreePrev(id));
		model.addAttribute("next", service.getFreeNext(id));

		return "admin.board.notice.detail";
	}
}