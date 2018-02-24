package com.simpolor.app.member.mapper;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.simpolor.app.member.vo.MemberVO;

@Repository("withdrawMapper")
public class WithdrawMapper {
	
	@Autowired
	private SqlSession sqlSession;
	
	public int deleteMember(MemberVO memberVO){
		return sqlSession.delete("deleteMember", memberVO);
	}

}
