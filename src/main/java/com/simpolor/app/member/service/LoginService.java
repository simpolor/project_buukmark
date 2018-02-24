package com.simpolor.app.member.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.simpolor.app.member.mapper.LoginMapper;
import com.simpolor.app.member.service.LoginService;
import com.simpolor.app.member.vo.MemberVO;

@Service("loginService")
public class LoginService{
	
	@Resource(name="loginMapper")
	private LoginMapper loginMapper;

	public MemberVO selectMemberLogin(MemberVO memberVO) {
		memberVO = loginMapper.selectMemberLogin(memberVO);
		
		return memberVO;
	}
	
	public MemberVO selectMemberAdminLogin(MemberVO memberVO) {
		memberVO = loginMapper.selectMemberAdminLogin(memberVO);
		
		return memberVO;
	}

}
