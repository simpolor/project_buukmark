package com.simpolor.app.bookmark.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.simpolor.app.bookmark.mapper.BookmarkMapper;
import com.simpolor.app.bookmark.vo.BookmarkVO;

@Service("bookmarkService")
public class BookmarkService {
	
	@Resource(name="bookmarkMapper")
	private BookmarkMapper bookmarkMapper;
	
	public int selectBookmarkTotalCount(BookmarkVO bookmarkVO) {
		int result = 0; 
		result = bookmarkMapper.selectBookmarkTotalCount(bookmarkVO);
		
		return result;
	}
	
	public List<BookmarkVO> selectBookmarkList(BookmarkVO bookmarkVO) {
		List<BookmarkVO> list = null; 
		list = bookmarkMapper.selectBookmarkList(bookmarkVO);
		
		return list;
	}
	
	public BookmarkVO selectBookmark(BookmarkVO bookmarkVO) {
		BookmarkVO bookmark = null; 
		bookmark = bookmarkMapper.selectBookmark(bookmarkVO);
		
		return bookmark;
	}
	
	public int insertBookmark(BookmarkVO bookmarkVO) {
		int result = 0;
		result = bookmarkMapper.insertBookmark(bookmarkVO);
		
		return result;
	}
	
	public int deleteBookmark(BookmarkVO bookmarkVO) {
		int result = 0;
		result = bookmarkMapper.deleteBookmark(bookmarkVO);
		
		return result;
	}
	
	public List<String> selectBookmarkTypeList(BookmarkVO bookmarkVO) {
		List<String> list = null; 
		list = bookmarkMapper.selectBookmarkTypeList(bookmarkVO);
		
		return list;
	}
	
	public List<String> selectBookmarkCategoryList(BookmarkVO bookmarkVO) {
		List<String> list = null; 
		list = bookmarkMapper.selectBookmarkCategoryList(bookmarkVO);
		
		return list;
	}

}
