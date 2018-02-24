package com.simpolor.app.bookmark.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.simpolor.app.bookmark.mapper.BookmarkMyMapper;
import com.simpolor.app.bookmark.vo.BookmarkVO;

@Service("bookmarkMyService")
public class BookmarkMyService {
	
	@Resource(name="bookmarkMyMapper")
	private BookmarkMyMapper bookmarkMyMapper;
	
	public int selectBookmarkMyTotalCount(BookmarkVO bookmarkVO) {
		int result = 0; 
		result = bookmarkMyMapper.selectBookmarkMyTotalCount(bookmarkVO);
		
		return result;
	}
	
	public List<BookmarkVO> selectBookmarkMyList(BookmarkVO bookmarkVO) {
		List<BookmarkVO> list = null; 
		list = bookmarkMyMapper.selectBookmarkMyList(bookmarkVO);
		
		return list;
	}
	
	public int insertBookmarkMy(BookmarkVO bookmarkVO) {
		int result = 0;
		result = bookmarkMyMapper.insertBookmarkMy(bookmarkVO);
		
		return result;
	}
	
	public int deleteBookmarkMy(BookmarkVO bookmarkVO) {
		int result = 0;
		result = bookmarkMyMapper.deleteBookmarkMy(bookmarkVO);
		
		return result;
	}

}
