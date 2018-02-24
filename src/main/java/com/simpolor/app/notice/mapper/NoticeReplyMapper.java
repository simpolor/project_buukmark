package com.simpolor.app.notice.mapper;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.simpolor.app.notice.vo.NoticeReplyVO;

@Repository("noticeReplyMapper")
public class NoticeReplyMapper {
	
	@Autowired
	private SqlSession sqlSession;
	
	public int selectNoticeReplyTotalCount(NoticeReplyVO noticeReplyVO){
		return sqlSession.selectOne("selectNoticeReplyTotalCount", noticeReplyVO);
	}
	
	public List<NoticeReplyVO> selectNoticeReplyList(NoticeReplyVO noticeReplyVO){
		return sqlSession.selectList("selectNoticeReplyList", noticeReplyVO);
	}
	
	public int insertNoticeReply(NoticeReplyVO noticeReplyVO){
		return sqlSession.insert("insertNoticeReply", noticeReplyVO);
	}
	
	public int updateNoticeReply(NoticeReplyVO noticeReplyVO){
		return sqlSession.update("updateNoticeReply", noticeReplyVO);
	}
	
	public int deleteNoticeReply(NoticeReplyVO noticeReplyVO){
		return sqlSession.update("deleteNoticeReply", noticeReplyVO);
	}
	
}
