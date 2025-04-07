package com.kh.mybatis.board.reply.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.kh.mybatis.board.reply.model.vo.Reply;
import com.kh.mybatis.board.reply.service.ReplyServiceImpl;
import com.kh.mybatis.member.service.MemberServiceImpl;

/**
 * Servlet implementation class ReplyInsert
 */
@WebServlet("/replyInsert.re")
public class ReplyInsert extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReplyInsert() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String replyContent = request.getParameter("replyContent");
		String replyWriterId = request.getParameter("replyWriter");
		int boardNo = Integer.parseInt(request.getParameter("boardNo"));
		
		System.out.println(replyContent + " " + replyWriterId + " " + boardNo);
		
		int replyWriter = new MemberServiceImpl().selectMemberNo(replyWriterId);
		
		int result = new ReplyServiceImpl().insertReply(new Reply(replyContent, boardNo, replyWriter));
		
		response.setContentType("application/json; charset=utf-8");
		
		Gson gson = new Gson();

		// 댓글 정보
	    List<Reply> rList = new ReplyServiceImpl().selectReplyList(boardNo);

		gson.toJson(rList, response.getWriter());
	}

}
