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

import com.simpolor.app.Defines;
import com.simpolor.app.board.vo.BoardReplyVO;
import com.simpolor.app.bookmark.service.BookmarkMyService;
import com.simpolor.app.bookmark.service.BookmarkReportService;
import com.simpolor.app.bookmark.service.BookmarkService;
import com.simpolor.app.bookmark.vo.BookmarkVO;
import com.simpolor.app.common.util.PageInfinity;
import com.simpolor.app.common.util.PageNavigation;
import com.simpolor.app.common.util.StringUtil;
import com.simpolor.app.common.util.ValidateUtil;

/**
 * Handles requests for the application home page.
 */
@Controller
public class BookmarkReportController {
	
	private static final Logger logger = LoggerFactory.getLogger(BookmarkReportController.class);
	
	@Autowired
	private BookmarkReportService bookmarkReportService;
	
	@Autowired
    MessageSource messageSource;
	
	Locale locale;
	
	@RequestMapping(value = "/bookmark/reportList", method = RequestMethod.GET)
	public String bookmarkReportList(HttpServletRequest request, HttpSession session, Model model, BookmarkVO bookmarkVO){
		
		// 나의 북마크를 위한 설정
		String member_id = StringUtil.getString(session.getAttribute("SESSION_MEMBER_ID"));
		bookmarkVO.setReg_id(member_id);
				
		// 전체 갯수 가져오기 
		int totalCount = bookmarkReportService.selectBookmarkReportTotalCount(bookmarkVO);
		
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
		
		List<BookmarkVO> list = bookmarkReportService.selectBookmarkReportList(bookmarkVO);

		model.addAttribute("bookmarkReportList", list);
		model.addAttribute("paging", paging);
		
		return "app/bookmark/list";
	}
	
	
}
