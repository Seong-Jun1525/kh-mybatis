package com.kh.mybatis.board.reply.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.kh.mybatis.board.reply.model.dao.ReplyDAO;
import com.kh.mybatis.board.reply.model.vo.Reply;
import com.kh.mybatis.template.Template;

public class ReplyServiceImpl implements ReplyService {

	public ReplyServiceImpl() {}

	@Override
	public List<Reply> selectReplyList(int boardNo) {
		
		SqlSession sqlSession = Template.getSqlSession();
		
		System.out.println(boardNo);
		
		List<Reply> rList = new ReplyDAO().selectReplyList(sqlSession, boardNo);
		
		sqlSession.close();
		
		return rList;
	}

	@Override
	public int insertReply(Reply r) {
		SqlSession sqlSession = Template.getSqlSession();
		
		System.out.println(r);

		int result = new ReplyDAO().insertReply(sqlSession, r);
		
		if(result > 0) sqlSession.commit();
		
		sqlSession.close();
		
		return result;
	}

	@Override
	public int deleteReplyByBoardNo(int boardNo) {
		SqlSession sqlSession = Template.getSqlSession();
		
		int result = new ReplyDAO().deleteReplyByBoardNo(sqlSession, boardNo);

		if(result > 0) sqlSession.commit();
		
		sqlSession.close();
		
		return result;
	}

}
