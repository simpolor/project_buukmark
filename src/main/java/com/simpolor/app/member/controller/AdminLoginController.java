package com.simpolor.app.member.controller;

import java.security.PrivateKey;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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

import com.simpolor.app.Defines;
import com.simpolor.app.common.util.EncryptUtil;
import com.simpolor.app.common.util.RSA;
import com.simpolor.app.common.util.RSAUtil;
import com.simpolor.app.common.util.StringUtil;
import com.simpolor.app.member.service.LoginService;
import com.simpolor.app.member.vo.MemberVO;

/**
 * Handles requests for the application home page.
 */
@Controller
public class AdminLoginController {
	
	private static final Logger logger = LoggerFactory.getLogger(AdminLoginController.class);
	
	@Value("${administrator.identify}")
	private String admin_id;
	
	@Value("${administrator.password}")
	private String admin_pw;
	
	@Value("${administrator.name}")
	private String admin_name;
	
	@Value("${administrator.nickname}")
	private String admin_nickname;
	
	@Value("${administrator.level}")
	private String admin_level;
	
	@Autowired
	private LoginService loginService;
	
	@Autowired
    MessageSource messageSource;
	
	@Autowired
    RSAUtil rsaUtil;
	
	@Autowired
    EncryptUtil encryptUtil;
	
	Locale locale;

	// 로그인 폼
	@RequestMapping(value = "/admin/member/login", method = RequestMethod.GET)
	public String login(Model model, MemberVO memberVO, HttpSession session) {
		
		 // RSA 키 생성
	    PrivateKey key = (PrivateKey) session.getAttribute("RSAprivateKey");
	    if (key != null) { // 기존 key 파기
	        session.removeAttribute("RSAprivateKey");
	    }
	    RSA rsa = rsaUtil.createRSA();
	    model.addAttribute("modulus", rsa.getModulus());
	    model.addAttribute("exponent", rsa.getExponent());
	    session.setAttribute("RSAprivateKey", rsa.getPrivateKey());
		
	    logger.debug("rsa.getModulus() : "+rsa.getModulus());
	    logger.debug("rsa.getExponent() : "+rsa.getExponent());
	    logger.debug("rsa.getPrivateKey() : "+rsa.getPrivateKey());

	    return "/app/member/adminLogin";
	}
	
	// 로그인 처리
	@RequestMapping(value = "/admin/member/login", method = RequestMethod.POST)
	public String login(HttpServletRequest request, HttpServletResponse response, HttpSession session, RedirectAttributes redirectAttributes, Model model, MemberVO memberVO) {
		
		 // RSA의 개인키를 취득합니다.
	    PrivateKey key = (PrivateKey) session.getAttribute("RSAprivateKey");
	    
	    // session에 저장된 RSA 개인키 삭제
	    session.removeAttribute("RSAprivateKey");
	    
	    if (key == null) {
	    	redirectAttributes.addFlashAttribute(Defines.ALERT_MESSAGE, messageSource.getMessage("access.wrong", null, locale));
	        return "redirect:/admin/member/login";
	    }
	 
	    try {
	    	// 아이디/비밀번호 복호화
	        String member_id = rsaUtil.getDecryptText(key, memberVO.getMember_id());
	        String member_pw = rsaUtil.getDecryptText(key, memberVO.getMember_pw());
	 
	        // 복호화된 평문을 재설정
	        memberVO.setMember_id(member_id);
	        memberVO.setMember_pw(encryptUtil.getEncMD5(member_pw));
	        //memberVO.setMember_pw(member_pw);
	        
	        // 관리자 처리
	        if(StringUtil.isEquals(admin_id, member_id) && StringUtil.isEquals(admin_pw, encryptUtil.getEncMD5(member_pw))) {
	        	session.setAttribute("SESSION_MEMBER_ID", admin_id);
				session.setAttribute("SESSION_MEMBER_NAME", admin_name);
				session.setAttribute("SESSION_MEMBER_NICKNAME", admin_nickname);
				session.setAttribute("SESSION_MEMBER_LEVEL", admin_level);

				return "redirect:/admin/bookmark/list";
	        }
	        
	        // 로그인 처리
	        memberVO = loginService.selectMemberAdminLogin(memberVO);
	        if(memberVO != null){
	        	session.setAttribute("SESSION_MEMBER_ID", memberVO.getMember_id());
				session.setAttribute("SESSION_MEMBER_NAME", memberVO.getMember_name());
				session.setAttribute("SESSION_MEMBER_NICKNAME", memberVO.getMember_nickname());
				session.setAttribute("SESSION_MEMBER_LEVEL", memberVO.getLevel());
				//return "redirect:/admin/home";
				return "redirect:/admin/bookmark/list";
			}else{
				redirectAttributes.addFlashAttribute(Defines.ALERT_MESSAGE, messageSource.getMessage("member.notmatch", null, locale));
		        return "redirect:/admin/member/login";
			}
	        
	    } catch (Exception e) {
	    	e.printStackTrace();
	    	redirectAttributes.addFlashAttribute(Defines.ALERT_MESSAGE, messageSource.getMessage("access.wrong", null, locale));
	        return "redirect:/admin/member/login";
	    }
		
	}
	
	// 로그아웃 처리
	@RequestMapping(value = "/admin/member/logout", method = RequestMethod.GET)
	public String logoutOk(HttpServletRequest request, HttpServletResponse response, HttpSession session, RedirectAttributes redirectAttributes, Model model) {
		
		session.invalidate();

		return "redirect:/admin/member/login";
	}
	
}
