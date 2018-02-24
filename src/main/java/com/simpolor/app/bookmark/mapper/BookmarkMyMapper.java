package com.simpolor.app.bookmark.mapper;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.simpolor.app.bookmark.vo.BookmarkVO;

@Repository("bookmarkMyMapper")
public class BookmarkMyMapper {
	
	@Autowired
	private SqlSession sqlSession;
	
	public int selectBookmarkMyTotalCount(BookmarkVO bookmarkVO){
		return sqlSession.selectOne("selectBookmarkMyTotalCount", bookmarkVO);
	}
	
	public List<BookmarkVO> selectBookmarkMyList(BookmarkVO bookmarkVO){
		return sqlSession.selectList("selectBookmarkMyList", bookmarkVO);
	}
	
	public int insertBookmarkMy(BookmarkVO bookmarkVO){
		return sqlSession.insert("insertBookmarkMy", bookmarkVO);
	}
	
	public int deleteBookmarkMy(BookmarkVO bookmarkVO){
		return sqlSession.delete("deleteBookmarkMy", bookmarkVO);
	}
	
}
