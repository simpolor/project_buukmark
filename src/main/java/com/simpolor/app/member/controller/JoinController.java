package com.simpolor.app.member.controller;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

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
public class JoinController {
	
	@Autowired
	private MemberService memberService;
	
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

		// 아이디 유효성검사
		if(!ValidateUtil.isIdentity(memberVO.getMember_id(), 6, 20)){
			model.addAttribute(Defines.ALERT_MESSAGE, messageSource.getMessage("required.identify", null, locale));
			model.addAttribute("memberVO", memberVO);
			
			return "/app/member/join";
		}
		
		// 비밀번호 유효성검사
		if(!ValidateUtil.isPassword(memberVO.getMember_pw(), 6, 16)){
			model.addAttribute(Defines.ALERT_MESSAGE, messageSource.getMessage("required.password", null, locale));
			model.addAttribute("memberVO", memberVO);
			
			return "/app/member/join";
		}
		
		// 이름 유효성검사
		if(!ValidateUtil.isName(memberVO.getMember_name(), 2, 25)){
			model.addAttribute(Defines.ALERT_MESSAGE, messageSource.getMessage("required.name", null, locale));
			model.addAttribute("memberVO", memberVO);
			
			return "/app/member/join";
		}
		
		// 닉네임 유효성검사
		if(!ValidateUtil.isNickname(memberVO.getMember_nickname(), 2, 25)){
			model.addAttribute(Defines.ALERT_MESSAGE, messageSource.getMessage("required.nickname", null, locale));
			model.addAttribute("memberVO", memberVO);
			
			return "/app/member/join";
		}
				
		// 이메일 유효성검사
		if(!ValidateUtil.isEmail(memberVO.getMember_email())){
			model.addAttribute(Defines.ALERT_MESSAGE, messageSource.getMessage("required.email", null, locale));
			model.addAttribute("memberVO", memberVO);
			
			return "/app/member/join";
		}
		
		if(StringUtil.isEquals(memberVO.getMember_pw(), memberVO.getMember_pw2())){
			int idDupCheckResult = memberService.selectMemberIdDupCheck(memberVO);
			if(idDupCheckResult == 0){
				int nicknameDupCheckResult = memberService.selectMemberNicknameDupCheck(memberVO);
				if(nicknameDupCheckResult == 0){
					int emailDupCheckResult = memberService.selectMemberEmailDupCheck(memberVO);
					if(emailDupCheckResult == 0){
						// 비밀번호 암호화
						memberVO.setMember_pw(encryptUtil.getEncMD5(memberVO.getMember_pw()));
						int result = memberService.insertMember(memberVO);
						
						if(result > 0){
							redirectAttributes.addFlashAttribute("memberVO", memberVO);
							
							return "redirect:/member/joinComplete";
							
						}else{
							model.addAttribute(Defines.ALERT_MESSAGE, messageSource.getMessage("member.register.error", null, locale));
							model.addAttribute("memberVO", memberVO);
							
							return "/app/member/join";
						}
					}else{
						model.addAttribute(Defines.ALERT_MESSAGE, messageSource.getMessage("member.duplication.email", null, locale));
						model.addAttribute("memberVO", memberVO);
						
						return "/app/member/join";
					}
				}else{
					model.addAttribute(Defines.ALERT_MESSAGE, messageSource.getMessage("member.duplication.nickname", null, locale));
					model.addAttribute("memberVO", memberVO);
					
					return "/app/member/join";
				}
			}else{
				model.addAttribute(Defines.ALERT_MESSAGE, messageSource.getMessage("member.duplication.id", null, locale));
				model.addAttribute("memberVO", memberVO);
				
				return "/app/member/join";
			}
		}else{
			model.addAttribute(Defines.ALERT_MESSAGE, messageSource.getMessage("member.notmatch.password.confirm", null, locale));
			model.addAttribute("memberVO", memberVO);
			
			return "/app/member/join";
		}
	}
	
	// 회원가입 완료
	@RequestMapping(value = "/member/joinComplete", method = RequestMethod.GET)
	public String joinComplete(HttpServletRequest request, RedirectAttributes redirectAttributes, Model model, MemberVO memberVO){
		
		String member_id = StringUtil.getString(memberVO.getMember_id());
		if(StringUtil.isEmpty(member_id)){
			redirectAttributes.addFlashAttribute(Defines.ALERT_MESSAGE, messageSource.getMessage("access.wrong", null, locale));
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
