package com.simpolor.app.board.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.simpolor.app.board.mapper.BoardMapper;
import com.simpolor.app.board.vo.BoardVO;

@Service("boardService")
public class BoardService {
	
	@Resource(name="boardMapper")
	private BoardMapper boardMapper;
	
	public int selectBoardTotalCount(BoardVO boardVO) {
		int result = 0; 
		result = boardMapper.selectBoardTotalCount(boardVO);
		
		return result;
	}
	
	public List<BoardVO> selectBoardList(BoardVO boardVO) {
		List<BoardVO> list = null; 
		list = boardMapper.selectBoardList(boardVO);
		
		return list;
	}
	
	public BoardVO selectBoard(BoardVO boardVO) {
		BoardVO board = null; 
		board = boardMapper.selectBoard(boardVO);
		
		return board;
	}
	
	public int insertBoard(BoardVO boardVO) {
		int result = 0;
		result = boardMapper.insertBoard(boardVO);
		
		return result;
	}
	
	public int updateBoard(BoardVO boardVO) {
		int result = 0;
		result = boardMapper.updateBoard(boardVO);
		
		return result;
	}
	
	public int deleteBoard(BoardVO boardVO) {
		int result = 0;
		result = boardMapper.deleteBoard(boardVO);
		
		return result;
	}
	
}
