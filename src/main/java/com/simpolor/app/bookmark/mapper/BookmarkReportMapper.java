package com.simpolor.app.bookmark.mapper;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.simpolor.app.bookmark.vo.BookmarkVO;

@Repository("bookmarkReportMapper")
public class BookmarkReportMapper {
	
	@Autowired
	private SqlSession sqlSession;
	
	public int selectBookmarkReportTotalCount(BookmarkVO bookmarkVO){
		return sqlSession.selectOne("selectBookmarkReportTotalCount", bookmarkVO);
	}
	
	public List<BookmarkVO> selectBookmarkReportList(BookmarkVO bookmarkVO){
		return sqlSession.selectList("selectBookmarkReportList", bookmarkVO);
	}
	
	public int insertBookmarkReport(BookmarkVO bookmarkVO){
		return sqlSession.insert("insertBookmarkReport", bookmarkVO);
	}
	
	
}
