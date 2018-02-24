package com.simpolor.app.notice.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.simpolor.app.notice.mapper.NoticeMapper;
import com.simpolor.app.notice.vo.NoticeVO;

@Service("noticeService")
public class NoticeService {
	
	@Resource(name="noticeMapper")
	private NoticeMapper noticeMapper;
	
	public int selectNoticeTotalCount(NoticeVO noticeVO) {
		int result = 0; 
		result = noticeMapper.selectNoticeTotalCount(noticeVO);
		
		return result;
	}
	
	public List<NoticeVO> selectNoticeList(NoticeVO noticeVO) {
		List<NoticeVO> list = null; 
		list = noticeMapper.selectNoticeList(noticeVO);
		
		return list;
	}
	
	public NoticeVO selectNotice(NoticeVO noticeVO) {
		NoticeVO notice = null; 
		notice = noticeMapper.selectNotice(noticeVO);
		
		return notice;
	}
	
	public int insertNotice(NoticeVO noticeVO) {
		int result = 0;
		result = noticeMapper.insertNotice(noticeVO);
		
		return result;
	}
	
	public int updateNotice(NoticeVO noticeVO) {
		int result = 0;
		result = noticeMapper.updateNotice(noticeVO);
		
		return result;
	}
	
	public int deleteNotice(NoticeVO noticeVO) {
		int result = 0;
		result = noticeMapper.deleteNotice(noticeVO);
		
		return result;
	}
	
}
