package com.simpolor.app.ask.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.simpolor.app.ask.mapper.AskMapper;
import com.simpolor.app.ask.vo.AskVO;

@Service("askService")
public class AskService {
	
	@Resource(name="askMapper")
	private AskMapper askMapper;
	
	public int selectAskTotalCount(AskVO askVO) {
		int result = 0; 
		result = askMapper.selectAskTotalCount(askVO);
		
		return result;
	}
	
	public List<AskVO> selectAskList(AskVO askVO) {
		List<AskVO> list = null; 
		list = askMapper.selectAskList(askVO);
		
		return list;
	}
	
	public AskVO selectAsk(AskVO askVO) {
		AskVO notice = null; 
		notice = askMapper.selectAsk(askVO);
		
		return notice;
	}
	
	public int insertAsk(AskVO askVO) {
		int result = 0;
		result = askMapper.insertAsk(askVO);
		
		return result;
	}
	
	public int updateAsk(AskVO askVO) {
		int result = 0;
		result = askMapper.updateAsk(askVO);
		
		return result;
	}
	
	public int deleteAsk(AskVO askVO) {
		int result = 0;
		result = askMapper.deleteAsk(askVO);
		
		return result;
	}
	
	public int selectAskMyTotalCount(AskVO askVO) {
		int result = 0; 
		result = askMapper.selectAskMyTotalCount(askVO);
		
		return result;
	}
	
	public List<AskVO> selectAskMyList(AskVO askVO) {
		List<AskVO> list = null; 
		list = askMapper.selectAskMyList(askVO);
		
		return list;
	}
	
}
