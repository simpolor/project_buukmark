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
import com.simpolor.app.common.component.GenerateCharacter;
import com.simpolor.app.common.component.MailSender;
import com.simpolor.app.common.util.EncryptUtil;
import com.simpolor.app.common.util.StringUtil;
import com.simpolor.app.member.service.SearchService;
import com.simpolor.app.member.vo.MemberVO;

@Controller
public class SearchController {
	
	@Autowired
	private SearchService searchService;
	
	@Autowired
    EncryptUtil encryptUtil;
	
	@Autowired
    MessageSource messageSource;
	
	@Autowired
	MailSender mailSender;
	
	@Autowired
	GenerateCharacter generateCharacter;
	
	Locale locale;
	
	// 회원정보찾기 폼
	@RequestMapping(value = "/member/search", method = RequestMethod.GET)
	public String search(Model model, MemberVO memberVO){

		return "/app/member/search";
	}
	
	// 회원정보찾기 처리
	@RequestMapping(value = "/member/search", method = RequestMethod.POST)
	public String search(HttpServletRequest request, RedirectAttributes redirectAttributes, Model model, MemberVO memberVO) {

		memberVO = searchService.selectMemberSearch(memberVO);
		if(memberVO != null){
			String tempPassword = generateCharacter.excuteGenerate("password", 10);
			System.out.println("tempPassword : "+tempPassword);
			memberVO.setMember_pw(encryptUtil.getEncMD5(tempPassword));
			int result = searchService.updateMemberChange(memberVO);
			if(result > 0){
				String recipient = "simpolor@naver.com";
				String subject = "[buukmark] 아이디 및 임시비밀번호가 발송되었습니다.";
				StringBuffer content = new StringBuffer();
				content.append("<span style=\"font-weight:bold\">"+memberVO.getMember_name() +"</span>님의 아이디 및 임시비밀번호입니다.<br />");
				content.append("<hr />");
				content.append("<ul>");
				content.append("	<li>사이트 : <a href=\"http://buukmark.com\">http://buukmark.com</a></li>");
				content.append("	<li>회원아이디 : "+memberVO.getMember_id()+"</li>");
				content.append("	<li>임시비밀번호 : "+tempPassword+"</li>");
				content.append("</ul>");
				content.append("<hr />");
				content.append("사이트를 이용해주셔서 감사합니다.");
				
				if(mailSender.send(recipient, subject, content.toString())){
					redirectAttributes.addFlashAttribute("memberVO", memberVO);
					return "redirect:/member/searchComplete";
				}else{
					model.addAttribute(Defines.ALERT_MESSAGE, messageSource.getMessage("result.error", null, locale));
					return "/app/member/search";
				}
			}else{
				model.addAttribute(Defines.ALERT_MESSAGE, messageSource.getMessage("result.error", null, locale));
				return "/app/member/search";
			}
			
		}else{
			model.addAttribute(Defines.ALERT_MESSAGE, messageSource.getMessage("member.notmatch", null, locale));
			
			return "/app/member/search";
		}
	}
	
	// 회원정보찾기 완료
	@RequestMapping(value = "/member/searchComplete", method = RequestMethod.GET)
	public String searchComplete(HttpServletRequest request, HttpSession session, RedirectAttributes redirectAttributes, MemberVO memberVO){
		
		String member_id = StringUtil.getString(memberVO.getMember_id());
		if(StringUtil.isEmpty(member_id)){
			redirectAttributes.addFlashAttribute(Defines.ALERT_MESSAGE, messageSource.getMessage("access.wrong", null, locale));
			return "redirect:/member/login";
		}

		return "/app/member/searchComplete";
	}
	
	// 회원 비밀번호 수정 폼
	@RequestMapping(value = "/member/change", method = RequestMethod.GET)
	public String change(HttpServletRequest request, RedirectAttributes redirectAttributes, MemberVO memberVO){
		
		String member_id = StringUtil.getString(memberVO.getMember_id());
		if(StringUtil.isEmpty(member_id)){
			redirectAttributes.addFlashAttribute(Defines.ALERT_MESSAGE, messageSource.getMessage("required.login", null, locale));
			return "redirect:/member/login";
		}
		
		return "/app/member/change";
	}
	
	@RequestMapping(value = "/member/change", method = RequestMethod.POST)
	public String change(HttpServletRequest request, HttpSession session, RedirectAttributes redirectAttributes, Model model, MemberVO memberVO) {

		String member_id = StringUtil.getString(memberVO.getMember_id());
		if(StringUtil.isEmpty(member_id)){
			redirectAttributes.addFlashAttribute(Defines.ALERT_MESSAGE, messageSource.getMessage("access.wrong", null, locale));
			return "redirect:/member/login";
		}
		
		memberVO.setMember_id(memberVO.getMember_id());
		
		if(StringUtil.isEquals(memberVO.getMember_pw(), memberVO.getMember_pw2())){
			memberVO.setMember_pw(encryptUtil.getEncMD5( memberVO.getMember_pw()));
			int result = searchService.updateMemberChange(memberVO);
			if(result > 0){
				redirectAttributes.addFlashAttribute("memberVO", memberVO);

				return "redirect:/member/changeComplete";
			}else{
				model.addAttribute(Defines.ALERT_MESSAGE, messageSource.getMessage("member.change.password.error", null, locale));
				model.addAttribute("memberVO", memberVO);
				
				return "/app/member/change";
			}
		}else{
			model.addAttribute(Defines.ALERT_MESSAGE, messageSource.getMessage("member.notmatch.password", null, locale));
			model.addAttribute("memberVO", memberVO);
			
			return "/app/member/change";
		}
	}
	
	// 회원 비밀번호 수정 완료
	@RequestMapping(value = "/member/changeComplete", method = RequestMethod.GET)
	public String changeComplete(HttpServletRequest request, HttpSession session, RedirectAttributes redirectAttributes, MemberVO memberVO){
		
		String member_id = StringUtil.getString(memberVO.getMember_id());
		if(StringUtil.isEmpty(member_id)){
			redirectAttributes.addFlashAttribute(Defines.ALERT_MESSAGE, messageSource.getMessage("access.wrong", null, locale));
			return "redirect:/member/login";
		}

		return "/app/member/changeComplete";
	}

}
