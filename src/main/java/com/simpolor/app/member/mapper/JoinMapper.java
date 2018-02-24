package com.simpolor.app.member.mapper;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.simpolor.app.member.vo.MemberVO;

@Repository("joinMapper")
public class JoinMapper {
	
	@Autowired
	private SqlSession sqlSession;
	
	public int selectMemberIdDupCheck(String member_id){
		return sqlSession.selectOne("selectMemberIdDupCheck", member_id);
	}
	
	public int selectMemberEmailDupCheck(String email){
		return sqlSession.selectOne("selectMemberEmailDupCheck", email);
	}
	
	public int insertJoinMember(MemberVO memberVO){
		return sqlSession.insert("insertJoinMember", memberVO);
	}

}
