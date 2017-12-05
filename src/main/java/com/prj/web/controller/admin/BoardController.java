package com.prj.web.controller.admin;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.Principal;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.Gson;
import com.prj.web.entity.Advice;
import com.prj.web.entity.Comment;
import com.prj.web.entity.Free;
import com.prj.web.entity.Info;
import com.prj.web.entity.Tip;
import com.prj.web.entity.User;
import com.prj.web.entity.Voting;
import com.prj.web.entity.VotingLike;
import com.prj.web.service.admin.BoardService;

@Controller
@RequestMapping("/admin/board/*")
public class BoardController {

	@Autowired
	private BoardService service;
	
	/*-------------- 자유 게시판 --------------*/
	
	@RequestMapping("free")
	public String free(@RequestParam(value = "p", defaultValue = "1") int page, @RequestParam(value = "q", defaultValue = "") String query, Model model) {
		List<Free> list = service.getFreeList(page, query);
		int count = service.getFreeCount();
		
		model.addAttribute("list", list);
		model.addAttribute("count", count);
		return "admin.board.free.list";
	}
	
	@RequestMapping("free/{id}")
	public String freeDetail(@PathVariable("id") String id, Model model) {

		service.updateFreeHit(id);
		model.addAttribute("f", service.getFree(id));
		model.addAttribute("prev", service.getFreePrev(id));
		model.addAttribute("next", service.getFreeNext(id));

		return "admin.board.free.detail";
	}
	
	@RequestMapping(value = "free/{id}/edit", method = RequestMethod.GET)
	public String freeUpdate(@PathVariable("id") String id, Model model) {

		model.addAttribute("f", service.getFree(id));
		
		return "admin.board.free.edit";
	}
	
	@RequestMapping(value = "free/{id}/edit", method = RequestMethod.POST)
	public String freeUpdate(@PathVariable("id") String id, Free free) {
		
		int update = service.freeUpdate(id, free);
		
		return "redirect:../../free/{id}";
	}
	
	@RequestMapping(value = "free/reg", method = RequestMethod.GET)
	public String freeReg() {
		return "admin.board.free.reg";
	}

	@RequestMapping(value = "free/reg", method = RequestMethod.POST)
	public String freeReg(Free free, MultipartFile file, HttpServletRequest request, Principal principal) throws IOException {

		int nextId = service.getFreeNextId();
		String writerId = principal.getName();
		System.out.println(writerId);
		
		service.freeInsert(free.getTitle(), free.getContent(), writerId);

		return "redirect:../free";
	}
	
	@RequestMapping("free/{id}/del")
	public String freeDel(@PathVariable("id") String id) {
		
		service.freeDel(id);
		
		return "redirect:../../free";
	}
	
	/*-------------- Tip 게시판 --------------*/
	
	@RequestMapping("tip")
	public String tip(@RequestParam(value = "p", defaultValue = "1") int page, @RequestParam(value = "q", defaultValue = "") String query, Model model) {
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
		service.updateTipHit(id);

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
		String writerId = principal.getName();
		System.out.println(writerId);

		service.tipInsert(tip.getTitle(), tip.getContent(), writerId);

		return "redirect:../tip";
	}
	
	@RequestMapping("tip/{id}/del")
	public String tipDel(@PathVariable("id") String id) {
		
		service.tipDel(id);
		
		return "redirect:../../tip";
	}
	
	/*-------------- Info 게시판 --------------*/
	
	@RequestMapping("info")
	public String info(@RequestParam(value = "p", defaultValue = "1") int page, Model model) {
		
		  List<Info> listInfo = service.getInfoList(page);
		  int count = service.getInfoCount();
		  
          model.addAttribute("list", listInfo);
          model.addAttribute("count", count);
		
          return "admin.board.info.list";
		
		
	}
	
	@RequestMapping("info/{id}")
	public String infoDetail(@PathVariable("id") String id, Model model) {
		
		service.updateInfoHit(id);
		model.addAttribute("i", service.getInfo(id));	  
	    model.addAttribute("prev", service.getInfoPrev(id));
	    model.addAttribute("next", service.getInfoNext(id));
	         
	    return "admin.board.info.detail";
	}
	
	@RequestMapping(value="info/{id}/edit",method=RequestMethod.GET)
	public String infoUpdate(@PathVariable("id") String id, Model model) {
		
		model.addAttribute("i", service.getInfo(id));
		
		return "admin.board.info.edit";
		
	}

	@RequestMapping(value="info/{id}/edit",method=RequestMethod.POST)
	public String infoUpdate(@PathVariable("id") String id, Info info) {
		
		service.infoUpdate(id, info);
		
	return "redirect:../../info/{id}";	
	}
	
	@RequestMapping(value="info/reg", method=RequestMethod.GET)
    public String noticeReg() {
       return "admin.board.info.reg";
    }
	
	@RequestMapping(value="info/reg", method=RequestMethod.POST)
	public String infoReg(Info info,MultipartFile file,HttpServletRequest request, Principal principal) {
		
		service.getInfoNextId();
		String writerId = principal.getName();
		service.infoInsert(info.getTitle(), info.getContent(), writerId);
		
		return "redirect:../info";
		
	}
	
	@RequestMapping("info/{id}/del")
	public String infoDel(@PathVariable("id") String id) {
		
		service.infoDel(id);
		
		return "redirect:../../info";
	}
	
	/*-------------- Voting 게시판 --------------*/
	
	@RequestMapping("voting")
	public String voting(@RequestParam(value = "p", defaultValue = "1") int page, @RequestParam(value = "q", defaultValue = "") String query, Model model) {
		
		List<Voting> list = service.getVotingList(page, query);
		int count = service.getVotingCount();	// 조회수
		
		model.addAttribute("list", list);
		model.addAttribute("count", count);
		return "admin.board.voting.list";
	}
	
	@RequestMapping(value = "voting/{id}", method = RequestMethod.GET)
	public String votingDetail(@PathVariable("id") String id, Model model, Principal principal) {
		// 사진투표------
		List<String> pics = service.getVotingImgs(id);
		VotingLike like = service.getVotingLike(id);
		int voteUser = service.getVoteUser(principal.getName(), id);
		// ------------
		
		User user = service.getWriterUser(id);
		Voting prev = service.getVotingPrev(id);
		Voting next = service.getVotingNext(id);
		service.updateVotingHit(id);

		model.addAttribute("v", service.getVoting(id));
		// 사진투표------
		model.addAttribute("voteUser", voteUser);	
		model.addAttribute("pics", pics);	
		model.addAttribute("vl", like); 
		// ------------
		model.addAttribute("writer", user);	
		model.addAttribute("prev", prev);
		model.addAttribute("next", next);

		return "admin.board.voting.detail";
	}
	
	// 사진 투표 단입니다!! 좋아요 단 아니에요!!!-------------------------------
	/*@ResponseBody
	@RequestMapping("voting/{id}/imgs")
	public String votingListImgs(@PathVariable("id") String id, Principal principal) {
		System.out.println("sdfsdfsf");
		//List<String> pics = service.getVotingImgs(id);
			
		System.out.println(pics.get(0));
		System.out.println(pics.get(1));

		Map<String, String> objects = new HashMap<String,String>();
		objects.put("img1", pics.get(0));
		objects.put("img2", pics.get(1));
//		 "id" : 3
		String json = "";
		Gson gson = new Gson();
		json = gson.toJson(objects); 
		
		return json;
	}*/
	//--------------------------------------------------------------
	
	// 사진 투표 단입니다!! 좋아요 단 아니에요!!!-------------------------------
	@ResponseBody
	@RequestMapping("voting/{id}/like")
	public String votingLikeUp(@PathVariable("id") String id, @RequestParam("num") String num, Principal principal) {
		
		int voteNum = service.setVotingUserLike(id, principal.getName(), num);
		
		String str = Integer.toString(voteNum);
		
		return str;
	}
	//--------------------------------------------------------------
	
	@RequestMapping(value = "voting/{id}/edit", method = RequestMethod.GET)
	public String votingUpdate(@PathVariable("id") String id, Model model) {

		model.addAttribute("v", service.getVoting(id));
		
		return "admin.board.voting.edit";
	}
	
	@RequestMapping(value = "voting/{id}/edit", method = RequestMethod.POST)
	public String votingUpdate(@PathVariable("id") String id, Voting voting) {
		
		service.votingUpdate(id, voting);
		
		return "redirect:../../voting/{id}";
	}
	
	@RequestMapping(value = "voting/reg", method = RequestMethod.GET)
	public String votingReg() {
		return "admin.board.voting.reg";
	}

	@RequestMapping(value = "voting/reg", method = RequestMethod.POST)
	public String votingReg(Voting voting, MultipartFile file, HttpServletRequest request, Principal principal) throws IOException {

		int nextId = service.getVotingNextId();
		String writerId = principal.getName();
		
		service.votingInsert(voting.getTitle(), voting.getContent(), writerId);

		return "redirect:../voting";
	}
	
	@RequestMapping("voting/{id}/del")
	public String votingDel(@PathVariable("id") String id) {
		
		service.votingDel(id);
		
		return "redirect:../../voting";
	}


	/*-------------- Advice 게시판 --------------*/
	
	@RequestMapping("advice")
	public String advice(@RequestParam(value = "p", defaultValue = "1") int page, @RequestParam(value = "q", defaultValue = "") String query, Model model) {
		
		List<Advice> list = service.getAdviceList(page, query);
		int count = service.getAdviceCount();
		List<Voting> vlist = service.getVotingList(page, query);
		List<String> votingPics = null;		
						
		for(Voting v : vlist) {
			votingPics = service.getVotingImgs(v.getId());
			if(votingPics.isEmpty() == true) {
				break;
			}else {				
				v.setPics(votingPics);
			}
		}
		
		model.addAttribute("vList", vlist);
		model.addAttribute("list", list);
		model.addAttribute("count", count);
		return "admin.board.codi.list";
	}
	 
	@RequestMapping("advice/{id}")
	public String adviceDetail(@PathVariable("id") String id, Model model) {
		
		Advice prev = service.getAdvicePrev(id);
		Advice next = service.getAdviceNext(id);
		service.updateAdviceHit(id);
		Advice advice = service.getAdvice(id);
		List<Advice> prevList = service.getPrevAdviceList(advice.getWriterId(), advice.getDate());
		
		//댓글 모델 설정
  		List<Comment> list = service.getAdviceCommentList(id);
  		model.addAttribute("adviceCommentList",list);

		model.addAttribute("a", advice);
		model.addAttribute("prevlist", prevList);
		model.addAttribute("prev", prev);
		model.addAttribute("next", next);

		return "admin.board.advice.detail";
	}
	
	@RequestMapping(value = "advice/{id}/edit", method = RequestMethod.GET)
	public String adviceUpdate(@PathVariable("id") String id, Model model) {

		model.addAttribute("a", service.getAdvice(id));
		
		return "admin.board.advice.edit";
	}
	
	@RequestMapping(value = "advice/{id}/edit", method = RequestMethod.POST)
	public String adviceUpdate(@PathVariable("id") String id, Advice advice) {
		
		service.adviceUpdate(id, advice);
		
		return "redirect:../../advice/{id}";
	}
	
	@RequestMapping(value = "advice/reg", method = RequestMethod.GET)
	public String adviceReg() {
		return "admin.board.advice.reg";
	}

	@RequestMapping(value = "advice/reg", method = RequestMethod.POST)
	public String adviceReg(Advice advice, MultipartFile file, HttpServletRequest request, Principal principal) throws IOException {

		int nextId = service.getAdviceNextId();
		String writerId = principal.getName();
		
		service.adviceInsert(advice.getTitle(), advice.getContent(), writerId);

		return "redirect:../advice";
	}
	
	@RequestMapping("advice/{id}/del")
	public String adviceDel(@PathVariable("id") String id) {
		
		service.adviceDel(id);
		
		return "redirect:../../advice";
	}
	
	////////////////////// Advice 댓글 start //////////////////////

    //AJAX 호출 (댓글 등록)
    @RequestMapping(value="advice/comment/save", method=RequestMethod.POST)
    @ResponseBody
    public String AdviceCommentSave(@RequestParam Map<String, Object> objParams,Principal principal) {
    	 
        //정보입력
    	String content = objParams.get("comment_content").toString();
    	String advice_id = objParams.get("advice_id").toString();
    	String writer_id = principal.getName();
   
        int result = service.adviceCommentInsert(content, advice_id, writer_id);
        if(result>0){
        	System.out.println("comment등록에 성공하였습니다.");
        }else{
            System.out.println("comment등록에 실패하였습니다.");
        }
       
        return "aa";
    }
    
    @RequestMapping(value="advice/comment/update-ajax", method=RequestMethod.GET)
    @ResponseBody
    public String AdviceCommentUpdate(@RequestParam String adviceId,@RequestParam String cId) {
	
    	//정보입력
    	System.out.println("adviceId : "+adviceId+", id : "+cId);
    	List<Comment> list = service.getAdviceUpdateCommentList(adviceId,cId);
    	if(list==null)
    		System.out.println("최신글입니다");
    	else 
    		System.out.println("최신 :"+list);
    	
    	String json = "";	
		Gson gson = new Gson();
		json = gson.toJson(list);
		
		return json;
    }
    
    //////////////////////Advice 댓글 end //////////////////////
	
}