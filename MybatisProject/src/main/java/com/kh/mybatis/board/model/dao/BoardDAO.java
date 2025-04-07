package com.kh.mybatis.board.model.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.kh.mybatis.board.model.vo.Board;

public class BoardDAO {

	public BoardDAO() {}

	public List<Board> selectBoardList(SqlSession sqlSession) {
		return sqlSession.selectList("boardMapper.selectBoardList");
	}

	public Board selectBoardByNo(SqlSession sqlSession, int boardNo) {
		return sqlSession.selectOne("boardMapper.selectBoardByNo", boardNo);
	}

	public int insertBoard(SqlSession sqlSession, Board board) {
		return sqlSession.insert("boardMapper.insertBoard", board);
	}

	public int deleteBoard(SqlSession sqlSession, int boardNo) {
		return sqlSession.delete("boardMapper.deleteBoard", boardNo);
	}

}