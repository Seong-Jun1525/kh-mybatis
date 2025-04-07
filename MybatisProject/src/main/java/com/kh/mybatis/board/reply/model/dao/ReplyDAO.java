package com.kh.mybatis.board.reply.model.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.kh.mybatis.board.reply.model.vo.Reply;

public class ReplyDAO {

	public ReplyDAO() {}

	public List<Reply> selectReplyList(SqlSession sqlSession, int boardNo) {
		return sqlSession.selectList("replyMapper.selectReplyList", boardNo);
	}

	public int insertReply(SqlSession sqlSession, Reply r) {
		return sqlSession.insert("replyMapper.insertReply", r);
	}

	public int deleteReplyByBoardNo(SqlSession sqlSession, int boardNo) {
		return sqlSession.delete("replyMapper.deleteReployByBoardNo", boardNo);
	}
	

}
