package com.simpolor.app.bookmark.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.simpolor.app.board.vo.BoardReplyVO;
import com.simpolor.app.bookmark.service.BookmarkMyService;
import com.simpolor.app.bookmark.service.BookmarkService;
import com.simpolor.app.bookmark.vo.BookmarkVO;
import com.simpolor.app.common.util.PageNavigation;
import com.simpolor.app.common.util.StringUtil;

/**
 * Handles requests for the application home page.
 */
@Controller
public class BookmarkMyController {
	
	private static final Logger logger = LoggerFactory.getLogger(BookmarkMyController.class);
	
	@Autowired
	private BookmarkService bookmarkService;
	
	@Autowired
	private BookmarkMyService bookmarkMyService;
	
	@Autowired
    MessageSource messageSource;
	
	Locale locale;
	
	@RequestMapping(value = "/bookmark/myList", method = RequestMethod.GET)
	public String bookmarkMyList(HttpServletRequest request,  HttpSession session, RedirectAttributes redirectAttributes,Model model, BookmarkVO bookmarkVO){
		
		String member_id = StringUtil.getString(session.getAttribute("SESSION_MEMBER_ID"));
		if(StringUtil.isEmpty(member_id)){
			redirectAttributes.addFlashAttribute("alertMessage", messageSource.getMessage("login.required", null, locale));
			redirectAttributes.addFlashAttribute("returnUrl", request.getRequestURL());
			return "redirect:/member/login";
		}
		
		bookmarkVO.setReg_id(member_id);
		
		// 전체 갯수 가져오기 
		int totalCount = bookmarkMyService.selectBookmarkMyTotalCount(bookmarkVO);
		
		// page 파라미터 값 가져오기
		int page = bookmarkVO.getPage();
		
		 // 페이징 정보 추가
		PageNavigation paging = new PageNavigation();
		paging.setTotalCount(totalCount);
		paging.setPageNo(page);
		paging.makePageNavigation();
		
		// bookmarkVO에 offset, limit 세팅
		bookmarkVO.setOffset(paging.getOffset());
		bookmarkVO.setLimit(paging.getLimit());
		
		List<BookmarkVO> list = bookmarkMyService.selectBookmarkMyList(bookmarkVO);
		List<String> typeList = bookmarkService.selectBookmarkTypeList(bookmarkVO);
		List<String> categoryList = bookmarkService.selectBookmarkCategoryList(bookmarkVO);

		model.addAttribute("bookmarkList", list);
		model.addAttribute("bookmarkTypeList", typeList);
		model.addAttribute("bookmarkCategoryList", categoryList);
		model.addAttribute("paging", paging);
		
		return "app/bookmark/myList";
	}
	
	@ResponseBody
	@RequestMapping(value = "/bookmark/myAdd", method = RequestMethod.POST)
	public Map<String, Object> bookmarkMyAdd(HttpServletRequest request, RedirectAttributes redirectAttributes, HttpSession session, Model model, BookmarkVO bookmarkVO) {
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		String member_id = StringUtil.getString(session.getAttribute("SESSION_MEMBER_ID"));
		String member_name = StringUtil.getString(session.getAttribute("SESSION_MEMBER_NAME"));
		String member_nickname = StringUtil.getString(session.getAttribute("SESSION_MEMBER_NICKNAME"));
		if(StringUtil.isEmpty(member_id)){
			resultMap.put("result", "fail");
			resultMap.put("reason", messageSource.getMessage("login.required", null, locale));
			return resultMap;
		}
		
		bookmarkVO.setReg_id(member_id);
		bookmarkVO.setReg_name(member_name);
		bookmarkVO.setReg_nickname(member_nickname);
		
		int result = bookmarkMyService.insertBookmarkMy(bookmarkVO);
		if(result > 0){
			BookmarkVO bookmark = bookmarkService.selectBookmark(bookmarkVO);
			resultMap.put("result", "success");
			resultMap.put("bookmark", bookmark);
		}else{
			resultMap.put("result", "fail");
			resultMap.put("reason", messageSource.getMessage("result.not.found", null, locale));
		}
		
		return resultMap;
	}
	
	@ResponseBody
	@RequestMapping(value = "/bookmark/myDel", method = RequestMethod.POST)
	public Map<String, Object> bookmarkMyDel(HttpServletRequest request, RedirectAttributes redirectAttributes, HttpSession session, Model model, BookmarkVO bookmarkVO) {
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		String member_id = StringUtil.getString(session.getAttribute("SESSION_MEMBER_ID"));
		String member_name = StringUtil.getString(session.getAttribute("SESSION_MEMBER_NAME"));
		String member_nickname = StringUtil.getString(session.getAttribute("SESSION_MEMBER_NICKNAME"));
		if(StringUtil.isEmpty(member_id)){
			resultMap.put("result", "fail");
			resultMap.put("reason", messageSource.getMessage("login.required", null, locale));
			return resultMap;
		}

		bookmarkVO.setReg_id(member_id);
		bookmarkVO.setMod_name(member_name);
		bookmarkVO.setMod_name(member_name);
		bookmarkVO.setMod_nickname(member_nickname);
		
		int result = bookmarkMyService.deleteBookmarkMy(bookmarkVO);
		if(result > 0){
			BookmarkVO bookmark = bookmarkService.selectBookmark(bookmarkVO);
			resultMap.put("result", "success");
			resultMap.put("bookmark", bookmark);
		}else{
			resultMap.put("result", "fail");
			resultMap.put("reason", messageSource.getMessage("result.not.found", null, locale));
		}
		
		return resultMap;
	}
	
	@RequestMapping(value = "/bookmark/myCancel", method = RequestMethod.POST)
	public String bookmarkMyCancel(HttpServletRequest request, RedirectAttributes redirectAttributes, HttpSession session, Model model, BookmarkVO bookmarkVO) {
		
		String member_id = StringUtil.getString(session.getAttribute("SESSION_MEMBER_ID"));
		String member_name = StringUtil.getString(session.getAttribute("SESSION_MEMBER_NAME"));
		String member_nickname = StringUtil.getString(session.getAttribute("SESSION_MEMBER_NICKNAME"));
		if(StringUtil.isEmpty(member_id)){
			redirectAttributes.addFlashAttribute("alertMessage", messageSource.getMessage("login.required", null, locale));
			redirectAttributes.addFlashAttribute("returnUrl", request.getRequestURL());
			return "redirect:/member/login";
		}
		
		bookmarkVO.setReg_id(member_id); 
		bookmarkVO.setMod_name(member_name);
		bookmarkVO.setMod_name(member_name);
		bookmarkVO.setMod_nickname(member_nickname);
		
		int result = bookmarkMyService.deleteBookmarkMy(bookmarkVO);
		if(result > 0){
			//redirectAttributes.addFlashAttribute("alertMessage", messageSource.getMessage("bookmark.delete.complete", null, locale));
		}else{
			redirectAttributes.addFlashAttribute("alertMessage", messageSource.getMessage("bookmark.delete.error", null, locale));
		}
		
		return "redirect:/bookmark/myList";
	}
	
}
