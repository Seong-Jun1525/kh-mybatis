package com.kh.mybatis.board.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.mybatis.board.reply.service.ReplyServiceImpl;
import com.kh.mybatis.board.service.BoardServiceImpl;

/**
 * Servlet implementation class BoardDeleteController
 */
@WebServlet("/boardDelete.bo/*")
public class BoardDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardDeleteController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uri = request.getRequestURI();
        int boardNo = Integer.parseInt(uri.substring(uri.lastIndexOf("/") + 1));
		
		System.out.println(boardNo);
		
		int resultReply = new ReplyServiceImpl().deleteReplyByBoardNo(boardNo);
		System.out.println("resultReply : " + resultReply);
		int result = new BoardServiceImpl().deleteBoard(boardNo);
		System.out.println("result : " + result);
		
		if(result > 0) {
			request.setAttribute("alertMsg", "게시글 삭제 성공");
			response.sendRedirect(request.getContextPath() + "/boardList.bo");
//			request.getRequestDispatcher(request.getContextPath());
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
