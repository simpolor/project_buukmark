package com.simpolor.app.bookmark.mapper;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.simpolor.app.bookmark.vo.BookmarkVO;

@Repository("bookmarkMapper")
public class BookmarkMapper {
	
	@Autowired
	private SqlSession sqlSession;
	
	public int selectBookmarkTotalCount(BookmarkVO bookmarkVO){
		return sqlSession.selectOne("selectBookmarkTotalCount", bookmarkVO);
	}
	
	public List<BookmarkVO> selectBookmarkList(BookmarkVO bookmarkVO){
		return sqlSession.selectList("selectBookmarkList", bookmarkVO);
	}
	
	public BookmarkVO selectBookmark(BookmarkVO bookmarkVO){
		return sqlSession.selectOne("selectBookmark", bookmarkVO);
	}
	
	public int insertBookmark(BookmarkVO bookmarkVO){
		return sqlSession.insert("insertBookmark", bookmarkVO);
	}
	
	public int deleteBookmark(BookmarkVO bookmarkVO){
		return sqlSession.update("deleteBookmark", bookmarkVO);
	}
	
	public List<String> selectBookmarkTypeList(BookmarkVO bookmarkVO){
		return sqlSession.selectList("selectBookmarkTypeList", bookmarkVO);
	}
	
	public List<String> selectBookmarkCategoryList(BookmarkVO bookmarkVO){
		return sqlSession.selectList("selectBookmarkCategoryList", bookmarkVO);
	}
	
}
