package com.simpolor.app.notice.mapper;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.simpolor.app.notice.vo.NoticeVO;


@Repository("noticeMapper")
public class NoticeMapper {
	
	@Autowired
	private SqlSession sqlSession;
	
	public int selectNoticeTotalCount(NoticeVO noticeVO){
		return sqlSession.selectOne("selectNoticeTotalCount", noticeVO);
	}
	
	public List<NoticeVO> selectNoticeList(NoticeVO noticeVO){
		return sqlSession.selectList("selectNoticeList", noticeVO);
	}
	
	public NoticeVO selectNotice(NoticeVO noticeVO){
		return sqlSession.selectOne("selectNotice", noticeVO);
	}
	
	public int insertNotice(NoticeVO noticeVO){
		return sqlSession.insert("insertNotice", noticeVO);
	}
	
	public int updateNotice(NoticeVO noticeVO){
		return sqlSession.update("updateNotice", noticeVO);
	}
	
	public int deleteNotice(NoticeVO noticeVO){
		return sqlSession.update("deleteNotice", noticeVO);
	}
	
}
