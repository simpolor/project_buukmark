package com.simpolor.app.member.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.simpolor.app.member.mapper.MemberMapper;
import com.simpolor.app.member.service.MemberService;
import com.simpolor.app.member.vo.MemberVO;

@Service("memberService")
public class MemberService{
	
	@Resource(name="memberMapper")
	private MemberMapper memberMapper;
	
	public MemberVO selectMember(String member_id) {
		MemberVO memberVO = null; 
		memberVO = memberMapper.selectMember(member_id);
		
		return memberVO;
	}
	
	public int updateMember(MemberVO memberVO) {
		int result = 0;
		result = memberMapper.updateMember(memberVO);
		
		return result;
	}
	
	public int updateMemberPassword(MemberVO memberVO) {
		int result = 0;
		result = memberMapper.updateMemberPassword(memberVO);
		
		return result;
	}
	
	public int selectMemberConfirm(MemberVO memberVO) {
		int result = 0;
		result = memberMapper.selectMemberConfirm(memberVO);
		
		return result;
	}
	
	public int selectMemberTotalCount(MemberVO memberVO) {
		int result = 0; 
		result = memberMapper.selectMemberTotalCount(memberVO);
		
		return result;
	}
	
	public List<MemberVO> selectMemberList(MemberVO memberVO) {
		List<MemberVO> list = null; 
		list = memberMapper.selectMemberList(memberVO);
		
		return list;
	}

}
