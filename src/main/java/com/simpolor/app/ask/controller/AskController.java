package com.simpolor.app.ask.controller;

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

import com.simpolor.app.Defines;
import com.simpolor.app.ask.service.AskService;
import com.simpolor.app.ask.vo.AskVO;
import com.simpolor.app.common.util.PageNavigation;
import com.simpolor.app.common.util.StringUtil;

/**
 * Handles requests for the application home page.
 */
@Controller
public class AskController {
	
	private static final Logger logger = LoggerFactory.getLogger(AskController.class);
	
	@Autowired
	private AskService askService;
	
	@Autowired
    MessageSource messageSource;
	
	Locale locale;

	@RequestMapping(value = "/ask/list", method = RequestMethod.GET)
	public String askList(HttpServletRequest request, HttpSession session, RedirectAttributes redirectAttributes, Model model, AskVO askVO){
		
		// 전체 갯수 가져오기 
		int totalCount = askService.selectAskTotalCount(askVO);
		
		// 현재 페이지 가져오기
		int page = askVO.getPage();
		 
		// PageNavigation 생성
		PageNavigation paging = new PageNavigation();
		paging.setTotalCount(totalCount);
		paging.setPageNo(page);
		paging.makePageNavigation();
		
		// bookmarkVO에 offset, limit 세팅
		askVO.setOffset(paging.getOffset());
		askVO.setLimit(paging.getLimit());
		
		List<AskVO> list = askService.selectAskList(askVO);

		model.addAttribute("askList", list);
		model.addAttribute("paging", paging);
		
		return "app/ask/list";
	}
	
	@RequestMapping(value = "/ask/view", method = RequestMethod.GET)
	public String askView(HttpServletRequest request, HttpSession session, RedirectAttributes redirectAttributes, Model model, AskVO askVO) {
		
		askVO = askService.selectAsk(askVO);
		
		if("Y".equals(StringUtil.getString(askVO.getSecret_yn()))) {
			String member_id = StringUtil.getString(session.getAttribute("SESSION_MEMBER_ID"));
			if(!StringUtil.isEquals(member_id, askVO.getReg_id())){
				redirectAttributes.addFlashAttribute(Defines.ALERT_MESSAGE, messageSource.getMessage("only.writer", null, locale));
				return "redirect:/ask/list";
			}
		}
		
		model.addAttribute("askVO", askVO);
		
		return "app/ask/view";
	}
	
	@RequestMapping(value = "/ask/write", method = RequestMethod.GET)
	public String askWriteForm(HttpServletRequest request, HttpSession session, RedirectAttributes redirectAttributes, Model model, AskVO askVO) {
		
		String member_id = StringUtil.getString(session.getAttribute("SESSION_MEMBER_ID"));
		if(StringUtil.isEmpty(member_id)){
			redirectAttributes.addFlashAttribute(Defines.ALERT_MESSAGE, messageSource.getMessage("required.login", null, locale));
			redirectAttributes.addFlashAttribute(Defines.RETURN_URL, request.getRequestURL());
			return "redirect:/member/login";
		}
		
		return "app/ask/write";
		
	}
	
	@RequestMapping(value = "/ask/write", method = RequestMethod.POST)
	public String askWrite(HttpServletRequest request, HttpSession session, RedirectAttributes redirectAttributes, Model model, AskVO askVO) {
		
		String member_id = StringUtil.getString(session.getAttribute("SESSION_MEMBER_ID"));
		String member_name = StringUtil.getString(session.getAttribute("SESSION_MEMBER_NAME"));
		String member_nickname = StringUtil.getString(session.getAttribute("SESSION_MEMBER_NICKNAME"));
		if(StringUtil.isEmpty(member_id)){
			redirectAttributes.addFlashAttribute(Defines.ALERT_MESSAGE, messageSource.getMessage("access.wrong", null, locale));
			redirectAttributes.addFlashAttribute(Defines.RETURN_URL, request.getRequestURL());
			return "redirect:/member/login";
		}
		
		askVO.setReg_id(member_id);
		askVO.setReg_name(member_name);
		askVO.setReg_nickname(member_nickname);
		//askVO.setAsk_content(StringUtil.replaceHtmlY(askVO.getAsk_content()));
		
		int result = askService.insertAsk(askVO);
		if(result > 0){
			//redirectAttributes.addFlashAttribute(Defines.ALERT_MESSAGE, messageSource.getMessage("register.complete", null, locale));
		}else{
			redirectAttributes.addFlashAttribute(Defines.ALERT_MESSAGE, messageSource.getMessage("register.error", null, locale));
		}
		
		return "redirect:/ask/view?ask_seq="+askVO.getAsk_seq();
		
	}
	
	@RequestMapping(value = "/ask/modify", method = RequestMethod.GET)
	public String askModifyForm(HttpServletRequest request, HttpSession session, RedirectAttributes redirectAttributes, Model model, AskVO askVO) {

		String member_id = StringUtil.getString(session.getAttribute("SESSION_MEMBER_ID"));
		if(StringUtil.isEmpty(member_id)){
			redirectAttributes.addFlashAttribute(Defines.ALERT_MESSAGE, messageSource.getMessage("required.login", null, locale));
			redirectAttributes.addFlashAttribute(Defines.RETURN_URL, request.getRequestURL());
			return "redirect:/member/login";
		}
		
		askVO = askService.selectAsk(askVO);
		//askVO.setAsk_content(StringUtil.replaceHtmlN(askVO.getAsk_content()));
		
		if(!StringUtil.isEquals(member_id, askVO.getReg_id())){
			redirectAttributes.addFlashAttribute(Defines.ALERT_MESSAGE, messageSource.getMessage("only.writer", null, locale));
			return "redirect:/ask/list";
		}
		
		model.addAttribute("askVO", askVO);
		
		return "app/ask/modify";
	}
	
	@RequestMapping(value = "/ask/modify", method = RequestMethod.POST)
	public String askModify(HttpServletRequest request, HttpSession session, RedirectAttributes redirectAttributes, Model model, AskVO askVO) {
		
		String member_id = StringUtil.getString(session.getAttribute("SESSION_MEMBER_ID"));
		String member_name = StringUtil.getString(session.getAttribute("SESSION_MEMBER_NAME"));
		String member_nickname = StringUtil.getString(session.getAttribute("SESSION_MEMBER_NICKNAME"));
		if(StringUtil.isEmpty(member_id)){
			redirectAttributes.addFlashAttribute(Defines.ALERT_MESSAGE, messageSource.getMessage("access.wrong", null, locale));
			redirectAttributes.addFlashAttribute(Defines.RETURN_URL, request.getRequestURL());
			return "redirect:/member/login";
		}
		
		askVO.setMod_id(member_id);
		askVO.setMod_name(member_name);
		askVO.setMod_nickname(member_nickname);
		//askVO.setAsk_content(StringUtil.replaceHtmlY(askVO.getAsk_content()));
		
		int result = askService.updateAsk(askVO);
		if(result > 0){
			//redirectAttributes.addFlashAttribute(Defines.ALERT_MESSAGE, messageSource.getMessage("modify.complete", null, locale));
		}else{
			redirectAttributes.addFlashAttribute(Defines.ALERT_MESSAGE, messageSource.getMessage("modify.error", null, locale));
		}
		
		return "redirect:/ask/view?ask_seq="+askVO.getAsk_seq();
	}
	
	@RequestMapping(value = "/ask/delete", method = RequestMethod.POST)
	public String askDelete(HttpServletRequest request, HttpSession session, RedirectAttributes redirectAttributes, Model model, AskVO askVO) {
		
		String member_id = StringUtil.getString(session.getAttribute("SESSION_MEMBER_ID"));
		String member_name = StringUtil.getString(session.getAttribute("SESSION_MEMBER_NAME"));
		String member_nickname = StringUtil.getString(session.getAttribute("SESSION_MEMBER_NICKNAME"));
		if(StringUtil.isEmpty(member_id)){
			redirectAttributes.addFlashAttribute(Defines.ALERT_MESSAGE, messageSource.getMessage("access.wrong", null, locale));
			redirectAttributes.addFlashAttribute(Defines.RETURN_URL, request.getContextPath()+"/ask/view?ask_seq="+askVO.getAsk_seq());
			return "redirect:/member/login";
		}
		
		askVO.setMod_id(member_id);
		askVO.setMod_name(member_name);
		askVO.setMod_nickname(member_nickname);
		
		int result = askService.deleteAsk(askVO);
		if(result > 0){
			//redirectAttributes.addFlashAttribute(Defines.ALERT_MESSAGE, messageSource.getMessage("delete.complete", null, locale));
		}else{
			redirectAttributes.addFlashAttribute(Defines.ALERT_MESSAGE, messageSource.getMessage("delete.error", null, locale));
		}
		
		return "redirect:/ask/list";
	}
	
}
