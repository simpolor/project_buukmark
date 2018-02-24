package com.simpolor.app.board.mapper;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.simpolor.app.board.vo.BoardReplyVO;

@Repository("boardReplyMapper")
public class BoardReplyMapper {
	
	@Autowired
	private SqlSession sqlSession;
	
	public int selectBoardReplyTotalCount(BoardReplyVO boardReplyVO){
		return sqlSession.selectOne("selectBoardReplyTotalCount", boardReplyVO);
	}
	
	public List<BoardReplyVO> selectBoardReplyList(BoardReplyVO boardReplyVO){
		return sqlSession.selectList("selectBoardReplyList", boardReplyVO);
	}
	
	public int insertBoardReply(BoardReplyVO boardReplyVO){
		return sqlSession.insert("insertBoardReply", boardReplyVO);
	}
	
	public int updateBoardReply(BoardReplyVO boardReplyVO){
		return sqlSession.update("updateBoardReply", boardReplyVO);
	}
	
	public int deleteBoardReply(BoardReplyVO boardReplyVO){
		return sqlSession.update("deleteBoardReply", boardReplyVO);
	}
	
}
