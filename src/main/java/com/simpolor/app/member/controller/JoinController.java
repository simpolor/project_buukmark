package com.simpolor.app.member.controller;

import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.simpolor.app.common.component.MailSender;
import com.simpolor.app.common.util.EncryptUtil;
import com.simpolor.app.common.util.StringUtil;
import com.simpolor.app.common.util.ValidateUtil;
import com.simpolor.app.member.service.JoinService;
import com.simpolor.app.member.vo.MemberVO;

@Controller
public class JoinController {
	
	@Autowired
	private JoinService joinService;
	
	@Autowired
    EncryptUtil encryptUtil;
	
	@Autowired
    MessageSource messageSource;
	
	Locale locale;
	
	// 회원가입 폼
	@RequestMapping(value = "/member/join", method = RequestMethod.GET)
	public String join(HttpServletRequest request, Model model, MemberVO memberVO) {
		
		return "/app/member/join";
	}
	
	// 회원가입 처리
	@RequestMapping(value = "/member/join", method = RequestMethod.POST)
	public String join(HttpServletRequest request, RedirectAttributes redirectAttributes, Model model, MemberVO memberVO) {

		String member_id = memberVO.getMember_id();
		String member_pw = memberVO.getMember_pw();
		String member_pw2 = memberVO.getMember_pw2();
		String member_email = memberVO.getMember_email();
		
		// 비밀번호 유효성검사
		/*if(!ValidateUtil.isPassword(member_pw)){
			model.addAttribute("memberVO", memberVO);
			model.addAttribute("alertMessage", messageSource.getMessage("join.form.password", null, locale));
			
			return "/app/member/join";
		}*/
				
		// 이메일 유효성검사
		if(!ValidateUtil.isEmail(member_email)){
			model.addAttribute("memberVO", memberVO);
			model.addAttribute("alertMessage", messageSource.getMessage("join.form.email", null, locale));
			
			return "/app/member/join";
		}
		
		if(member_pw.equals(member_pw2)){
			int idDupCheckResult = joinService.selectMemberIdDupCheck(member_id);
			if(idDupCheckResult == 0){
				int emailDupCheckResult = joinService.selectMemberEmailDupCheck(member_email);
				if(emailDupCheckResult == 0){
					// 비밀번호 암호화
					memberVO.setMember_pw(encryptUtil.getEncMD5(member_pw));
					int result = joinService.insertJoinMember(memberVO);
					
					if(result > 0){
						redirectAttributes.addFlashAttribute("memberVO", memberVO);
						
						return "redirect:/member/joinComplete";
						
					}else{
						model.addAttribute("memberVO", memberVO);
						model.addAttribute("alertMessage", messageSource.getMessage("join.register.error", null, locale));
						
						return "/app/member/join";
					}
				}else{
					model.addAttribute("memberVO", memberVO);
					model.addAttribute("alertMessage", messageSource.getMessage("join.duplication.email", null, locale));
					
					return "/app/member/join";
				}
			}else{
				model.addAttribute("memberVO", memberVO);
				model.addAttribute("alertMessage", messageSource.getMessage("join.duplication.id", null, locale));
				
				return "/app/member/join";
			}
		}else{
			model.addAttribute("memberVO", memberVO);
			model.addAttribute("alertMessage", messageSource.getMessage("join.notmatch.password", null, locale));
			
			return "/app/member/join";
		}
	}
	
	// 회원가입 완료
	@RequestMapping(value = "/member/joinComplete", method = RequestMethod.GET)
	public String joinComplete(HttpServletRequest request, RedirectAttributes redirectAttributes, Model model, MemberVO memberVO){
		
		String member_id = memberVO.getMember_id();
		if(member_id == null || member_id.equals("")){
			redirectAttributes.addFlashAttribute("alertMessage", messageSource.getMessage("path.access.required", null, locale));
			return "redirect:/member/join";
		}
		
		return "/app/member/joinComplete";
	}
	
	/*public String joinComplete(HttpServletRequest request, RedirectAttributes redirectAttributes, Model model, MemberVO memberVO){
		
		Map<String, ?> flashMap = RequestContextUtils.getInputFlashMap(request);
		if(flashMap != null){
			System.out.println("alertMessage: "+flashMap.get("alertMessage"));
		}
		return "/app/member/joinComplete";
	}*/
	
}
