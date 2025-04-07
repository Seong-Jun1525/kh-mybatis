package com.kh.mybatis.board.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.mybatis.board.model.vo.Board;
import com.kh.mybatis.board.reply.model.vo.Reply;
import com.kh.mybatis.board.reply.service.ReplyServiceImpl;
import com.kh.mybatis.board.service.BoardServiceImpl;
import com.kh.mybatis.member.service.MemberServiceImpl;

/**
 * Servlet implementation class BoardDetailController
 */
@WebServlet("/boardDetail.bo/*")
public class BoardDetailController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardDetailController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		System.out.println(request.getPathInfo().substring(1));
//		int boardNo = Integer.parseInt(request.getPathInfo().substring(1));
//		System.out.println(boardNo);
//		
//		Board b = new BoardServiceImpl().selectBoardByNo(boardNo);
//		
//		System.out.println(b);
//		
//		request.setAttribute("board", b);
//		
//		request.getRequestDispatcher("WEB-INF/views/board/boardDetail.jsp").forward(request, response);
		// Path 정보 출력 (디버깅)
		// 디버깅용 출력
		// 디버깅용 출력
	    System.out.println("Path Info: " + request.getPathInfo());
	    System.out.println("Request URI: " + request.getRequestURI());
	    System.out.println("Servlet Path: " + request.getServletPath());

	    // 게시글 번호 추출
	    String pathInfo = request.getPathInfo();
	    String path = "";

	    if (pathInfo == null || pathInfo.equals("/")) {
	        // `request.getPathInfo()`가 null이면 `request.getRequestURI()`에서 가져옴
	        String uri = request.getRequestURI();
	        path = uri.substring(uri.lastIndexOf("/") + 1);
	    } else {
	        // `/1` 형태로 오므로 `/` 제거
	        path = pathInfo.substring(1);
	    }

	    // 숫자인지 확인 후 변환
	    int boardNo;
	    if (path.matches("\\d+")) {  
	        try {
	            boardNo = Integer.parseInt(path);
	        } catch (NumberFormatException e) {
	            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "유효하지 않은 게시글 번호입니다.");
	            return;
	        }
	    } else {
	        response.sendError(HttpServletResponse.SC_BAD_REQUEST, "게시글 번호는 숫자여야 합니다.");
	        return;
	    }

	    System.out.println("게시글 번호: " + boardNo);

	    // 서비스 호출
	    Board b = new BoardServiceImpl().selectBoardByNo(boardNo);

	    if (b == null) {
	        System.out.println("게시글이 존재하지 않습니다: " + boardNo);
	        response.sendError(HttpServletResponse.SC_NOT_FOUND, "게시글을 찾을 수 없습니다.");
	        return;
	    }

	    System.out.println("게시글 정보: " + b);
	    
	    // 댓글 정보
	    List<Reply> rList = new ReplyServiceImpl().selectReplyList(b.getBoardNo());
		
//		for(Reply r : rList) System.out.println(r);

	    // 요청 속성에 저장 후 포워딩
	    request.setAttribute("board", b);
	    request.setAttribute("rList", rList);
	    request.getRequestDispatcher("/WEB-INF/views/board/boardDetail.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
