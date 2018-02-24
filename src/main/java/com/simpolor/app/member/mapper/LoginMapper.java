package com.simpolor.app.member.mapper;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.simpolor.app.member.vo.MemberVO;

@Repository("loginMapper")
public class LoginMapper {
	
	@Autowired
	private SqlSession sqlSession;
	
	public MemberVO selectMemberLogin(MemberVO memberVO){
		return sqlSession.selectOne("selectMemberLogin", memberVO);
	}
	
	public MemberVO selectMemberAdminLogin(MemberVO memberVO){
		return sqlSession.selectOne("selectMemberAdminLogin", memberVO);
	}

}
