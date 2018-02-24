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

import com.simpolor.app.common.util.EncryptUtil;
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
	
	Locale locale;
	
	// 회원정보 찾기 폼
	@RequestMapping(value = "/member/search", method = RequestMethod.GET)
	public String search(Model model, MemberVO memberVO){

		return "/app/member/search";
	}
	
	// 회원정보 찾기 처리
	@RequestMapping(value = "/member/search", method = RequestMethod.POST)
	public String search(HttpServletRequest request, RedirectAttributes redirectAttributes, Model model, MemberVO memberVO) {

		memberVO = searchService.selectMemberSearch(memberVO);
		
		if(memberVO != null){
			redirectAttributes.addFlashAttribute("memberVO", memberVO);
			return "redirect:/member/change";
		}else{
			model.addAttribute("alertMessage", messageSource.getMessage("search.notmatch.info", null, locale));
			
			return "/app/member/search";
		}
	}
	
	// 회원 비밀번호 수정 폼
	@RequestMapping(value = "/member/change", method = RequestMethod.GET)
	public String change(HttpServletRequest request, RedirectAttributes redirectAttributes, MemberVO memberVO){
		
		String member_id = memberVO.getMember_id();
		if(member_id == null || member_id.equals("")){
			redirectAttributes.addFlashAttribute("alertMessage", messageSource.getMessage("path.access.required", null, locale));
			return "redirect:/member/login";
		}
		
		return "/app/member/change";
	}
	
	@RequestMapping(value = "/member/change", method = RequestMethod.POST)
	public String change(HttpServletRequest request, HttpSession session, RedirectAttributes redirectAttributes, Model model, MemberVO memberVO) {
		
		String member_pw = memberVO.getMember_pw();
		String member_pw2 = memberVO.getMember_pw2();
		
		if(member_pw.equals(member_pw2)){
			memberVO.setMember_pw(encryptUtil.getEncMD5(member_pw));
			int result = searchService.updateMemberChange(memberVO);
			if(result > 0){
				redirectAttributes.addFlashAttribute("memberVO", memberVO);

				return "redirect:/member/changeComplete";
			}else{
				model.addAttribute("memberVO", memberVO);
				model.addAttribute("alertMessage", messageSource.getMessage("search.change.password.error", null, locale));
				
				return "/app/member/change";
			}
		}else{
			model.addAttribute("memberVO", memberVO);
			model.addAttribute("alertMessage", messageSource.getMessage("search.notmatch.password", null, locale));
			
			return "/app/member/change";
		}
	}
	
	// 회원 비밀번호 수정 완료
	@RequestMapping(value = "/member/changeComplete", method = RequestMethod.GET)
	public String changeComplete(HttpServletRequest request, HttpSession session, RedirectAttributes redirectAttributes, MemberVO memberVO){
		
		String member_id = memberVO.getMember_id();
		if(member_id == null || member_id.equals("")){
			redirectAttributes.addFlashAttribute("alertMessage", messageSource.getMessage("path.access.required", null, locale));
			return "redirect:/member/login";
		}

		return "/app/member/changeComplete";
	}

}
