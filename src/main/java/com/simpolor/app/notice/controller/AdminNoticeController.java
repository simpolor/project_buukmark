package com.simpolor.app.notice.controller;

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

import com.simpolor.app.notice.service.NoticeReplyService;
import com.simpolor.app.notice.service.NoticeService;
import com.simpolor.app.notice.vo.NoticeReplyVO;
import com.simpolor.app.notice.vo.NoticeVO;
import com.simpolor.app.common.util.PageNavigation;
import com.simpolor.app.common.util.StringUtil;

/**
 * Handles requests for the application home page.
 */
@Controller
public class AdminNoticeController {
	
	private static final Logger logger = LoggerFactory.getLogger(AdminNoticeController.class);
	
	@Autowired
	private NoticeService noticeService;
	
	@Autowired
	private NoticeReplyService noticeReplyService;
	
	@Autowired
    MessageSource messageSource;
	
	Locale locale;

	@RequestMapping(value = "/admin/notice/list", method = RequestMethod.GET)
	public String noticeList(HttpServletRequest request, HttpSession session, RedirectAttributes redirectAttributes, Model model, NoticeVO noticeVO){
		
		String member_id = StringUtil.getString(session.getAttribute("SESSION_MEMBER_ID"));
		int member_level = StringUtil.getInt(session.getAttribute("SESSION_MEMBER_LEVEL"));
		if(StringUtil.isEmpty(member_id) || member_level < 90){
			redirectAttributes.addFlashAttribute("alertMessage", messageSource.getMessage("login.required", null, locale));
			redirectAttributes.addFlashAttribute("returnUrl", request.getRequestURL());
			return "redirect:/admin/member/login";
		}
		
		// 전체 갯수 가져오기 
		int totalCount = noticeService.selectNoticeTotalCount(noticeVO);
		
		// page 파라미터 값 가져오기
		int page = noticeVO.getPage();
				
		 // 페이징 정보 추가
		PageNavigation paging = new PageNavigation();
		paging.setTotalCount(totalCount);
		paging.setPageNo(page);
		paging.makePageNavigation();
		
		// bookmarkVO에 offset, limit 세팅
		noticeVO.setOffset(paging.getOffset());
		noticeVO.setLimit(paging.getLimit());
		
		List<NoticeVO> list = noticeService.selectNoticeList(noticeVO);

		model.addAttribute("noticeList", list);
		model.addAttribute("paging", paging);
		
		return "app/notice/adminList";
	}
	
	@RequestMapping(value = "/admin/notice/view", method = RequestMethod.GET)
	public String noticeView(HttpServletRequest request, HttpSession session, RedirectAttributes redirectAttributes, Model model, NoticeVO noticeVO) {
		
		String member_id = StringUtil.getString(session.getAttribute("SESSION_MEMBER_ID"));
		int member_level = StringUtil.getInt(session.getAttribute("SESSION_MEMBER_LEVEL"));
		if(StringUtil.isEmpty(member_id) || member_level < 90){
			redirectAttributes.addFlashAttribute("alertMessage", messageSource.getMessage("login.required", null, locale));
			redirectAttributes.addFlashAttribute("returnUrl", request.getRequestURL());
			return "redirect:/admin/member/login";
		}
		noticeVO = noticeService.selectNotice(noticeVO);
		
		NoticeReplyVO noticeReplyVO = new NoticeReplyVO();
		noticeReplyVO.setNotice_seq(noticeVO.getNotice_seq());
		List<NoticeReplyVO> noticeReplyList = noticeReplyService.selectNoticeReplyList(noticeReplyVO);
		
		model.addAttribute("noticeVO", noticeVO);
		model.addAttribute("noticeReplyList", noticeReplyList);
		
		return "app/notice/adminView";
	}
	
	@RequestMapping(value = "/admin/notice/write", method = RequestMethod.GET)
	public String noticeWriteForm(HttpServletRequest request, HttpSession session, RedirectAttributes redirectAttributes, Model model, NoticeVO noticeVO) {
		
		String member_id = StringUtil.getString(session.getAttribute("SESSION_MEMBER_ID"));
		int member_level = StringUtil.getInt(session.getAttribute("SESSION_MEMBER_LEVEL"));
		if(StringUtil.isEmpty(member_id) || member_level < 90){
			redirectAttributes.addFlashAttribute("alertMessage", messageSource.getMessage("login.required", null, locale));
			redirectAttributes.addFlashAttribute("returnUrl", request.getRequestURL());
			return "redirect:/admin/member/login";
		}
		
		return "app/notice/adminWrite";
		
	}
	
	@RequestMapping(value = "/admin/notice/write", method = RequestMethod.POST)
	public String noticeWrite(HttpServletRequest request, HttpSession session, RedirectAttributes redirectAttributes, Model model, NoticeVO noticeVO) {
		
		String member_id = StringUtil.getString(session.getAttribute("SESSION_MEMBER_ID"));
		String member_name = StringUtil.getString(session.getAttribute("SESSION_MEMBER_NAME"));
		String member_nickname = StringUtil.getString(session.getAttribute("SESSION_MEMBER_NICKNAME"));
		int member_level = StringUtil.getInt(session.getAttribute("SESSION_MEMBER_LEVEL"));
		if(StringUtil.isEmpty(member_id) || member_level < 90){
			redirectAttributes.addFlashAttribute("alertMessage", messageSource.getMessage("login.required", null, locale));
			redirectAttributes.addFlashAttribute("returnUrl", request.getRequestURL());
			return "redirect:/admin/member/login";
		}
		
		noticeVO.setReg_id(member_id);
		noticeVO.setReg_name(member_name);
		noticeVO.setReg_nickname(member_nickname);
		
		int result = noticeService.insertNotice(noticeVO);
		if(result > 0){
			//redirectAttributes.addFlashAttribute("alertMessage", messageSource.getMessage("insert.complete", null, locale));
		}else{
			redirectAttributes.addFlashAttribute("alertMessage", messageSource.getMessage("insert.error", null, locale));
		}
		
		return "redirect:/admin/notice/view?notice_seq="+noticeVO.getNotice_seq();
		
	}
	
	@RequestMapping(value = "/admin/notice/modify", method = RequestMethod.GET)
	public String noticeModifyForm(HttpServletRequest request, HttpSession session, RedirectAttributes redirectAttributes, Model model, NoticeVO noticeVO) {
		
		String member_id = StringUtil.getString(session.getAttribute("SESSION_MEMBER_ID"));
		int member_level = StringUtil.getInt(session.getAttribute("SESSION_MEMBER_LEVEL"));
		if(StringUtil.isEmpty(member_id) || member_level < 90){
			redirectAttributes.addFlashAttribute("alertMessage", messageSource.getMessage("login.required", null, locale));
			redirectAttributes.addFlashAttribute("returnUrl", request.getRequestURL());
			return "redirect:/admin/member/login";
		}
		
		noticeVO = noticeService.selectNotice(noticeVO);
		//noticeVO.setNotice_content(StringUtil.replaceHtmlN(noticeVO.getNotice_content()));
		
		model.addAttribute("noticeVO", noticeVO);
		
		return "app/notice/adminModify";
	}
	
	@RequestMapping(value = "/admin/notice/modify", method = RequestMethod.POST)
	public String bookmarkMod(HttpServletRequest request, HttpSession session, RedirectAttributes redirectAttributes, Model model, NoticeVO noticeVO) {
		
		String member_id = StringUtil.getString(session.getAttribute("SESSION_MEMBER_ID"));
		String member_name = StringUtil.getString(session.getAttribute("SESSION_MEMBER_NAME"));
		String member_nickname = StringUtil.getString(session.getAttribute("SESSION_MEMBER_NICKNAME"));
		int member_level = StringUtil.getInt(session.getAttribute("SESSION_MEMBER_LEVEL"));
		if(StringUtil.isEmpty(member_id) || member_level < 90){
			redirectAttributes.addFlashAttribute("alertMessage", messageSource.getMessage("login.required", null, locale));
			redirectAttributes.addFlashAttribute("returnUrl", request.getRequestURL());
			return "redirect:/admin/member/login";
		}
		
		noticeVO.setMod_id(member_id);
		noticeVO.setMod_name(member_name);
		noticeVO.setMod_name(member_nickname);
		
		int result = noticeService.updateNotice(noticeVO);
		if(result > 0){
			//redirectAttributes.addFlashAttribute("alertMessage", messageSource.getMessage("update.complete", null, locale));
		}else{
			redirectAttributes.addFlashAttribute("alertMessage", messageSource.getMessage("update.error", null, locale));
		}
		
		return "redirect:/admin/notice/view?notice_seq="+noticeVO.getNotice_seq();
	}
	
	@RequestMapping(value = "/admin/notice/delete", method = RequestMethod.POST)
	public String bookmarkDel(HttpServletRequest request, HttpSession session, RedirectAttributes redirectAttributes, Model model, NoticeVO noticeVO) {
		
		String member_id = StringUtil.getString(session.getAttribute("SESSION_MEMBER_ID"));
		String member_name = StringUtil.getString(session.getAttribute("SESSION_MEMBER_NAME"));
		String member_nickname = StringUtil.getString(session.getAttribute("SESSION_MEMBER_NICKNAME"));
		int member_level = StringUtil.getInt(session.getAttribute("SESSION_MEMBER_LEVEL"));
		if(StringUtil.isEmpty(member_id) || member_level < 90){
			redirectAttributes.addFlashAttribute("alertMessage", messageSource.getMessage("login.required", null, locale));
			redirectAttributes.addFlashAttribute("returnUrl", request.getContextPath()+"/admin/notice/list");
			return "redirect:/admin/member/login";
		}
		
		noticeVO.setMod_id(member_id);
		noticeVO.setMod_name(member_name);
		noticeVO.setMod_nickname(member_nickname);
		
		int result = noticeService.deleteNotice(noticeVO);
		if(result > 0){
			//redirectAttributes.addFlashAttribute("alertMessage", messageSource.getMessage("delete.complete", null, locale));
		}else{
			redirectAttributes.addFlashAttribute("alertMessage", messageSource.getMessage("delete.error", null, locale));
		}
		
		return "redirect:/admin/notice/list";
	}
}
