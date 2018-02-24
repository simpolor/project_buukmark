package com.simpolor.app.main.controller;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.simpolor.app.common.component.MailComponent;
import com.simpolor.app.common.util.StringUtil;

/**
 * Handles requests for the application home page.
 */
@Controller
public class AdminMainController {
	
	private static final Logger logger = LoggerFactory.getLogger(AdminMainController.class);
	
	@Autowired
    MessageSource messageSource;
	
	@Autowired
	MailComponent mailComponent;

	Locale locale;
	
	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	public String admin(HttpServletRequest request, RedirectAttributes redirectAttributes, HttpSession session) {

		String recipient = "simpolor@naver.com";
		String subject = "제목입니다.";
		String content = "내용입니다.";
		System.out.println("mailSender : "+mailComponent.mailSender(recipient, subject, content));
		
		return "index";
		/*String member_id = StringUtil.getString(session.getAttribute("SESSION_MEMBER_ID"));
		int member_level = StringUtil.getInt(session.getAttribute("SESSION_MEMBER_LEVEL"));
		if(StringUtil.isEmpty(member_id) || member_level < 90){
			redirectAttributes.addFlashAttribute("alertMessage", messageSource.getMessage("login.required", null, locale));
			return "redirect:/admin/member/login";
		}*/
		
		//return "redirect:/admin/bookmark/list";
	}
}
