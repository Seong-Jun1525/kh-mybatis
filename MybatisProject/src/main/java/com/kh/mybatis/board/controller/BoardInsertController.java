package com.kh.mybatis.board.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.mybatis.board.model.vo.Board;
import com.kh.mybatis.board.service.BoardServiceImpl;
import com.kh.mybatis.member.service.MemberServiceImpl;

/**
 * Servlet implementation class BoardInsertController
 */
@WebServlet("/boardWrite.bo")
public class BoardInsertController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardInsertController() {
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
		String userId = (String) request.getParameter("boardWriter");
		String boardTitle = (String) request.getParameter("boardTitle");
		String boardContent = (String) request.getParameter("boardContent");
		
		System.out.println(userId + " " + boardTitle + " " + boardContent);
		
		int boardWriter = new MemberServiceImpl().selectMemberNo(userId);
		System.out.println(boardWriter);
		
		// 서비스에 데이터 처리 요청 후 결과 받고 결과 처리
		int result = new BoardServiceImpl().insertBoard(new Board(boardWriter, boardTitle, boardContent));
		
		if(result > 0) {
			request.setAttribute("alertMsg", "게시글 등록에 성공했습니다");
			request.getRequestDispatcher("boardList.bo").forward(request, response);
		} else {
			request.setAttribute("errorMsg", "게시글 등록에 실패했습니다");
			request.getRequestDispatcher("WEB-INF/views/common/errorPage.jsp").forward(request, response);
		}
		
	}

}
