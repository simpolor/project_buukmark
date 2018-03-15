package com.simpolor.app.bookmark.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.simpolor.app.bookmark.mapper.BookmarkReportMapper;
import com.simpolor.app.bookmark.vo.BookmarkVO;

@Service("bookmarkReportService")
public class BookmarkReportService {
	
	@Resource(name="bookmarkReportMapper")
	private BookmarkReportMapper bookmarkReportMapper;
	
	public int selectBookmarkReportTotalCount(BookmarkVO bookmarkVO) {
		int result = 0; 
		result = bookmarkReportMapper.selectBookmarkReportTotalCount(bookmarkVO);
		
		return result;
	}
	
	public List<BookmarkVO> selectBookmarkReportList(BookmarkVO bookmarkVO) {
		List<BookmarkVO> list = null; 
		list = bookmarkReportMapper.selectBookmarkReportList(bookmarkVO);
		
		return list;
	}
	
	public int insertBookmarkReport(BookmarkVO bookmarkVO) {
		int result = 0;
		result = bookmarkReportMapper.insertBookmarkReport(bookmarkVO);
		
		return result;
	}

}
