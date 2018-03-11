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
public class AdminAskController {
	
	private static final Logger logger = LoggerFactory.getLogger(AdminAskController.class);
	
	@Autowired
	private AskService askService;
	
	@Autowired
    MessageSource messageSource;
	
	Locale locale;

	@RequestMapping(value = "/admin/ask/list", method = RequestMethod.GET)
	public String askList(HttpServletRequest request, HttpSession session, RedirectAttributes redirectAttributes, Model model, AskVO askVO){
		
		String member_id = StringUtil.getString(session.getAttribute("SESSION_MEMBER_ID"));
		int member_level = StringUtil.getInt(session.getAttribute("SESSION_MEMBER_LEVEL"));
		if(StringUtil.isEmpty(member_id) || member_level < 90){
			redirectAttributes.addFlashAttribute(Defines.ALERT_MESSAGE, messageSource.getMessage("required.login", null, locale));
			redirectAttributes.addFlashAttribute(Defines.RETURN_URL, request.getRequestURL());
			return "redirect:/admin/member/login";
		}
		
		// 전체 갯수 가져오기 
		int totalCount = askService.selectAskTotalCount(askVO);
		
		// page 파라미터 값 가져오기
		int page = askVO.getPage();
		
		 // 페이징 정보 추가
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
		
		return "app/ask/adminList";
	}
	
	@RequestMapping(value = "/admin/ask/view", method = RequestMethod.GET)
	public String askView(HttpServletRequest request, RedirectAttributes redirectAttributes, HttpSession session, Model model, AskVO askVO) {
		
		String member_id = StringUtil.getString(session.getAttribute("SESSION_MEMBER_ID"));
		int member_level = StringUtil.getInt(session.getAttribute("SESSION_MEMBER_LEVEL"));
		if(StringUtil.isEmpty(member_id) || member_level < 90){
			redirectAttributes.addFlashAttribute(Defines.ALERT_MESSAGE, messageSource.getMessage("required.login", null, locale));
			redirectAttributes.addFlashAttribute(Defines.RETURN_URL, request.getRequestURL());
			return "redirect:/admin/member/login";
		}
		
		askVO = askService.selectAsk(askVO);
		model.addAttribute("askVO", askVO);
		
		return "app/ask/adminView";
	}
	
	@RequestMapping(value = "/admin/ask/write", method = RequestMethod.GET)
	public String askWriteForm(HttpServletRequest request, RedirectAttributes redirectAttributes, HttpSession session, Model model, AskVO askVO) {
		
		String member_id = StringUtil.getString(session.getAttribute("SESSION_MEMBER_ID"));
		int member_level = StringUtil.getInt(session.getAttribute("SESSION_MEMBER_LEVEL"));
		if(StringUtil.isEmpty(member_id) || member_level < 90){
			redirectAttributes.addFlashAttribute(Defines.ALERT_MESSAGE, messageSource.getMessage("required.login", null, locale));
			redirectAttributes.addFlashAttribute(Defines.RETURN_URL, request.getRequestURL());
			return "redirect:/admin/member/login";
		}
		
		return "app/ask/adminWrite";
		
	}
	
	@RequestMapping(value = "/admin/ask/write", method = RequestMethod.POST)
	public String askWrite(HttpServletRequest request, RedirectAttributes redirectAttributes, HttpSession session, Model model, AskVO askVO) {
		
		String member_id = StringUtil.getString(session.getAttribute("SESSION_MEMBER_ID"));
		String member_name = StringUtil.getString(session.getAttribute("SESSION_MEMBER_NAME"));
		String member_nickname = StringUtil.getString(session.getAttribute("SESSION_MEMBER_NICKNAME"));
		int member_level = StringUtil.getInt(session.getAttribute("SESSION_MEMBER_LEVEL"));
		if(StringUtil.isEmpty(member_id) || member_level < 90){
			redirectAttributes.addFlashAttribute(Defines.ALERT_MESSAGE, messageSource.getMessage("access.wrong", null, locale));
			redirectAttributes.addFlashAttribute(Defines.RETURN_URL, request.getRequestURL());
			return "redirect:/admin/member/login";
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
		
		return "redirect:/admin/ask/view?ask_seq="+askVO.getAsk_seq();
		
	}
	
	@RequestMapping(value = "/admin/ask/modify", method = RequestMethod.GET)
	public String askModifyForm(HttpServletRequest request, RedirectAttributes redirectAttributes, HttpSession session, Model model, AskVO askVO) {
		
		String member_id = StringUtil.getString(session.getAttribute("SESSION_MEMBER_ID"));
		int member_level = StringUtil.getInt(session.getAttribute("SESSION_MEMBER_LEVEL"));
		if(StringUtil.isEmpty(member_id) || member_level < 90){
			redirectAttributes.addFlashAttribute("alertMessage", messageSource.getMessage("required.login", null, locale));
			redirectAttributes.addFlashAttribute("returnUrl", request.getRequestURL());
			return "redirect:/admin/member/login";
		}
		
		askVO = askService.selectAsk(askVO);
		//askVO.setAsk_content(StringUtil.replaceHtmlN(askVO.getAsk_content()));
		
		model.addAttribute("askVO", askVO);
		
		return "app/ask/adminModify";
	}
	
	@RequestMapping(value = "/admin/ask/modify", method = RequestMethod.POST)
	public String bookmarkMod(HttpServletRequest request, RedirectAttributes redirectAttributes, HttpSession session, Model model, AskVO askVO) {
		
		String member_id = StringUtil.getString(session.getAttribute("SESSION_MEMBER_ID"));
		String member_name = StringUtil.getString(session.getAttribute("SESSION_MEMBER_NAME"));
		String member_nickname = StringUtil.getString(session.getAttribute("SESSION_MEMBER_NICKNAME"));
		int member_level = StringUtil.getInt(session.getAttribute("SESSION_MEMBER_LEVEL"));
		if(StringUtil.isEmpty(member_id) || member_level < 90){
			redirectAttributes.addFlashAttribute("alertMessage", messageSource.getMessage("access.wrong", null, locale));
			redirectAttributes.addFlashAttribute("returnUrl", request.getRequestURL());
			return "redirect:/admin/member/login";
		}
		
		askVO.setMod_id(member_id);
		askVO.setMod_name(member_name);
		askVO.setMod_nickname(member_nickname);
		//askVO.setAsk_content(StringUtil.replaceHtmlY(askVO.getAsk_content()));
		
		int result = askService.updateAsk(askVO);
		if(result > 0){
			//redirectAttributes.addFlashAttribute("alertMessage", messageSource.getMessage("modify.complete", null, locale));
		}else{
			redirectAttributes.addFlashAttribute("alertMessage", messageSource.getMessage("modify.error", null, locale));
		}
		
		return "redirect:/admin/ask/view?ask_seq="+askVO.getAsk_seq();
		
	}
	
	@RequestMapping(value = "/admin/ask/delete", method = RequestMethod.POST)
	public String bookmarkDel(HttpServletRequest request, RedirectAttributes redirectAttributes, HttpSession session, Model model, AskVO askVO) {
		
		String member_id = StringUtil.getString(session.getAttribute("SESSION_MEMBER_ID"));
		String member_name = StringUtil.getString(session.getAttribute("SESSION_MEMBER_NAME"));
		String member_nickname = StringUtil.getString(session.getAttribute("SESSION_MEMBER_NICKNAME"));
		int member_level = StringUtil.getInt(session.getAttribute("SESSION_MEMBER_LEVEL"));
		if(StringUtil.isEmpty(member_id) || member_level < 90){
			redirectAttributes.addFlashAttribute("alertMessage", messageSource.getMessage("access.wrong", null, locale));
			redirectAttributes.addFlashAttribute("returnUrl", request.getContextPath()+"/admin/ask/view?ask_seq="+askVO.getAsk_seq());
			return "redirect:/admin/member/login";
		}
		
		askVO.setMod_id(member_id);
		askVO.setMod_name(member_name);
		askVO.setMod_nickname(member_nickname);
		
		int result = askService.deleteAsk(askVO);
		if(result > 0){
			//redirectAttributes.addFlashAttribute("alertMessage", messageSource.getMessage("delete.complete", null, locale));
		}else{
			redirectAttributes.addFlashAttribute("alertMessage", messageSource.getMessage("delete.error", null, locale));
		}
		
		return "redirect:/admin/ask/list";
	}
	
}
