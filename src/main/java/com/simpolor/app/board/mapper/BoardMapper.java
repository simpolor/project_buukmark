package com.simpolor.app.board.mapper;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.simpolor.app.board.vo.BoardVO;


@Repository("boardMapper")
public class BoardMapper {
	
	@Autowired
	private SqlSession sqlSession;
	
	public int selectBoardTotalCount(BoardVO boardVO){
		return sqlSession.selectOne("selectBoardTotalCount", boardVO);
	}
	
	public List<BoardVO> selectBoardList(BoardVO boardVO){
		return sqlSession.selectList("selectBoardList", boardVO);
	}
	
	public BoardVO selectBoard(BoardVO boardVO){
		return sqlSession.selectOne("selectBoard", boardVO);
	}
	
	public int insertBoard(BoardVO boardVO){
		return sqlSession.insert("insertBoard", boardVO);
	}
	
	public int updateBoard(BoardVO boardVO){
		return sqlSession.update("updateBoard", boardVO);
	}
	
	public int deleteBoard(BoardVO boardVO){
		return sqlSession.update("deleteBoard", boardVO);
	}
	
}
