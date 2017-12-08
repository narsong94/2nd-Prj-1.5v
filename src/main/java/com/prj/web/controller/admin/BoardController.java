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
import com.prj.web.entity.Adviceview;
import com.prj.web.entity.Comment;
import com.prj.web.entity.Drama;
import com.prj.web.entity.DramaObject;
import com.prj.web.entity.Dramaview;
import com.prj.web.entity.Free;
import com.prj.web.entity.Honor;
import com.prj.web.entity.Imgview;
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
	
	/*-------------- Hornor �Խ��� --------------*/

	@RequestMapping("honor")
	public String hornor(@RequestParam(value = "q", defaultValue = "") String query, Model model) {
		/*List<Honor> list = service.getHonorList(query);

		model.addAttribute("list", list);*/
		return "admin.board.honor.list";
	}
	
	@RequestMapping(value = "honor/reg", method = RequestMethod.GET)
	public String honorReg() {
		return "admin.board.honor.reg";
	}

	@RequestMapping(value = "honor/reg", method = RequestMethod.POST)
	public String honorReg(Honor honor, MultipartFile file, HttpServletRequest request, Principal principal)
			throws IOException {

		String writerId = principal.getName();

		service.honorInsert(honor.getContent(), writerId);
		service.honorUpdate();

		return "redirect:../honor";
	}

	/*-------------- Tip �Խ��� --------------*/

	@RequestMapping("tip")
	public String tip(@RequestParam(value = "p", defaultValue = "1") int page,
			@RequestParam(value = "q", defaultValue = "") String query, Model model) {
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
	public String tipReg(Tip tip, MultipartFile file, HttpServletRequest request, Principal principal)
			throws IOException {

		int nextId = service.getTipNextId();
		String writerId = principal.getName();
		System.out.println(writerId);

		service.tipInsert(tip.getTitle(), tip.getContent(), writerId);

		return "redirect:../info";
	}

	@RequestMapping("tip/{id}/del")
	public String tipDel(@PathVariable("id") String id) {

		service.tipDel(id);

		return "redirect:../../tip";
	}

	///////////////// ��� start////////////////
	@RequestMapping(value = "tip/comment/save", method = RequestMethod.POST)
	@ResponseBody
	public String boardTipCommentSave(@RequestParam Map<String, Object> objParams, Principal principal) {

		// �����Է�
		String content = objParams.get("comment_content").toString();
		String tip_id = objParams.get("tip_id").toString();
		String writer_id = principal.getName();

		int result = service.tipCommentInsert(content, tip_id, writer_id);
		if (result > 0) {
			System.out.println("comment��Ͽ� �����Ͽ����ϴ�.");
		} else {
			System.out.println("comment��Ͽ� �����Ͽ����ϴ�.");
		}
		return "aa";
	}

	@RequestMapping(value = "tip/comment/update-ajax", method = RequestMethod.GET)
	@ResponseBody
	public String boardTipCommentUpdate(@RequestParam String tipId) {

		// �����Է�
		System.out.println("tipId : " + tipId);
		List<Comment> list = service.getTipCommentList(tipId);
		if (list == null)
			System.out.println("�ֽű��Դϴ�");
		else
			System.out.println("�ֽ� :" + list);

		String json = "";
		Gson gson = new Gson();
		json = gson.toJson(list);

		return json;
	}
	///////////////// ��� end////////////////
	
	///////////////// ���ƿ� start////////////////
	// ���ƿ� ����
		@RequestMapping(value = "tip/like/save", method = RequestMethod.GET)
		@ResponseBody
		public String tipLikeSave(@RequestParam String tipId, Principal principal) {
			String tip_id = tipId;
			 System.out.println("�Ӵ� tipId : "+ tip_id);
			String writer_id = principal.getName();

			// ���ƿ� start�����ϴ� �κ�
			// ���ƿ並 ���� ����� �ߺ�üũ
			Integer checkResult = 0;
			checkResult = service.tipLikeCheck(tip_id, writer_id);
			// System.out.println("idcheckResult : "+ checkResult);
			// writerId���� �������� ������ <=0
			if (checkResult <= 0) {
				int insertResult = 0;
				int countUpResult = 0;
				insertResult = service.tipLikeInsert(tip_id, writer_id);// ���ƿ䴩����������ϱ�
				countUpResult = service.tipLikeCountUp(tip_id);// ���ƿ� �����ø���
				if (insertResult > 0) {
					System.out.println("Like_writerId��Ͽ� �����Ͽ����ϴ�.");
				} else {
					System.out.println("Like_writerId��Ͽ� �����Ͽ����ϴ�.");
				}
				if (countUpResult > 0) {
					System.out.println("Like���� up�� �����Ͽ����ϴ�.");
				} else {
					System.out.println("Like���� up�� �����Ͽ����ϴ�.");
				}
			}
			// ���ƿ� ���� �������� �κ�
			int likeCount = 0;
			likeCount = service.tipLikecount(tip_id, writer_id);// ���ƿ�count
			Map<String, Integer> obj = new HashMap<String, Integer>();
			obj.put("idCheck", checkResult);
			obj.put("likeCount", likeCount);
			String json = "";
			Gson gson = new Gson();
			json = gson.toJson(obj);
			return json;
		}
	
		///////////////// ���ƿ� end////////////////
		
	/*-------------- Info �Խ��� --------------*/

	@RequestMapping("info")
	public String info(@RequestParam(value = "p", defaultValue = "1") int page, Model model) {

		List<Info> listInfo = service.getInfoList(page);
		List<Imgview> imgInfo = service.getId();
		int count = service.getInfoCount();
		
		List<Tip> listTip = service.getTipList(page, "");
        model.addAttribute("tipList",listTip);
		
		model.addAttribute("list2", imgInfo);
		model.addAttribute("list", listInfo);
		model.addAttribute("count", count);
		
		//��� ����Ʈ
        List<DramaObject> listdrama = service.getDramaList(page);
        model.addAttribute("listdrama",listdrama);
        List<Dramaview> DramaInfo = service.getdramaId();
        model.addAttribute("dramaview", DramaInfo);

		return "admin.board.info.list";

	}

	@RequestMapping("info/{id}")
	public String infoDetail(@PathVariable("id") String id, Model model, Principal principal) {

		service.updateInfoHit(id);
		model.addAttribute("i", service.getInfo(id));
		model.addAttribute("prev", service.getInfoPrev(id));
		model.addAttribute("next", service.getInfoNext(id));
		
		/*--------���ƿ�_detail start-----------------------------*/
		String writerId = principal.getName();
		model.addAttribute("likeCount", service.infoLikecount(id, writerId));
		/*--------���ƿ�_detail end-------------------------------*/

		return "admin.board.info.detail";
	}

	@RequestMapping(value = "info/{id}/edit", method = RequestMethod.GET)
	public String infoUpdate(@PathVariable("id") String id, Model model) {

		model.addAttribute("i", service.getInfo(id));

		return "admin.board.info.edit";

	}

	@RequestMapping(value = "info/{id}/edit", method = RequestMethod.POST)
	public String infoUpdate(@PathVariable("id") String id, Info info) {

		service.infoUpdate(id, info);

		return "redirect:../../info/{id}";
	}

	@RequestMapping(value = "info/reg", method = RequestMethod.GET)
	public String noticeReg() {
		return "admin.board.info.reg";
	}

	@RequestMapping(value = "info/reg", method = RequestMethod.POST)
	public String infoReg(Info info, MultipartFile file, HttpServletRequest request, Principal principal) {

		service.getInfoNextId();
		String writerId = principal.getName();
		service.infoInsert(info.getTitle(), info.getContent(), writerId);

		return "redirect:../info";

	}
	
	//��󸶼� �� ���
	@RequestMapping(value="info/dramaReg", method=RequestMethod.GET)
	public String dramaReg() {
	    return "admin.board.info.dramaReg";
	}
		
	@RequestMapping(value="info/dramaReg", method=RequestMethod.POST)
	public String dramaReg(Drama drama,MultipartFile file,HttpServletRequest request, Principal principal) {
			
		service.getDramaNextId();
		String writerId = principal.getName();
		service.infoDramaInsert(drama.getName(), drama.getContent(), writerId);
			
		return "redirect:../info";
			
	}

	@RequestMapping("info/{id}/del")
	public String infoDel(@PathVariable("id") String id) {

		service.infoDel(id);

		return "redirect:../../info";
	}

	///////////////// ��� start////////////////
	@RequestMapping(value = "info/comment/save", method = RequestMethod.POST)
	@ResponseBody
	public String boardInfoCommentSave(@RequestParam Map<String, Object> objParams, Principal principal) {

		// �����Է�
		String content = objParams.get("comment_content").toString();
		String info_id = objParams.get("info_id").toString();
		String writer_id = principal.getName();

		int result = service.infoCommentInsert(content, info_id, writer_id);
		if (result > 0) {
			System.out.println("comment��Ͽ� �����Ͽ����ϴ�.");
		} else {
			System.out.println("comment��Ͽ� �����Ͽ����ϴ�.");
		}
		return "aa";
	}

	@RequestMapping(value = "info/comment/update-ajax", method = RequestMethod.GET)
	@ResponseBody
	public String boardInfoCommentUpdate(@RequestParam String infoId) {

		// �����Է�
		System.out.println("infoId : " + infoId);
		List<Comment> list = service.getInfoCommentList(infoId);
		if (list == null)
			System.out.println("�ֽű��Դϴ�");
		else
			System.out.println("�ֽ� :" + list);

		String json = "";
		Gson gson = new Gson();
		json = gson.toJson(list);

		return json;
	}
	///////////////// ��� end////////////////
	
	///////////////// ���ƿ� start ////////////////
	// ���ƿ� ����
		@RequestMapping(value = "info/like/save", method = RequestMethod.GET)
		@ResponseBody
		public String boardLikeSave(@RequestParam String infoId, Principal principal) {
			String info_id = infoId;
			// System.out.println("�Ӵ� infoID : "+ info_id);
			String writer_id = principal.getName();

			// ���ƿ� start�����ϴ� �κ�
			// ���ƿ並 ���� ����� �ߺ�üũ
			Integer checkResult = 0;
			checkResult = service.infoLikeCheck(info_id, writer_id);
			// System.out.println("idcheckResult : "+ checkResult);
			// writerId���� �������� ������ <=0
			if (checkResult <= 0) {
				int insertResult = 0;
				int countUpResult = 0;
				insertResult = service.infoLikeInsert(info_id, writer_id);// ���ƿ䴩����������ϱ�
				countUpResult = service.infoLikeCountUp(info_id);// ���ƿ� �����ø���
				if (insertResult > 0) {
					System.out.println("Like_writerId��Ͽ� �����Ͽ����ϴ�.");
				} else {
					System.out.println("Like_writerId��Ͽ� �����Ͽ����ϴ�.");
				}
				if (countUpResult > 0) {
					System.out.println("Like���� up�� �����Ͽ����ϴ�.");
				} else {
					System.out.println("Like���� up�� �����Ͽ����ϴ�.");
				}
			}
			// ���ƿ� ���� �������� �κ�
			int likeCount = 0;
			likeCount = service.infoLikecount(info_id, writer_id);// ���ƿ�count
			Map<String, Integer> obj = new HashMap<String, Integer>();
			obj.put("idCheck", checkResult);
			obj.put("likeCount", likeCount);
			String json = "";
			Gson gson = new Gson();
			json = gson.toJson(obj);
			return json;
		}
		///////////////// ���ƿ� end ////////////////

	/*-------------- Voting �Խ��� --------------*/

	@RequestMapping("voting")
	public String voting(@RequestParam(value = "p", defaultValue = "1") int page,
			@RequestParam(value = "q", defaultValue = "") String query, Model model) {

		List<Voting> list = service.getVotingList(page, query);
		int count = service.getVotingCount(); // ��ȸ��

		model.addAttribute("list", list);
		model.addAttribute("count", count);
		return "admin.board.voting.list";
	}

	@RequestMapping(value = "voting/{id}", method = RequestMethod.GET)
	public String votingDetail(@PathVariable("id") String id, Model model, Principal principal) {
		// ������ǥ------
		List<String> pics = service.getVotingImgs(id);
		VotingLike like = service.getVotingLike(id);
		int voteUser = service.getVoteUser(principal.getName(), id);
		// ------------

		User user = service.getWriterUser(id);
		Voting prev = service.getVotingPrev(id);
		Voting next = service.getVotingNext(id);
		service.updateVotingHit(id);

		model.addAttribute("v", service.getVoting(id));
		// ������ǥ------
		model.addAttribute("voteUser", voteUser);
		model.addAttribute("pics", pics);
		model.addAttribute("vl", like);
		// ------------
		model.addAttribute("writer", user);
		model.addAttribute("prev", prev);
		model.addAttribute("next", next);

		return "admin.board.voting.detail";
	}

	// ���� ��ǥ ���Դϴ�!! ���ƿ� �� �ƴϿ���!!!-------------------------------
	@ResponseBody
	@RequestMapping("voting/{id}/like")
	public String votingLikeUp(@PathVariable("id") String id, @RequestParam("num") String num, Principal principal) {

		int voteNum = service.setVotingUserLike(id, principal.getName(), num);

		String str = Integer.toString(voteNum);

		return str;
	}
	// --------------------------------------------------------------

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
	public String votingReg(Voting voting, MultipartFile file, HttpServletRequest request, Principal principal)
			throws IOException {

		int nextId = service.getVotingNextId();
		String writerId = principal.getName();

		service.votingInsert(voting.getTitle(), voting.getContent(), writerId);

		return "redirect:../advice";
	}

	@RequestMapping("voting/{id}/del")
	public String votingDel(@PathVariable("id") String id) {

		service.votingDel(id);

		return "redirect:../../advice";
	}

	///////////////// ��� start////////////////
	@RequestMapping(value = "voting/comment/save", method = RequestMethod.POST)
	@ResponseBody
	public String boardVotingCommentSave(@RequestParam Map<String, Object> objParams, Principal principal) {

		// �����Է�
		String content = objParams.get("comment_content").toString();
		String voting_id = objParams.get("voting_id").toString();
		String writer_id = principal.getName();

		int result = service.votingCommentInsert(content, voting_id, writer_id);
		if (result > 0) {
			System.out.println("comment��Ͽ� �����Ͽ����ϴ�.");
		} else {
			System.out.println("comment��Ͽ� �����Ͽ����ϴ�.");
		}
		return "aa";
	}

	@RequestMapping(value = "voting/comment/update-ajax", method = RequestMethod.GET)
	@ResponseBody
	public String boardVotingCommentUpdate(@RequestParam String votingId) {

		// �����Է�
		System.out.println("votingId : " + votingId);
		List<Comment> list = service.getVotingCommentList(votingId);
		if (list == null)
			System.out.println("�ֽű��Դϴ�");
		else
			System.out.println("�ֽ� :" + list);

		String json = "";
		Gson gson = new Gson();
		json = gson.toJson(list);

		return json;
	}
	///////////////// ��� end////////////////

	/*-------------- Advice �Խ��� --------------*/

	@RequestMapping("advice")
	public String advice(@RequestParam(value = "p", defaultValue = "1") int page,
			@RequestParam(value = "q", defaultValue = "") String query, Model model) {

		//List<Advice> list = service.getAdviceList(page, query);
		List<Adviceview> adviceInfo = service.getadviceId();
		int count = service.getAdviceCount();
		List<Voting> vlist = service.getVotingList(page, query);
		List<String> votingPics = null;

		for (Voting v : vlist) {
			votingPics = service.getVotingImgs(v.getId());
			if (votingPics.isEmpty() == true) {
				break;
			} else {
				v.setPics(votingPics);
			}
		}

		model.addAttribute("vList", vlist);
		model.addAttribute("list", adviceInfo);
		model.addAttribute("count", count);
		return "admin.board.advice.list";
	}

	@RequestMapping("advice/{id}")
	public String adviceDetail(@PathVariable("id") String id, Model model) {

		Advice prev = service.getAdvicePrev(id);
		Advice next = service.getAdviceNext(id);
		service.updateAdviceHit(id);
		Advice advice = service.getAdvice(id);
		List<Advice> prevList = service.getPrevAdviceList(advice.getWriterId(), advice.getDate());

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
	public String adviceReg(Advice advice, MultipartFile file, HttpServletRequest request, Principal principal)
			throws IOException {

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

	///////////////// ��� start////////////////
	@RequestMapping(value = "advice/comment/save", method = RequestMethod.POST)
	@ResponseBody
	public String boardAdviceCommentSave(@RequestParam Map<String, Object> objParams, Principal principal) {

		// �����Է�
		String content = objParams.get("comment_content").toString();
		String advice_id = objParams.get("advice_id").toString();
		String writer_id = principal.getName();

		int result = service.adviceCommentInsert(content, advice_id, writer_id);
		if (result > 0) {
			System.out.println("comment��Ͽ� �����Ͽ����ϴ�.");
		} else {
			System.out.println("comment��Ͽ� �����Ͽ����ϴ�.");
		}
		return "aa";
	}

	@RequestMapping(value = "advice/comment/update-ajax", method = RequestMethod.GET)
	@ResponseBody
	public String boardAdviceCommentUpdate(@RequestParam String adviceId) {

		// �����Է�
		System.out.println("adviceId : " + adviceId);
		List<Comment> list = service.getAdviceCommentList(adviceId);
		if (list == null)
			System.out.println("�ֽű��Դϴ�");
		else
			System.out.println("�ֽ� :" + list);

		String json = "";
		Gson gson = new Gson();
		json = gson.toJson(list);

		return json;
	}
	///////////////// ��� end////////////////
	
	//////////////// ���ƿ� start /////////////
	// ���ƿ� ����
		@RequestMapping(value = "advice/like/save", method = RequestMethod.GET)
		@ResponseBody
		public String adviceLikeSave(@RequestParam String adviceId, Principal principal) {
			String advice_id = adviceId;
			System.out.println("�Ӵ� adviceController_id : "+ advice_id);
			String writer_id = principal.getName();

			// ���ƿ� start�����ϴ� �κ�
			// ���ƿ並 ���� ����� �ߺ�üũ
			Integer checkResult = 0;
			checkResult = service.adviceLikeCheck(advice_id, writer_id);
			 System.out.println("adviceController_idcheckResult : "+ checkResult);
			// writerId���� �������� ������ <=0
			if (checkResult <= 0) {
				int insertResult = 0;
				int countUpResult = 0;
				insertResult = service.adviceLikeInsert(advice_id, writer_id);// ���ƿ䴩����������ϱ�
				countUpResult = service.adviceLikeCountUp(advice_id);// ���ƿ� �����ø���
				if (insertResult > 0) {
					System.out.println("Like_writerId��Ͽ� �����Ͽ����ϴ�.");
				} else {
					System.out.println("Like_writerId��Ͽ� �����Ͽ����ϴ�.");
				}
				if (countUpResult > 0) {
					System.out.println("Like���� up�� �����Ͽ����ϴ�.");
				} else {
					System.out.println("Like���� up�� �����Ͽ����ϴ�.");
				}
			}
			// ���ƿ� ���� �������� �κ�
			int likeCount = 0;
			likeCount = service.adviceLikecount(advice_id, writer_id);// ���ƿ�count
			Map<String, Integer> obj = new HashMap<String, Integer>();
			obj.put("idCheck", checkResult);
			obj.put("likeCount", likeCount);
			String json = "";
			Gson gson = new Gson();
			json = gson.toJson(obj);
			return json;
		}
		////////////////���ƿ� end /////////////
}