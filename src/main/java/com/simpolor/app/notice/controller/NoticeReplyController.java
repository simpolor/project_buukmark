package com.simpolor.app.notice.controller;

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

import com.simpolor.app.notice.service.NoticeReplyService;
import com.simpolor.app.notice.vo.NoticeReplyVO;
import com.simpolor.app.common.util.StringUtil;

/**
 * Handles requests for the application home page.
 */
@Controller
public class NoticeReplyController {
	
	private static final Logger logger = LoggerFactory.getLogger(NoticeReplyController.class);
	
	@Autowired
	private NoticeReplyService noticeReplyService;
	
	@Autowired
    MessageSource messageSource;
	
	Locale locale;

	@ResponseBody
	@RequestMapping(value="/notice/replyWrite", method=RequestMethod.POST)
	public Map<String, Object> noticeReplyWrite(HttpServletRequest request, HttpSession session, Model model, NoticeReplyVO noticeReplyVO) {
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		String member_id = StringUtil.getString(session.getAttribute("SESSION_MEMBER_ID"));
		String member_name = StringUtil.getString(session.getAttribute("SESSION_MEMBER_NAME"));
		String member_nickname = StringUtil.getString(session.getAttribute("SESSION_MEMBER_NICKNAME"));
		if(StringUtil.isEmpty(member_id)){
			resultMap.put("result", "fail");
			resultMap.put("reason", messageSource.getMessage("login.required", null, locale));
			return resultMap;
		}
		
		noticeReplyVO.setReg_id(member_id);
		noticeReplyVO.setReg_name(member_name);
		noticeReplyVO.setReg_nickname(member_nickname);
		noticeReplyVO.setReply_content(StringUtil.replaceHtmlY(noticeReplyVO.getReply_content()));
		
		int result = noticeReplyService.insertNoticeReply(noticeReplyVO);
		if(result > 0){
			List<NoticeReplyVO> list = noticeReplyService.selectNoticeReplyList(noticeReplyVO);
			resultMap.put("result", "success");
			resultMap.put("list", list);
		}else{
			resultMap.put("result", "fail");
			resultMap.put("reason", messageSource.getMessage("result.not.found", null, locale));
		}
		
		return resultMap;
		
	}
	
	@ResponseBody
	@RequestMapping(value="/notice/replyModify", method=RequestMethod.POST)
	public Map<String, Object> noticeReplyModify(HttpServletRequest request, HttpSession session, Model model, NoticeReplyVO noticeReplyVO) {
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		String member_id = StringUtil.getString(session.getAttribute("SESSION_MEMBER_ID"));
		String member_name = StringUtil.getString(session.getAttribute("SESSION_MEMBER_NAME"));
		String member_nickname = StringUtil.getString(session.getAttribute("SESSION_MEMBER_NICKNAME"));
		if(StringUtil.isEmpty(member_id)){
			resultMap.put("result", "fail");
			resultMap.put("reason", messageSource.getMessage("login.required", null, locale));
			return resultMap;
		}
		
		noticeReplyVO.setMod_id(member_id);
		noticeReplyVO.setMod_name(member_name);
		noticeReplyVO.setMod_nickname(member_nickname);
		noticeReplyVO.setReply_content(StringUtil.replaceHtmlY(noticeReplyVO.getReply_content()));
		
		int result = noticeReplyService.updateNoticeReply(noticeReplyVO);
		if(result > 0){
			List<NoticeReplyVO> list = noticeReplyService.selectNoticeReplyList(noticeReplyVO);
			resultMap.put("result", "success");
			resultMap.put("list", list);
		}else{
			resultMap.put("result", "fail");
			resultMap.put("reason", messageSource.getMessage("result.not.found", null, locale));
		}
		
		return resultMap;
		
	}
	
	@ResponseBody
	@RequestMapping(value = "/notice/replyDelete", method = RequestMethod.POST)
	public Map<String, Object> noticeReplyDelete(HttpServletRequest request, HttpSession session, Model model, NoticeReplyVO noticeReplyVO) {
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		String member_id = StringUtil.getString(session.getAttribute("SESSION_MEMBER_ID"));
		String member_name = StringUtil.getString(session.getAttribute("SESSION_MEMBER_NAME"));
		String member_nickname = StringUtil.getString(session.getAttribute("SESSION_MEMBER_NICKNAME"));
		if(StringUtil.isEmpty(member_id)){
			resultMap.put("result", "fail");
			resultMap.put("reason", messageSource.getMessage("login.required", null, locale));
			return resultMap;
		}
		
		noticeReplyVO.setMod_id(member_id);
		noticeReplyVO.setMod_name(member_name);
		noticeReplyVO.setMod_nickname(member_nickname);
		
		int result = noticeReplyService.deleteNoticeReply(noticeReplyVO);
		if(result > 0){
			List<NoticeReplyVO> list = noticeReplyService.selectNoticeReplyList(noticeReplyVO);
			resultMap.put("result", "success");
			resultMap.put("list", list);
		}else{
			resultMap.put("result", "fail");
			resultMap.put("reason", messageSource.getMessage("result.not.found", null, locale));
		}
		
		return resultMap;
	}
}
