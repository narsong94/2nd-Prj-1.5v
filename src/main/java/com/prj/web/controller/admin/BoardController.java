package com.prj.web.controller.admin;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.Principal;
import java.util.Calendar;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.prj.web.entity.Free;
import com.prj.web.entity.Tip;
import com.prj.web.service.admin.BoardService;

@Controller
@RequestMapping("/admin/board/*")
public class BoardController {

	@Autowired
	private BoardService service;
	
	/*-------------- 자유 게시판 --------------*/
	
	@RequestMapping("free")
	public String advice(@RequestParam(value = "p", defaultValue = "1") int page, @RequestParam(value = "p", defaultValue = "") String query, Model model) {
		List<Free> list = service.getFreeList(page, query);
		
		model.addAttribute("list", list);
		return "admin.board.free.list";
	}
	
	@RequestMapping("free/{id}")
	public String freeDetail(@PathVariable("id") String id, Model model) {

		model.addAttribute("f", service.getFree(id));
		model.addAttribute("prev", service.getFreePrev(id));
		model.addAttribute("next", service.getFreeNext(id));

		return "admin.board.free.detail";
	}
	
	/*-------------- Tip 게시판 --------------*/
	
	@RequestMapping("tip")
	public String tip(@RequestParam(value = "p", defaultValue = "1") int page, @RequestParam(value = "p", defaultValue = "") String query, Model model) {
		List<Tip> list = service.getTipList(page, query);
		int count = service.getTipCount();
		
		model.addAttribute("list", list);
		model.addAttribute("count", count);
		return "admin.board.tip.list";
	}
	
	@RequestMapping("tip/{id}")
	public String tipDetail(@PathVariable("id") String id, Model model) {
		
		Tip prev = service.getTipPrev(id);
		Tip next = service.getTipNext(id);

		model.addAttribute("t", service.getTip(id));
		model.addAttribute("prev", prev);
		model.addAttribute("next", next);

		return "admin.board.tip.detail";
	}
	
	@RequestMapping(value = "tip/{id}/edit", method = RequestMethod.GET)
	public String tipUpdate(@PathVariable("id") String id, Model model) {

		model.addAttribute("t", service.getTip(id));
		
		return "admin.board.tip.edit";
	}
	
	@RequestMapping(value = "tip/{id}/edit", method = RequestMethod.POST)
	public String tipUpdate(@PathVariable("id") String id, Tip tip) {
		
		int update = service.tipUpdate(id, tip);
		
		return "redirect:../../tip/{id}";
	}
	
	@RequestMapping(value = "tip/reg", method = RequestMethod.GET)
	public String tipReg() {
		return "admin.board.tip.reg";
	}

	@RequestMapping(value = "tip/reg", method = RequestMethod.POST)
	public String tipReg(Tip tip, MultipartFile file, HttpServletRequest request, Principal principal) throws IOException {

		int nextId = service.getTipNextId();
/*
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);

		ServletContext ctx = request.getServletContext();
		String path = ctx.getRealPath(String.format("/resource/customer/notice/%s/%s", year, nextId));

		System.out.println(path);

		File f = new File(path);
		if (!f.exists()) {
			if (!f.mkdirs())
				System.out.println("년도 디렉토리 생성 불가");
		}

		path += File.separator + file.getOriginalFilename();
		File f2 = new File(path);

		InputStream fis = file.getInputStream();
		OutputStream fos = new FileOutputStream(f2);

		byte[] buf = new byte[1024];

		int size = 0;
		while ((size = fis.read(buf)) > 0)
			fos.write(buf, 0, size);

		fos.close();
		fis.close();

		String fileName = file.getOriginalFilename();*/

		service.insert(tip.getTitle(), tip.getContent(), tip.getWriterId());

		return "redirect:../tip";
	}
}