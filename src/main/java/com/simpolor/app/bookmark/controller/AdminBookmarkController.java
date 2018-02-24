package com.simpolor.app.bookmark.controller;

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
public class AdminBookmarkController {
	
	private static final Logger logger = LoggerFactory.getLogger(AdminBookmarkController.class);
	
	@Autowired
	private BookmarkService bookmarkService;
	
	@Autowired
    MessageSource messageSource;
	
	Locale locale;

	@RequestMapping(value = "/admin/bookmark/list", method = RequestMethod.GET)
	public String bookmarkMyList(HttpServletRequest request, RedirectAttributes redirectAttributes, HttpSession session, Model model, BookmarkVO bookmarkVO){
		
		String member_id = StringUtil.getString(session.getAttribute("SESSION_MEMBER_ID"));
		int member_level = StringUtil.getInt(session.getAttribute("SESSION_MEMBER_LEVEL"));
		if(StringUtil.isEmpty(member_id) || member_level < 90){
			redirectAttributes.addFlashAttribute("alertMessage", messageSource.getMessage("login.required", null, locale));
			redirectAttributes.addFlashAttribute("returnUrl", request.getRequestURL());
			return "redirect:/admin/member/login";
		}
		
		bookmarkVO.setReg_id(member_id);
		
		// 전체 갯수 가져오기 
		int totalCount = bookmarkService.selectBookmarkTotalCount(bookmarkVO);
		
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
		
		List<BookmarkVO> list = bookmarkService.selectBookmarkList(bookmarkVO);
		List<String> typeList = bookmarkService.selectBookmarkTypeList(bookmarkVO);
		List<String> categoryList = bookmarkService.selectBookmarkCategoryList(bookmarkVO);
		
		model.addAttribute("bookmarkList", list);
		model.addAttribute("bookmarkTypeList", typeList);
		model.addAttribute("bookmarkCategoryList", categoryList);
		model.addAttribute("paging", paging);
		
		return "app/bookmark/adminList";
	}
	
	@RequestMapping(value = "/admin/bookmark/add", method = RequestMethod.POST)
	public String adminBookmarkAdd(HttpServletRequest request, RedirectAttributes redirectAttributes, HttpSession session, Model model, BookmarkVO bookmarkVO) {
		
		String member_id = StringUtil.getString(session.getAttribute("SESSION_MEMBER_ID"));
		String member_name = StringUtil.getString(session.getAttribute("SESSION_MEMBER_NAME"));
		String member_nickname = StringUtil.getString(session.getAttribute("SESSION_MEMBER_NICKNAME"));
		int member_level = StringUtil.getInt(session.getAttribute("SESSION_MEMBER_LEVEL"));
		if(StringUtil.isEmpty(member_id) || member_level < 90){
			redirectAttributes.addFlashAttribute("alertMessage", messageSource.getMessage("login.required", null, locale));
			redirectAttributes.addFlashAttribute("returnUrl", request.getContextPath()+"/bookmark/myList");
			return "redirect:/admin/member/login";
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
		
		return "redirect:/admin/bookmark/list";
		
	}
	
	@RequestMapping(value = "/admin/bookmark/del", method = RequestMethod.POST)
	public String bookmarkDel(HttpServletRequest request, HttpSession session, RedirectAttributes redirectAttributes, Model model, BookmarkVO bookmarkVO) {
		
		String member_id = StringUtil.getString(session.getAttribute("SESSION_MEMBER_ID"));
		String member_name = StringUtil.getString(session.getAttribute("SESSION_MEMBER_NAME"));
		String member_nickname = StringUtil.getString(session.getAttribute("SESSION_MEMBER_NICKNAME"));
		int member_level = StringUtil.getInt(session.getAttribute("SESSION_MEMBER_LEVEL"));
		if(StringUtil.isEmpty(member_id) || member_level < 90){
			redirectAttributes.addFlashAttribute("alertMessage", messageSource.getMessage("login.required", null, locale));
			redirectAttributes.addFlashAttribute("returnUrl", request.getContextPath()+"/bookmark/myList");
			return "redirect:/admin/member/login";
		}
		
		bookmarkVO.setMod_id(member_id);
		bookmarkVO.setMod_name(member_name);
		bookmarkVO.setMod_nickname(member_nickname);
		
		int result = bookmarkService.deleteBookmark(bookmarkVO);
		if(result > 0){
			redirectAttributes.addFlashAttribute("alertMessage", messageSource.getMessage("bookmark.delete.complete", null, locale));
		}else{
			redirectAttributes.addFlashAttribute("alertMessage", messageSource.getMessage("bookmark.delete.error", null, locale));
		}
		
		return "redirect:/admin/bookmark/myList";
	}
}
