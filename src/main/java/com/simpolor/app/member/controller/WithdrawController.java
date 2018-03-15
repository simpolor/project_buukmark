package com.simpolor.app.member.controller;

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

import com.simpolor.app.Defines;
import com.simpolor.app.common.util.EncryptUtil;
import com.simpolor.app.common.util.StringUtil;
import com.simpolor.app.member.service.MemberService;
import com.simpolor.app.member.vo.MemberVO;

@Controller
public class WithdrawController {
	
	@Autowired
	private MemberService memberService;
	
	@Autowired
    EncryptUtil encryptUtil;
	
	@Autowired
    MessageSource messageSource;
	
	Locale locale;
	
	// 회원탈퇴 폼
	@RequestMapping(value = "/member/withdraw", method = RequestMethod.GET)
	public String withdraw(HttpServletRequest request, HttpSession session, RedirectAttributes redirectAttributes, Model model){
		
		String member_id = StringUtil.getString(session.getAttribute("SESSION_MEMBER_ID"));
		if(StringUtil.isEmpty(member_id)){
			redirectAttributes.addFlashAttribute("alertMessage", messageSource.getMessage("required.login", null, locale));
			return "redirect:/member/login";
		}
		
		MemberVO memberVO = new MemberVO();
		memberVO.setMember_id(member_id);
		
		model.addAttribute("memberVO", memberVO);

		return "/app/member/withdraw";
	}
	
	// 회원탈퇴 처리
	@RequestMapping(value = "/member/withdraw", method = RequestMethod.POST)
	public String withdraw(HttpServletRequest request, HttpSession session, RedirectAttributes redirectAttributes, MemberVO memberVO) {
		
		String member_id = StringUtil.getString(session.getAttribute("SESSION_MEMBER_ID"));
		String member_name = StringUtil.getString(session.getAttribute("SESSION_MEMBER_NAME"));
		if(StringUtil.isEmpty(member_id)){
			redirectAttributes.addFlashAttribute(Defines.ALERT_MESSAGE, messageSource.getMessage("access.wrong", null, locale));
			return "redirect:/member/login";
		}
		
		// 비밀번호 암호화
		memberVO.setMember_pw(encryptUtil.getEncMD5(memberVO.getMember_pw()));
		
		//int result = memberService.deleteMember(memberVO);
		int result = memberService.updateMemberWithdraw(memberVO);
		if(result > 0){
			memberVO.setMember_id(member_id);
			memberVO.setMember_name(member_name);
			redirectAttributes.addFlashAttribute("memberVO", memberVO);
			session.invalidate();
			
			return "redirect:/member/withdrawComplete";
		}else{
			redirectAttributes.addFlashAttribute(Defines.ALERT_MESSAGE, messageSource.getMessage("member.notmatch.password", null, locale));
			redirectAttributes.addFlashAttribute("memberVO", memberVO);
			
			return "redirect:/member/withdraw";
		}
	}
	
	// 회원탈퇴 완료
	@RequestMapping(value = "/member/withdrawComplete", method = RequestMethod.GET)
	public String withdrawComplete(HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes, MemberVO memberVO) {
		
		String member_id = StringUtil.getString(memberVO.getMember_id());
		if(StringUtil.isEmpty(member_id)){
			redirectAttributes.addFlashAttribute(Defines.ALERT_MESSAGE, messageSource.getMessage("access.wrong", null, locale));
			return "redirect:/member/login";
		}
		
		return "/app/member/withdrawComplete";
	}
	
}
