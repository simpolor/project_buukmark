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

import com.simpolor.app.bookmark.service.BookmarkService;
import com.simpolor.app.bookmark.vo.BookmarkVO;
import com.simpolor.app.common.util.PageInfinity;
import com.simpolor.app.common.util.PageNavigation;
import com.simpolor.app.common.util.StringUtil;

/**
 * Handles requests for the application home page.
 */
@Controller
public class BookmarkController {
	
	private static final Logger logger = LoggerFactory.getLogger(BookmarkController.class);
	
	@Autowired
	private BookmarkService bookmarkService;
	
	@Autowired
    MessageSource messageSource;
	
	Locale locale;

	@RequestMapping(value = "/bookmark/list", method = RequestMethod.GET)
	public String bookmarkList(HttpServletRequest request, HttpSession session, Model model, BookmarkVO bookmarkVO){
		
		// 나의 북마크를 위한 설정
		String member_id = StringUtil.getString(session.getAttribute("SESSION_MEMBER_ID"));
		bookmarkVO.setReg_id(member_id);
				
		// 전체 갯수 가져오기 
		int totalCount = bookmarkService.selectBookmarkTotalCount(bookmarkVO);
		
		// page 파라미터 값 가져오기
		int page = bookmarkVO.getPage();
		
		PageInfinity paging = new PageInfinity();
		paging.setTotalCount(totalCount);
		paging.setPageNo(page);
		paging.setPageViewCount(10);
		paging.makePageInfinity();
	
		// bookmarkVO에 offset, limit 세팅
		bookmarkVO.setOffset(paging.getOffset());
		bookmarkVO.setLimit(paging.getLimit());
		
		List<BookmarkVO> list = bookmarkService.selectBookmarkList(bookmarkVO);
		List<String> typeList = bookmarkService.selectBookmarkTypeList(bookmarkVO);
		List<String> categoryList = bookmarkService.selectBookmarkCategoryList(bookmarkVO);

		model.addAttribute("bookmarkList", list);
		model.addAttribute("bookmarkTypeList", typeList);
		model.addAttribute("bookmarkCategoryList", categoryList);
		model.addAttribute("paging", paging);
		
		return "app/bookmark/list";
	}
	
	@ResponseBody
	@RequestMapping(value = "/bookmark/more", method = RequestMethod.POST)
	public Map<String, Object> bookmarkMore(HttpServletRequest request, HttpSession session, Model model, BookmarkVO bookmarkVO){
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		// 나의 북마크를 위한 설정
		String member_id = StringUtil.getString(session.getAttribute("SESSION_MEMBER_ID"));
		bookmarkVO.setReg_id(member_id);
				
		// 전체 갯수 가져오기 
		int totalCount = bookmarkService.selectBookmarkTotalCount(bookmarkVO);
		
		// page 파라미터 값 가져오기
		int page = bookmarkVO.getPage();
		
		 // 페이징 정보 추가
		PageNavigation paging = new PageNavigation();
		paging.setTotalCount(totalCount);
		paging.setPageNo(page);
		paging.setPageCount(10);
		paging.setPageViewCount(10);
		paging.makePageNavigation();
		
		// bookmarkVO에 offset, limit 세팅
		bookmarkVO.setOffset(paging.getOffset());
		bookmarkVO.setLimit(paging.getLimit());
		
		List<BookmarkVO> list = bookmarkService.selectBookmarkList(bookmarkVO);
		
		if(list != null && list.size() > 0) {
			resultMap.put("result", "success");
			resultMap.put("list", list);
		}else {
			resultMap.put("result", "fail");
			resultMap.put("reason", messageSource.getMessage("result.not.found", null, locale));
		}
		
		return resultMap;
	}
	
	@RequestMapping(value = "/bookmark/add", method = RequestMethod.POST)
	public String bookmarkAdd(HttpServletRequest request, RedirectAttributes redirectAttributes, HttpSession session, Model model, BookmarkVO bookmarkVO) {
		
		String member_id = StringUtil.getString(session.getAttribute("SESSION_MEMBER_ID"));
		String member_name = StringUtil.getString(session.getAttribute("SESSION_MEMBER_NAME"));
		String member_nickname = StringUtil.getString(session.getAttribute("SESSION_MEMBER_NICKNAME"));
		if(StringUtil.isEmpty(member_id)){
			redirectAttributes.addFlashAttribute("alertMessage", messageSource.getMessage("login.required", null, locale));
			redirectAttributes.addFlashAttribute("returnUrl", request.getContextPath()+"/bookmark/myList");
			return "redirect:/member/login";
		}
		
		bookmarkVO.setReg_id(member_id);
		bookmarkVO.setReg_name(member_name);
		bookmarkVO.setReg_nickname(member_nickname);
		
		int result = bookmarkService.insertBookmark(bookmarkVO);
		if(result > 0){
			redirectAttributes.addFlashAttribute("alertMessage", messageSource.getMessage("bookmark.register.complete", null, locale));
		}else{
			redirectAttributes.addFlashAttribute("alertMessage", messageSource.getMessage("bookmark.register.fail", null, locale));
		}
		
		return "redirect:/bookmark/myList";
		
	}
	
	@RequestMapping(value = "/bookmark/del", method = RequestMethod.POST)
	public String bookmarkDel(HttpServletRequest request, HttpSession session, RedirectAttributes redirectAttributes, Model model, BookmarkVO bookmarkVO) {
		
		String member_id = StringUtil.getString(session.getAttribute("SESSION_MEMBER_ID"));
		String member_name = StringUtil.getString(session.getAttribute("SESSION_MEMBER_NAME"));
		String member_nickname = StringUtil.getString(session.getAttribute("SESSION_MEMBER_NICKNAME"));
		if(StringUtil.isEmpty(member_id)){
			redirectAttributes.addFlashAttribute("alertMessage", messageSource.getMessage("login.required", null, locale));
			redirectAttributes.addFlashAttribute("returnUrl", request.getContextPath()+"/bookmark/myList");
			return "redirect:/member/login";
		}
		
		bookmarkVO.setMod_id(member_id);
		bookmarkVO.setMod_name(member_name);
		bookmarkVO.setMod_nickname(member_nickname);
		
		int result = bookmarkService.deleteBookmark(bookmarkVO);
		if(result > 0){
			//redirectAttributes.addFlashAttribute("alertMessage", messageSource.getMessage("bookmark.delete.complete", null, locale));
		}else{
			redirectAttributes.addFlashAttribute("alertMessage", messageSource.getMessage("bookmark.delete.error", null, locale));
		}
		
		return "redirect:/bookmark/myList";
	}
}
