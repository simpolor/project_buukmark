package com.simpolor.app.member.mapper;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.simpolor.app.member.vo.MemberVO;

@Repository("memberMapper")
public class MemberMapper {
	
	@Autowired
	private SqlSession sqlSession;
	
	public int selectMemberIdDupCheck(MemberVO memberVO){
		return sqlSession.selectOne("selectMemberIdDupCheck", memberVO);
	}
	
	public int selectMemberEmailDupCheck(MemberVO memberVO){
		return sqlSession.selectOne("selectMemberEmailDupCheck", memberVO);
	}
	
	public int selectMemberNicknameDupCheck(MemberVO memberVO){
		return sqlSession.selectOne("selectMemberNicknameDupCheck", memberVO);
	}
	
	public int insertMember(MemberVO memberVO){
		return sqlSession.insert("insertMember", memberVO);
	}
	
	public MemberVO selectMember(String member_id){
		return sqlSession.selectOne("selectMember", member_id);
	}
	
	public int updateMember(MemberVO memberVO){
		return sqlSession.update("updateMember", memberVO);
	}
	
	public int updateMemberPassword(MemberVO memberVO){
		return sqlSession.update("updateMemberPassword", memberVO);
	}
	
	public int deleteMember(MemberVO memberVO){
		return sqlSession.delete("deleteMember", memberVO);
	}
	
	public int selectMemberConfirm(MemberVO memberVO){
		return sqlSession.selectOne("selectMemberConfirm", memberVO);
	}
	
	public int selectMemberTotalCount(MemberVO memberVO){
		return sqlSession.selectOne("selectMemberTotalCount", memberVO);
	}
	
	public List<MemberVO> selectMemberList(MemberVO memberVO){
		return sqlSession.selectList("selectMemberList", memberVO);
	}
	
}
