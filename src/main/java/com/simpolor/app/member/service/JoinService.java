package com.simpolor.app.member.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.simpolor.app.member.mapper.JoinMapper;
import com.simpolor.app.member.service.JoinService;
import com.simpolor.app.member.vo.MemberVO;

@Service("joinService")
public class JoinService{
	
	@Resource(name="joinMapper")
	private JoinMapper joinMapper;
	
	public int selectMemberIdDupCheck(String member_id) {
		int result = 0;
		result = joinMapper.selectMemberIdDupCheck(member_id);
		
		return result;
	}
	
	public int selectMemberEmailDupCheck(String email) {
		int result = 0;
		result = joinMapper.selectMemberEmailDupCheck(email);
		
		return result;
	}
	
	public int insertJoinMember(MemberVO memberVO) {
		int result = 0;
		result = joinMapper.insertJoinMember(memberVO);
		
		return result;
	}

}
