package com.simpolor.app.member.controller;

import java.util.List;
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

import com.simpolor.app.board.vo.BoardVO;
import com.simpolor.app.common.util.EncryptUtil;
import com.simpolor.app.common.util.PageNavigation;
import com.simpolor.app.common.util.StringUtil;
import com.simpolor.app.member.service.MemberService;
import com.simpolor.app.member.vo.MemberVO;

@Controller
public class AdminMemberController {
	
	@Autowired
	private MemberService memberService;
	
	@Autowired
    EncryptUtil encryptUtil;
	
	@Autowired
    MessageSource messageSource;
	
	Locale locale;
	
	@RequestMapping(value = "/admin/member/list", method = RequestMethod.GET)
	public String memberList(HttpServletRequest request, HttpSession session, RedirectAttributes redirectAttributes, Model model, MemberVO memberVO){
		
		String member_id = StringUtil.getString(session.getAttribute("SESSION_MEMBER_ID"));
		int member_level = StringUtil.getInt(session.getAttribute("SESSION_MEMBER_LEVEL"));
		if(StringUtil.isEmpty(member_id) || member_level < 90){
			redirectAttributes.addFlashAttribute("alertMessage", messageSource.getMessage("login.required", null, locale));
			redirectAttributes.addFlashAttribute("returnUrl", request.getRequestURL());
			return "redirect:/admin/member/login";
		}
		
		// page 파라미터 값 가져오기
		int page = memberVO.getPage();
				
		// 전체 갯수 가져오기 
		int totalCount = memberService.selectMemberTotalCount(memberVO);
		
		 // 페이징 정보 추가
		PageNavigation paging = new PageNavigation();
		paging.setTotalCount(totalCount);
		paging.setPageNo(page);
		paging.makePageNavigation();
		
		// bookmarkVO에 offset, limit 세팅
		memberVO.setOffset(paging.getOffset());
		memberVO.setLimit(paging.getLimit());
		
		List<MemberVO> list = memberService.selectMemberList(memberVO);

		model.addAttribute("memberList", list);
		model.addAttribute("paging", paging);
		
		return "app/member/adminList";
	}
	
	@RequestMapping(value = "/admin/member/view", method = RequestMethod.GET)
	public String memberView(HttpServletRequest request, RedirectAttributes redirectAttributes, Model model, HttpSession session) {
		
		String member_id = StringUtil.getString(session.getAttribute("SESSION_MEMBER_ID"));
		int member_level = StringUtil.getInt(session.getAttribute("SESSION_MEMBER_LEVEL"));
		if(StringUtil.isEmpty(member_id) || member_level < 90){
			redirectAttributes.addFlashAttribute("alertMessage", messageSource.getMessage("login.required", null, locale));
			redirectAttributes.addFlashAttribute("returnUrl", request.getRequestURL());
			return "redirect:/admin/member/login";
		}
		
		MemberVO memberVO = memberService.selectMember(member_id);
		
		model.addAttribute("memberVO", memberVO);

		return "/app/member/adminView";
	}
	
	// 회원정보 수정 폼
	@RequestMapping(value = "/admin/member/edit", method = RequestMethod.GET)
	public String edit(HttpServletRequest request, RedirectAttributes redirectAttributes, HttpSession session, Model model) {
		
		String member_id = StringUtil.getString(session.getAttribute("SESSION_MEMBER_ID"));
		int member_level = StringUtil.getInt(session.getAttribute("SESSION_MEMBER_LEVEL"));
		if(StringUtil.isEmpty(member_id) || member_level < 90){
			redirectAttributes.addFlashAttribute("alertMessage", messageSource.getMessage("login.required", null, locale));
			redirectAttributes.addFlashAttribute("returnUrl", request.getRequestURL());
			return "redirect:/admin/member/login";
		}
		
		MemberVO memberVO = memberService.selectMember(member_id);
		model.addAttribute("memberVO", memberVO);

		return "/app/member/adminEdit";
	}
	
	// 회언정보 수정 처리
	@RequestMapping(value = "/admin/member/edit", method = RequestMethod.POST)
	public String edit(HttpServletRequest request, RedirectAttributes redirectAttributes, HttpSession session, Model model, MemberVO memberVO){
		
		String member_id = StringUtil.getString(session.getAttribute("SESSION_MEMBER_ID"));
		int member_level = StringUtil.getInt(session.getAttribute("SESSION_MEMBER_LEVEL"));
		if(StringUtil.isEmpty(member_id) || member_level < 90){
			redirectAttributes.addFlashAttribute("alertMessage", messageSource.getMessage("login.required", null, locale));
			redirectAttributes.addFlashAttribute("returnUrl", request.getRequestURL());
			return "redirect:/admin/member/login";
		}
		
		memberVO.setMember_id(member_id);
		memberVO.setMember_name(memberVO.getMember_name());
		memberVO.setMember_email(memberVO.getMember_email());
		
		int result = memberService.updateMember(memberVO);
		if(result > 0){
			redirectAttributes.addFlashAttribute("alertMessage", messageSource.getMessage("member.change.profile.complete", null, locale));
			return "redirect:/admin/member/view";
		}else{
			model.addAttribute("alertMessage", redirectAttributes.addFlashAttribute("alertMessage", messageSource.getMessage("member.change.profile.error", null, locale)));
			return "/app/member/adminEdit";
		}
	}
	
	// 회원 비밀번호 수정 폼
	@RequestMapping(value = "/admin/member/password", method = RequestMethod.GET)
	public String password(HttpServletRequest request, RedirectAttributes redirectAttributes, HttpSession session, Model model){
		
		String member_id = StringUtil.getString(session.getAttribute("SESSION_MEMBER_ID"));
		int member_level = StringUtil.getInt(session.getAttribute("SESSION_MEMBER_LEVEL"));
		if(StringUtil.isEmpty(member_id) || member_level < 90){
			redirectAttributes.addFlashAttribute("alertMessage", messageSource.getMessage("login.required", null, locale));
			redirectAttributes.addFlashAttribute("returnUrl", request.getRequestURL());
			return "redirect:/admin/member/login";
		}
		
		MemberVO memberVO = new MemberVO();
		memberVO.setMember_id(member_id);
		model.addAttribute("memberVO", memberVO);

		return "/app/member/adminPassword";
	}
	
	// 회원 비밀번호 수정 처리
	@RequestMapping(value = "/admin/member/password", method = RequestMethod.POST)
	public String password(HttpServletRequest request, HttpSession session,RedirectAttributes redirectAttributes, Model model, MemberVO memberVO) {
		
		String member_id = StringUtil.getString(session.getAttribute("SESSION_MEMBER_ID"));
		int member_level = StringUtil.getInt(session.getAttribute("SESSION_MEMBER_LEVEL"));
		if(StringUtil.isEmpty(member_id) || member_level < 90){
			redirectAttributes.addFlashAttribute("alertMessage", messageSource.getMessage("login.required", null, locale));
			redirectAttributes.addFlashAttribute("returnUrl", request.getRequestURL());
			return "redirect:/admin/member/login";
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
					redirectAttributes.addFlashAttribute("alertMessage", messageSource.getMessage("member.change.password.complete", null, locale));
					
					return "redirect:/admin/member/view";
				}else{
					memberVO.setMember_pw("");
					memberVO.setMember_pw2("");
					model.addAttribute("memberVO", memberVO);
					model.addAttribute("alertMessage", messageSource.getMessage("member.change.password.error", null, locale));
					
					return "/app/member/adminPassword";
				}
			}else{
				model.addAttribute("memberVO", memberVO);
				model.addAttribute("alertMessage", messageSource.getMessage("member.notmatch.password.old", null, locale));
				
				return "/app/member/adminPassword";
			}
		}else{
			model.addAttribute("memberVO", memberVO);
			model.addAttribute("alertMessage", messageSource.getMessage("member.notmatch.password.new", null, locale));
			
			return "/app/member/adminPassword";
		}
	}
	
}
