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
	
	@ResponseBody
	@RequestMapping(value = "/bookmark/report", method = RequestMethod.POST)
	public Map<String, Object> bookmarkReport(HttpServletRequest request, HttpSession session, Model model, BookmarkVO bookmarkVO){
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		String member_id = StringUtil.getString(session.getAttribute("SESSION_MEMBER_ID"));
		String member_name = StringUtil.getString(session.getAttribute("SESSION_MEMBER_NAME"));
		String member_nickname = StringUtil.getString(session.getAttribute("SESSION_MEMBER_NICKNAME"));
		if(StringUtil.isEmpty(member_id)){
			resultMap.put(Defines.ASYNC_RESULT, "fail");
			resultMap.put(Defines.ASYNC_REASON, messageSource.getMessage("access.wrong", null, locale));
			return resultMap;
		}
		
		System.out.println("getBookmark_seq : "+bookmarkVO.getBookmark_seq());
		System.out.println("getReport_content : "+bookmarkVO.getReport_content());
		
		bookmarkVO.setReg_id(member_id);
		bookmarkVO.setReg_name(member_name);
		bookmarkVO.setReg_nickname(member_nickname);
		
		int result = bookmarkReportService.insertBookmarkReport(bookmarkVO);
		if(result > 0){
			resultMap.put(Defines.ASYNC_RESULT, "success");
		}else{
			resultMap.put(Defines.ASYNC_RESULT, "fail");
			resultMap.put(Defines.ASYNC_REASON, messageSource.getMessage("result.notfound", null, locale));
		}
		
		return resultMap;
	}
	
	
}
