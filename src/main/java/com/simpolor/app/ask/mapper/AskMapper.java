package com.simpolor.app.ask.mapper;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.simpolor.app.ask.vo.AskVO;


@Repository("askMapper")
public class AskMapper {
	
	@Autowired
	private SqlSession sqlSession;
	
	public int selectAskTotalCount(AskVO askVO){
		return sqlSession.selectOne("selectAskTotalCount", askVO);
	}
	
	public List<AskVO> selectAskList(AskVO askVO){
		return sqlSession.selectList("selectAskList", askVO);
	}
	
	public AskVO selectAsk(AskVO askVO){
		return sqlSession.selectOne("selectAsk", askVO);
	}
	
	public int insertAsk(AskVO askVO){
		return sqlSession.insert("insertAsk", askVO);
	}
	
	public int updateAsk(AskVO askVO){
		return sqlSession.update("updateAsk", askVO);
	}
	
	public int deleteAsk(AskVO askVO){
		return sqlSession.update("deleteAsk", askVO);
	}
	
	public int selectAskMyTotalCount(AskVO askVO){
		return sqlSession.selectOne("selectAskMyTotalCount", askVO);
	}
	
	public List<AskVO> selectAskMyList(AskVO askVO){
		return sqlSession.selectList("selectAskMyList", askVO);
	}
	
}
