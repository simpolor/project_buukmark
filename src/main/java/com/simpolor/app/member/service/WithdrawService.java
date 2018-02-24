package com.simpolor.app.member.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.simpolor.app.member.mapper.WithdrawMapper;
import com.simpolor.app.member.service.WithdrawService;
import com.simpolor.app.member.vo.MemberVO;

@Service("withdrawService")
public class WithdrawService{
	
	@Resource(name="withdrawMapper")
	private WithdrawMapper withdrawMapper;
	
	public int deleteMember(MemberVO memberVO){
		int result = 0;
		result = withdrawMapper.deleteMember(memberVO);
		
		return result;
	}

}
