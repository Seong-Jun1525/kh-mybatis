package com.kh.mybatis.board.reply.service;

import java.util.List;

import com.kh.mybatis.board.reply.model.vo.Reply;

public interface ReplyService {

	List<Reply> selectReplyList(int boardNo);
	int insertReply(Reply r);
	int deleteReplyByBoardNo(int boardNo);
}
