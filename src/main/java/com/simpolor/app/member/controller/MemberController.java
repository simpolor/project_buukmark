package com.simpolor.app.member.controller;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.simpolor.app.Defines;
import com.simpolor.app.common.util.EncryptUtil;
import com.simpolor.app.common.util.StringUtil;
import com.simpolor.app.common.util.ValidateUtil;
import com.simpolor.app.member.service.MemberService;
import com.simpolor.app.member.vo.MemberVO;

@Controller
public class MemberController {
	
	@Autowired
	private MemberService memberService;
	
	@Autowired
    EncryptUtil encryptUtil;
	
	@Autowired
    MessageSource messageSource;
	
	Locale locale;
	
	// 회원정보 상세조회
	@RequestMapping(value = "/member/view", method = RequestMethod.GET)
	public String view(HttpServletRequest request, RedirectAttributes redirectAttributes, Model model, HttpSession session) {
		
		String member_id = StringUtil.getString(session.getAttribute("SESSION_MEMBER_ID"));
		if(StringUtil.isEmpty(member_id)){
			redirectAttributes.addFlashAttribute(Defines.ALERT_MESSAGE, messageSource.getMessage("required.login", null, locale));
			return "redirect:/member/login";
		}
		
		MemberVO memberVO = memberService.selectMember(member_id);
		
		model.addAttribute("memberVO", memberVO);

		return "/app/member/view";
	}
	
	// 회원정보 수정 폼
	@RequestMapping(value = "/member/edit", method = RequestMethod.GET)
	public String edit(HttpServletRequest request, RedirectAttributes redirectAttributes, HttpSession session, Model model) {
		
		String member_id = StringUtil.getString(session.getAttribute("SESSION_MEMBER_ID"));
		if(StringUtil.isEmpty(member_id)){
			redirectAttributes.addFlashAttribute(Defines.ALERT_MESSAGE, messageSource.getMessage("required.login", null, locale));
			return "redirect:/member/login";
		}
		
		MemberVO memberVO = memberService.selectMember(member_id);
		model.addAttribute("memberVO", memberVO);

		return "/app/member/edit";
	}
	
	// 회언정보 수정 처리
	@RequestMapping(value = "/member/edit", method = RequestMethod.POST)
	public String edit(HttpServletRequest request, RedirectAttributes redirectAttributes, HttpSession session, Model model, MemberVO memberVO){
		
		String member_id = StringUtil.getString(session.getAttribute("SESSION_MEMBER_ID"));
		if(StringUtil.isEmpty(member_id)){
			redirectAttributes.addFlashAttribute(Defines.ALERT_MESSAGE, messageSource.getMessage("access.wrong", null, locale));
			return "redirect:/member/login";
		}
		
		// 이름 유효성검사
		if(!ValidateUtil.isName(memberVO.getMember_name(), 2, 25)){
			model.addAttribute(Defines.ALERT_MESSAGE, messageSource.getMessage("required.name", null, locale));
			model.addAttribute("memberVO", memberVO);
			
			return "/app/member/edit";
		}
				
		// 닉네임 유효성검사
		if(!ValidateUtil.isNickname(memberVO.getMember_nickname(), 2, 25)){
			model.addAttribute(Defines.ALERT_MESSAGE, messageSource.getMessage("required.nickname", null, locale));
			model.addAttribute("memberVO", memberVO);
			
			return "/app/member/edit";
		}
				
		// 이메일 유효성검사
		if(!ValidateUtil.isEmail(memberVO.getMember_email())){
			model.addAttribute(Defines.ALERT_MESSAGE, messageSource.getMessage("required.email", null, locale));
			model.addAttribute("memberVO", memberVO);
			
			return "/app/member/edit";
		}

		System.out.println("member_id : "+member_id);
		memberVO.setMember_id(member_id);
		
		int nicknameDupCheckResult = memberService.selectMemberNicknameDupCheck(memberVO);
		if(nicknameDupCheckResult == 0){
			int emailDupCheckResult = memberService.selectMemberEmailDupCheck(memberVO);
			if(emailDupCheckResult == 0){
				int result = memberService.updateMember(memberVO);
				if(result > 0){
					redirectAttributes.addFlashAttribute(Defines.ALERT_MESSAGE, messageSource.getMessage("member.change.profile.complete", null, locale));
					return "redirect:/member/view";
				}else{
					model.addAttribute(Defines.ALERT_MESSAGE, messageSource.getMessage("member.change.profile.error", null, locale));
					return "/app/member/edit";
				}
			}else{
				model.addAttribute(Defines.ALERT_MESSAGE, messageSource.getMessage("member.duplication.email", null, locale));
				model.addAttribute("memberVO", memberVO);
				return "/app/member/edit";
			}
		}else{
			model.addAttribute(Defines.ALERT_MESSAGE, messageSource.getMessage("member.duplication.nickname", null, locale));
			model.addAttribute("memberVO", memberVO);
			return "/app/member/edit";
		}
	}
	
	// 회원 비밀번호 수정 폼
	@RequestMapping(value = "/member/password", method = RequestMethod.GET)
	public String password(HttpServletRequest request, RedirectAttributes redirectAttributes, HttpSession session, Model model){
		
		String member_id = StringUtil.getString(session.getAttribute("SESSION_MEMBER_ID"));
		if(StringUtil.isEmpty(member_id)){
			redirectAttributes.addFlashAttribute(Defines.ALERT_MESSAGE, messageSource.getMessage("required.login", null, locale));
			return "redirect:/member/login";
		}
		
		MemberVO memberVO = new MemberVO();
		memberVO.setMember_id(member_id);
		model.addAttribute("memberVO", memberVO);

		return "/app/member/password";
	}
	
	// 회원 비밀번호 수정 처리
	@RequestMapping(value = "/member/password", method = RequestMethod.POST)
	public String password(HttpServletRequest request, HttpSession session, RedirectAttributes redirectAttributes, Model model, MemberVO memberVO) {
		
		String member_id = StringUtil.getString(session.getAttribute("SESSION_MEMBER_ID"));
		if(StringUtil.isEmpty(member_id)){
			redirectAttributes.addFlashAttribute(Defines.ALERT_MESSAGE, messageSource.getMessage("access.wrong", null, locale));
			return "redirect:/member/login";
		}
		
		String member_pw_org = memberVO.getMember_pw_org();
		String member_pw = memberVO.getMember_pw();
		String member_pw2 = memberVO.getMember_pw2();
		
		if(member_pw.equals(member_pw2)){
			memberVO.setMember_pw_org(encryptUtil.getEncMD5(member_pw_org));
			int dupCheckResult = memberService.selectMemberConfirm(memberVO);
			
			if(dupCheckResult > 0){
				// 비밀번호 암호화
				memberVO.setMember_pw(encryptUtil.getEncMD5(member_pw));
				int result = memberService.updateMemberPassword(memberVO);
				
				if(result > 0){
					redirectAttributes.addFlashAttribute(Defines.ALERT_MESSAGE, messageSource.getMessage("member.change.password.complete", null, locale));
					
					return "redirect:/member/view";
				}else{
					memberVO.setMember_pw("");
					memberVO.setMember_pw2("");
					model.addAttribute(Defines.ALERT_MESSAGE, messageSource.getMessage("member.change.password.error", null, locale));
					model.addAttribute("memberVO", memberVO);
					
					return "/app/member/password";
				}
			}else{
				model.addAttribute(Defines.ALERT_MESSAGE, messageSource.getMessage("member.notmatch.password.old", null, locale));
				model.addAttribute("memberVO", memberVO);
				
				return "/app/member/password";
			}
		}else{
			model.addAttribute(Defines.ALERT_MESSAGE, messageSource.getMessage("member.notmatch.password.new", null, locale));
			model.addAttribute("memberVO", memberVO);
			
			return "/app/member/password";
		}
	}
	
}
