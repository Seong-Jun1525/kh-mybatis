package com.kh.mybatis.board.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.kh.mybatis.board.model.dao.BoardDAO;
import com.kh.mybatis.board.model.vo.Board;
import com.kh.mybatis.template.Template;

public class BoardServiceImpl implements BoardService {

	public BoardServiceImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<Board> selectBoardList() {
		/**
		 * SqlSession 객체 생성 
		 * 
		 * DAO에서 처리된 결과 저장
		 * 
		 * SqlSession 객체 반납
		 * 
		 * 결과 반환
		 */
		
		SqlSession sqlSession = Template.getSqlSession();
		
		List<Board> bList = new BoardDAO().selectBoardList(sqlSession);
		
		for(Board b : bList) {
			System.out.println(b);
		}
		
		sqlSession.close();
		
		return bList;
	}

	@Override
	public Board selectBoardByNo(int boardNo) {
		SqlSession sqlSession = Template.getSqlSession();
		
		Board b = new BoardDAO().selectBoardByNo(sqlSession, boardNo);
		
		sqlSession.close();
		
		return b;
	}

	@Override
	public int insertBoard(Board board) {
		SqlSession sqlSession = Template.getSqlSession();
		
		int result = new BoardDAO().insertBoard(sqlSession, board);

		if(result > 0) sqlSession.commit();
		
		sqlSession.close();
		
		return result;
	}

	@Override
	public int deleteBoard(int boardNo) {
		SqlSession sqlSession = Template.getSqlSession();
		
		int result = new BoardDAO().deleteBoard(sqlSession, boardNo);
		
		if(result > 0) sqlSession.commit();
		
		sqlSession.close();
		
		return result;
	}

}
