package com.simpolor.app.member.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.simpolor.app.member.mapper.SearchMapper;
import com.simpolor.app.member.service.SearchService;
import com.simpolor.app.member.vo.MemberVO;

@Service("searchService")
public class SearchService{
	
	@Resource(name="searchMapper")
	private SearchMapper searchMapper;
	
	public MemberVO selectMemberSearch(MemberVO memberVO){
		memberVO = searchMapper.selectMemberSearch(memberVO);
		
		return memberVO;
	}
	
	public int updateMemberChange(MemberVO memberVO){
		int result = 0;
		result = searchMapper.updateMemberChange(memberVO);
		
		return result;
	}

}
