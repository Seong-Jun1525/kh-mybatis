package com.kh.mybatis.board.service;

import java.util.List;

import com.kh.mybatis.board.model.vo.Board;

public interface BoardService {
	List<Board> selectBoardList();
	Board selectBoardByNo(int boardNo);
	int insertBoard(Board board);
	int deleteBoard(int boardNo);
}
