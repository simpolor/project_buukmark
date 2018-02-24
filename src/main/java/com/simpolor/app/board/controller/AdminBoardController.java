package com.simpolor.app.board.controller;

import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.simpolor.app.board.service.BoardReplyService;
import com.simpolor.app.board.service.BoardService;
import com.simpolor.app.board.vo.BoardReplyVO;
import com.simpolor.app.board.vo.BoardVO;
import com.simpolor.app.common.util.PageNavigation;
import com.simpolor.app.common.util.StringUtil;

/**
 * Handles requests for the application home page.
 */
@Controller
public class AdminBoardController {
	
	private static final Logger logger = LoggerFactory.getLogger(AdminBoardController.class);
	
	@Autowired
	private BoardService boardService;
	
	@Autowired
	private BoardReplyService boardReplyService;
	
	@Autowired
    MessageSource messageSource;
	
	Locale locale;

	@RequestMapping(value = "/admin/board/list", method = RequestMethod.GET)
	public String boardList(HttpServletRequest request, HttpSession session, RedirectAttributes redirectAttributes, Model model, BoardVO boardVO){
		
		String member_id = StringUtil.getString(session.getAttribute("SESSION_MEMBER_ID"));
		int member_level = StringUtil.getInt(session.getAttribute("SESSION_MEMBER_LEVEL"));
		if(StringUtil.isEmpty(member_id) || member_level < 90){
			redirectAttributes.addFlashAttribute("alertMessage", messageSource.getMessage("login.required", null, locale));
			redirectAttributes.addFlashAttribute("returnUrl", request.getRequestURL());
			return "redirect:/admin/member/login";
		}
		
		// 전체 갯수 가져오기 
		int totalCount = boardService.selectBoardTotalCount(boardVO);
		
		// page 파라미터 값 가져오기
		int page = boardVO.getPage();
				
		 // 페이징 정보 추가
		PageNavigation paging = new PageNavigation();
		paging.setTotalCount(totalCount);
		paging.setPageNo(page);
		paging.makePageNavigation();
		
		// bookmarkVO에 offset, limit 세팅
		boardVO.setOffset(paging.getOffset());
		boardVO.setLimit(paging.getLimit());
		
		List<BoardVO> list = boardService.selectBoardList(boardVO);

		model.addAttribute("boardList", list);
		model.addAttribute("paging", paging);
		
		return "app/board/adminList";
	}
	
	@RequestMapping(value = "/admin/board/view", method = RequestMethod.GET)
	public String boardView(HttpServletRequest request, HttpSession session, RedirectAttributes redirectAttributes, Model model, BoardVO boardVO) {

		String member_id = StringUtil.getString(session.getAttribute("SESSION_MEMBER_ID"));
		int member_level = StringUtil.getInt(session.getAttribute("SESSION_MEMBER_LEVEL"));
		if(StringUtil.isEmpty(member_id) || member_level < 90){
			redirectAttributes.addFlashAttribute("alertMessage", messageSource.getMessage("login.required", null, locale));
			redirectAttributes.addFlashAttribute("returnUrl", request.getRequestURL());
			return "redirect:/admin/member/login";
		}
		
		boardVO = boardService.selectBoard(boardVO);
		
		BoardReplyVO boardReplyVO = new BoardReplyVO();
		boardReplyVO.setBoard_seq(boardVO.getBoard_seq());
		List<BoardReplyVO> boardReplyList = boardReplyService.selectBoardReplyList(boardReplyVO);
		
		model.addAttribute("boardVO", boardVO);
		model.addAttribute("boardReplyList", boardReplyList);
		
		return "app/board/adminView";
	}
	
	@RequestMapping(value = "/admin/board/write", method = RequestMethod.GET)
	public String boardWriteForm(HttpServletRequest request, HttpSession session, RedirectAttributes redirectAttributes, Model model, BoardVO boardVO) {
		
		String member_id = (String)session.getAttribute("SESSION_MEMBER_ID");
		int member_level = StringUtil.getInt(session.getAttribute("SESSION_MEMBER_LEVEL"));
		if(StringUtil.isEmpty(member_id) || member_level < 90){
			redirectAttributes.addFlashAttribute("alertMessage", messageSource.getMessage("login.required", null, locale));
			redirectAttributes.addFlashAttribute("returnUrl", request.getRequestURL());
			return "redirect:/admin/member/login";
		}
		
		return "app/board/adminWrite";
		
	}
	
	@RequestMapping(value = "/admin/board/write", method = RequestMethod.POST)
	public String boardWrite(HttpServletRequest request, HttpSession session, RedirectAttributes redirectAttributes, Model model, BoardVO boardVO) {
		
		String member_id = StringUtil.getString(session.getAttribute("SESSION_MEMBER_ID"));
		String member_name = StringUtil.getString(session.getAttribute("SESSION_MEMBER_NAME"));
		String member_nickname = StringUtil.getString(session.getAttribute("SESSION_MEMBER_NICKNAME"));
		int member_level = StringUtil.getInt(session.getAttribute("SESSION_MEMBER_LEVEL"));
		if(StringUtil.isEmpty(member_id) || member_level < 90){
			redirectAttributes.addFlashAttribute("alertMessage", messageSource.getMessage("login.required", null, locale));
			redirectAttributes.addFlashAttribute("returnUrl", request.getRequestURL());
			return "redirect:/admin/member/login";
		}
		
		boardVO.setReg_id(member_id);
		boardVO.setReg_name(member_name);
		boardVO.setReg_nickname(member_nickname);
		//boardVO.setBoard_content(StringUtil.replaceHtmlY(boardVO.getBoard_content()));
		
		int result = boardService.insertBoard(boardVO);
		if(result > 0){
			//redirectAttributes.addFlashAttribute("alertMessage", messageSource.getMessage("insert.complete", null, locale));
		}else{
			redirectAttributes.addFlashAttribute("alertMessage", messageSource.getMessage("insert.error", null, locale));
		}
		
		return "redirect:/admin/board/view?board_seq="+boardVO.getBoard_seq();
		
	}
	
	@RequestMapping(value = "/admin/board/modify", method = RequestMethod.GET)
	public String boardModifyForm(HttpServletRequest request,  HttpSession session, RedirectAttributes redirectAttributes,Model model, BoardVO boardVO) {
		
		String member_id = StringUtil.getString(session.getAttribute("SESSION_MEMBER_ID"));
		int member_level = StringUtil.getInt(session.getAttribute("SESSION_MEMBER_LEVEL"));
		if(StringUtil.isEmpty(member_id) || member_level < 90){
			redirectAttributes.addFlashAttribute("alertMessage", messageSource.getMessage("login.required", null, locale));
			redirectAttributes.addFlashAttribute("returnUrl", request.getRequestURL());
			return "redirect:/admin/member/login";
		}
		
		boardVO = boardService.selectBoard(boardVO);
		//boardVO.setBoard_content(StringUtil.replaceHtmlN(boardVO.getBoard_content()));
		
		model.addAttribute("boardVO", boardVO);
		
		return "app/board/adminModify";
	}
	
	@RequestMapping(value = "/admin/board/modify", method = RequestMethod.POST)
	public String bookmarkMod(HttpServletRequest request, HttpSession session, RedirectAttributes redirectAttributes, Model model, BoardVO boardVO) {
		
		String member_id = StringUtil.getString(session.getAttribute("SESSION_MEMBER_ID"));
		String member_name = StringUtil.getString(session.getAttribute("SESSION_MEMBER_NAME"));
		String member_nickname = StringUtil.getString(session.getAttribute("SESSION_MEMBER_NICKNAME"));
		int member_level = StringUtil.getInt(session.getAttribute("SESSION_MEMBER_LEVEL"));
		if(StringUtil.isEmpty(member_id) || member_level < 90){
			redirectAttributes.addFlashAttribute("alertMessage", messageSource.getMessage("login.required", null, locale));
			redirectAttributes.addFlashAttribute("returnUrl", request.getRequestURL());
			return "redirect:/admin/member/login";
		}
		
		boardVO.setMod_id(member_id);
		boardVO.setMod_name(member_name);
		boardVO.setMod_nickname(member_nickname);
		//boardVO.setBoard_content(StringUtil.replaceHtmlY(boardVO.getBoard_content()));
		
		int result = boardService.updateBoard(boardVO);
		if(result > 0){
			//redirectAttributes.addFlashAttribute("alertMessage", messageSource.getMessage("update.complete", null, locale));
		}else{
			redirectAttributes.addFlashAttribute("alertMessage", messageSource.getMessage("update.error", null, locale));
		}
		
		return "redirect:/admin/board/view?board_seq="+boardVO.getBoard_seq();
	}
	
	@RequestMapping(value = "/admin/board/delete", method = RequestMethod.POST)
	public String bookmarkDel(HttpServletRequest request, HttpSession session, RedirectAttributes redirectAttributes, Model model, BoardVO boardVO) {
		
		String member_id = StringUtil.getString(session.getAttribute("SESSION_MEMBER_ID"));
		String member_name = StringUtil.getString(session.getAttribute("SESSION_MEMBER_NAME"));
		String member_nickname = StringUtil.getString(session.getAttribute("SESSION_MEMBER_NICKNAME"));
		int member_level = StringUtil.getInt(session.getAttribute("SESSION_MEMBER_LEVEL"));
		if(StringUtil.isEmpty(member_id) || member_level < 90){
			redirectAttributes.addFlashAttribute("alertMessage", messageSource.getMessage("login.required", null, locale));
			redirectAttributes.addFlashAttribute("returnUrl", request.getContextPath()+"/admin/board/view?board_seq="+boardVO.getBoard_seq());
			return "redirect:/admin/member/login";
		}
		
		boardVO.setMod_id(member_id);
		boardVO.setMod_name(member_name);
		boardVO.setMod_nickname(member_nickname);
		
		int result = boardService.deleteBoard(boardVO);
		if(result > 0){
			//redirectAttributes.addFlashAttribute("alertMessage", messageSource.getMessage("board.delete.complete", null, locale));
		}else{
			redirectAttributes.addFlashAttribute("alertMessage", messageSource.getMessage("board.delete.error", null, locale));
		}
		
		return "redirect:/admin/board/list";
	}
}
