package com.simpolor.app.member.mapper;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.simpolor.app.member.vo.MemberVO;

@Repository("searchMapper")
public class SearchMapper {
	
	@Autowired
	private SqlSession sqlSession;
	
	public MemberVO selectMemberSearch(MemberVO memberVO){
		return sqlSession.selectOne("selectMemberSearch", memberVO);
	}
	
	public int updateMemberChange( MemberVO memberVO ){
		return sqlSession.update("updateMemberChange", memberVO);
	}

}
