package com.simpolor.app.board.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.simpolor.app.board.mapper.BoardReplyMapper;
import com.simpolor.app.board.vo.BoardReplyVO;

@Service("BoardReplyService")
public class BoardReplyService {
	
	@Resource(name="boardReplyMapper")
	private BoardReplyMapper boardReplyMapper;
	
	public int selectBoardReplyTotalCount(BoardReplyVO boardReplyVO) {
		int result = 0; 
		result = boardReplyMapper.selectBoardReplyTotalCount(boardReplyVO);
		
		return result;
	}
	
	public List<BoardReplyVO> selectBoardReplyList(BoardReplyVO boardReplyVO) {
		List<BoardReplyVO> list = null; 
		list = boardReplyMapper.selectBoardReplyList(boardReplyVO);
		
		return list;
	}
	
	public int insertBoardReply(BoardReplyVO boardReplyVO) {
		int result = 0;
		result = boardReplyMapper.insertBoardReply(boardReplyVO);
		
		return result;
	}
	
	public int updateBoardReply(BoardReplyVO boardReplyVO) {
		int result = 0;
		result = boardReplyMapper.updateBoardReply(boardReplyVO);
		
		return result;
	}
	
	public int deleteBoardReply(BoardReplyVO boardReplyVO) {
		int result = 0;
		result = boardReplyMapper.deleteBoardReply(boardReplyVO);
		
		return result;
	}
	
}
