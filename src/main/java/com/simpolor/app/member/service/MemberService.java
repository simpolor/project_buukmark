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
	
	public int insertMember(MemberVO memberVO) {
		int result = 0;
		result = memberMapper.insertMember(memberVO);
		
		return result;
	}
	
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
	
	public int deleteMember(MemberVO memberVO){
		int result = 0;
		result = memberMapper.deleteMember(memberVO);
		
		return result;
	}
	
	public int updateMemberWithdraw(MemberVO memberVO){
		int result = 0;
		result = memberMapper.updateMemberWithdraw(memberVO);
		
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

	public int selectMemberIdDupCheck(MemberVO memberVO) {
		int result = 0;
		result = memberMapper.selectMemberIdDupCheck(memberVO);
		
		return result;
	}
	
	public int selectMemberEmailDupCheck(MemberVO memberVO) {
		int result = 0;
		result = memberMapper.selectMemberEmailDupCheck(memberVO);
		
		return result;
	}
	
	public int selectMemberNicknameDupCheck(MemberVO memberVO) {
		int result = 0;
		result = memberMapper.selectMemberNicknameDupCheck(memberVO);
		
		return result;
	}

}
