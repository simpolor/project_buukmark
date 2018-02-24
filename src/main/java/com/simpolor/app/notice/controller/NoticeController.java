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

/**
 * Handles requests for the application home page.
 */
@Controller
public class NoticeController {
	
	private static final Logger logger = LoggerFactory.getLogger(NoticeController.class);
	
	@Autowired
	private NoticeService noticeService;
	
	@Autowired
	private NoticeReplyService noticeReplyService;
	
	@Autowired
    MessageSource messageSource;
	
	Locale locale;

	@RequestMapping(value = "/notice/list", method = RequestMethod.GET)
	public String noticeList(HttpServletRequest request, HttpSession session, Model model, NoticeVO noticeVO){
				
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
		
		return "app/notice/list";
	}
	
	@RequestMapping(value = "/notice/view", method = RequestMethod.GET)
	public String noticeView(HttpServletRequest request, HttpSession session, RedirectAttributes redirectAttributes, Model model, NoticeVO noticeVO) {
		
		noticeVO = noticeService.selectNotice(noticeVO);
		
		NoticeReplyVO noticeReplyVO = new NoticeReplyVO();
		noticeReplyVO.setNotice_seq(noticeVO.getNotice_seq());
		List<NoticeReplyVO> noticeReplyList = noticeReplyService.selectNoticeReplyList(noticeReplyVO);
		
		model.addAttribute("noticeVO", noticeVO);
		model.addAttribute("noticeReplyList", noticeReplyList);
		
		return "app/notice/view";
	}
	
}
