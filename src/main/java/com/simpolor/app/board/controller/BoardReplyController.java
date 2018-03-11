package com.simpolor.app.board.controller;

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
public class BoardReplyController {
	
	private static final Logger logger = LoggerFactory.getLogger(BoardReplyController.class);
	
	@Autowired
	private BoardReplyService boardReplyService;
	
	@Autowired
    MessageSource messageSource;
	
	Locale locale;

	@ResponseBody
	@RequestMapping(value="/board/replyWrite", method=RequestMethod.POST)
	public Map<String, Object> boardReplyWrite(HttpServletRequest request, HttpSession session, Model model, BoardReplyVO boardReplyVO) {
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		String member_id = StringUtil.getString(session.getAttribute("SESSION_MEMBER_ID"));
		String member_name = StringUtil.getString(session.getAttribute("SESSION_MEMBER_NAME"));
		String member_nickname = StringUtil.getString(session.getAttribute("SESSION_MEMBER_NICKNAME"));
		if(StringUtil.isEmpty(member_id)){
			resultMap.put(Defines.ASYNC_RESULT, "success");
			resultMap.put(Defines.ASYNC_REASON, messageSource.getMessage("access.wrong", null, locale));
			return resultMap;
		}
		
		boardReplyVO.setReg_id(member_id);
		boardReplyVO.setReg_name(member_name);
		boardReplyVO.setReg_nickname(member_nickname);
		boardReplyVO.setReply_content(StringUtil.replaceHtmlY(boardReplyVO.getReply_content()));
		
		int result = boardReplyService.insertBoardReply(boardReplyVO);
		if(result > 0){
			List<BoardReplyVO> list = boardReplyService.selectBoardReplyList(boardReplyVO);
			resultMap.put(Defines.ASYNC_RESULT, "success");
			resultMap.put(Defines.ASYNC_LIST, list);
		}else{
			resultMap.put(Defines.ASYNC_RESULT, "fail");
			resultMap.put(Defines.ASYNC_REASON, messageSource.getMessage("result.notfound", null, locale));
		}
		
		return resultMap;
		
	}
	
	@ResponseBody
	@RequestMapping(value="/board/replyModify", method=RequestMethod.POST)
	public Map<String, Object> boardReplyModify(HttpServletRequest request, HttpSession session, Model model, BoardReplyVO boardReplyVO) {
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		String member_id = StringUtil.getString(session.getAttribute("SESSION_MEMBER_ID"));
		String member_name = StringUtil.getString(session.getAttribute("SESSION_MEMBER_NAME"));
		String member_nickname = StringUtil.getString(session.getAttribute("SESSION_MEMBER_NICKNAME"));
		if(StringUtil.isEmpty(member_id)){
			resultMap.put(Defines.ASYNC_RESULT, "fail");
			resultMap.put(Defines.ASYNC_REASON, messageSource.getMessage("access.wrong", null, locale));
			return resultMap;
		}
		
		boardReplyVO.setMod_id(member_id);
		boardReplyVO.setMod_name(member_name);
		boardReplyVO.setMod_nickname(member_nickname);
		boardReplyVO.setReply_content(StringUtil.replaceHtmlY(boardReplyVO.getReply_content()));
		
		int result = boardReplyService.updateBoardReply(boardReplyVO);
		if(result > 0){
			List<BoardReplyVO> list = boardReplyService.selectBoardReplyList(boardReplyVO);
			resultMap.put(Defines.ASYNC_RESULT, "success");
			resultMap.put(Defines.ASYNC_LIST, list);
		}else{
			resultMap.put(Defines.ASYNC_RESULT, "fail");
			resultMap.put(Defines.ASYNC_REASON, messageSource.getMessage("result.notfound", null, locale));
		}
		
		return resultMap;
		
	}
	
	@ResponseBody
	@RequestMapping(value = "/board/replyDelete", method = RequestMethod.POST)
	public Map<String, Object> boardReplyDelete(HttpServletRequest request, HttpSession session, Model model, BoardReplyVO boardReplyVO) {
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		String member_id = StringUtil.getString(session.getAttribute("SESSION_MEMBER_ID"));
		String member_name = StringUtil.getString(session.getAttribute("SESSION_MEMBER_NAME"));
		String member_nickname = StringUtil.getString(session.getAttribute("SESSION_MEMBER_NICKNAME"));
		if(StringUtil.isEmpty(member_id)){
			resultMap.put(Defines.ASYNC_RESULT, "fail");
			resultMap.put(Defines.ASYNC_REASON, messageSource.getMessage("access.wrong", null, locale));
			return resultMap;
		}
		
		boardReplyVO.setMod_id(member_id);
		boardReplyVO.setMod_name(member_name);
		boardReplyVO.setMod_nickname(member_nickname);
		
		int result = boardReplyService.deleteBoardReply(boardReplyVO);
		if(result > 0){
			List<BoardReplyVO> list = boardReplyService.selectBoardReplyList(boardReplyVO);
			resultMap.put(Defines.ASYNC_RESULT, "success");
			resultMap.put(Defines.ASYNC_LIST, list);
		}else{
			resultMap.put(Defines.ASYNC_RESULT, "fail");
			resultMap.put(Defines.ASYNC_REASON, messageSource.getMessage("result.notfound", null, locale));
		}
		
		return resultMap;
	}
}
