package com.simpolor.app.member.controller;

import java.security.PrivateKey;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.simpolor.app.common.util.EncryptUtil;
import com.simpolor.app.common.util.RSA;
import com.simpolor.app.common.util.RSAUtil;
import com.simpolor.app.common.util.StringUtil;
import com.simpolor.app.member.service.LoginService;
import com.simpolor.app.member.vo.MemberVO;

@Controller
public class LoginController {
	
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
	@RequestMapping(value = "/member/login", method = RequestMethod.GET)
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
		
	    // System.out.println("rsa.getModulus() : "+rsa.getModulus());
	    // System.out.println("rsa.getExponent() : "+rsa.getExponent());
	    // System.out.println("rsa.getPrivateKey() : "+rsa.getPrivateKey());

	    return "/app/member/login";
	}
	
	// 로그인 처리
	@RequestMapping(value = "/member/login", method = RequestMethod.POST)
	public String login(HttpServletRequest request, HttpServletResponse response, HttpSession session, RedirectAttributes redirectAttributes, Model model, MemberVO memberVO) {
		
		 // RSA의 개인키를 취득합니다.
	    PrivateKey key = (PrivateKey) session.getAttribute("RSAprivateKey");
	    
	    // session에 저장된 RSA 개인키 삭제
	    session.removeAttribute("RSAprivateKey");
	    
	    if (key == null) {
	    		redirectAttributes.addFlashAttribute("alertMessage", messageSource.getMessage("login.access.wrong", null, locale));
	        return "redirect:/member/login";
	    }
	 
	    try {
	    	// 아이디/비밀번호 복호화
	        String member_id = rsaUtil.getDecryptText(key, memberVO.getMember_id());
	        String member_pw = rsaUtil.getDecryptText(key, memberVO.getMember_pw());
	        
	        // 복호화된 평문을 재설정
	        memberVO.setMember_id(member_id);
	        memberVO.setMember_pw(encryptUtil.getEncMD5(member_pw));
	        
	        // 로그인 처리
	        memberVO = loginService.selectMemberLogin(memberVO);
	        
	        if(memberVO != null){
				session.setAttribute("SESSION_MEMBER_ID", memberVO.getMember_id());
				session.setAttribute("SESSION_MEMBER_NAME", memberVO.getMember_name());
				session.setAttribute("SESSION_MEMBER_NICKNAME", memberVO.getMember_nickname());
				session.setAttribute("SESSION_MEMBER_LEVEL", memberVO.getLevel());
				
				String returnUrl = StringUtil.getString(request.getParameter("return_url"));
				if(!StringUtil.isEmpty(returnUrl)){
					return "redirect:"+returnUrl;
				}
				return "redirect:/bookmark/list";
				
			}else{
				redirectAttributes.addFlashAttribute("alertMessage", messageSource.getMessage("login.notmatch", null, locale));
		        return "redirect:/member/login";
			}
	        
	    } catch (Exception e) {
	    	e.printStackTrace();
	    	redirectAttributes.addFlashAttribute("alertMessage", messageSource.getMessage("login.access.wrong", null, locale));
	        return "redirect:/member/login";
	    }
		
	}
	
	// 로그아웃 처리
	@RequestMapping(value = "/member/logout", method = RequestMethod.GET)
	public String logoutOk(HttpServletRequest request, HttpServletResponse response, HttpSession session, RedirectAttributes redirectAttributes, Model model) {
		
		session.invalidate();

		return "redirect:/bookmark/list";
	}
}
