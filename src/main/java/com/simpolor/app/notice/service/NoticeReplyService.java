package com.simpolor.app.notice.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.simpolor.app.notice.mapper.NoticeReplyMapper;
import com.simpolor.app.notice.vo.NoticeReplyVO;

@Service("NoticeReplyService")
public class NoticeReplyService {
	
	@Resource(name="noticeReplyMapper")
	private NoticeReplyMapper noticeReplyMapper;
	
	public int selectNoticeReplyTotalCount(NoticeReplyVO noticeReplyVO) {
		int result = 0; 
		result = noticeReplyMapper.selectNoticeReplyTotalCount(noticeReplyVO);
		
		return result;
	}
	
	public List<NoticeReplyVO> selectNoticeReplyList(NoticeReplyVO noticeReplyVO) {
		List<NoticeReplyVO> list = null; 
		list = noticeReplyMapper.selectNoticeReplyList(noticeReplyVO);
		
		return list;
	}
	
	public int insertNoticeReply(NoticeReplyVO noticeReplyVO) {
		int result = 0;
		result = noticeReplyMapper.insertNoticeReply(noticeReplyVO);
		
		return result;
	}
	
	public int updateNoticeReply(NoticeReplyVO noticeReplyVO) {
		int result = 0;
		result = noticeReplyMapper.updateNoticeReply(noticeReplyVO);
		
		return result;
	}
	
	public int deleteNoticeReply(NoticeReplyVO noticeReplyVO) {
		int result = 0;
		result = noticeReplyMapper.deleteNoticeReply(noticeReplyVO);
		
		return result;
	}
	
}
