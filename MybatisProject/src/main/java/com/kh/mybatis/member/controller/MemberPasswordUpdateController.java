package com.kh.mybatis.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.mybatis.member.service.MemberServiceImpl;

/**
 * Servlet implementation class MemberPasswordUpdateController
 */
@WebServlet("/updatePwd.me")
public class MemberPasswordUpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberPasswordUpdateController() {
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
		request.setCharacterEncoding("UTF-8");
		String userId = (String) request.getParameter("userId");
		String userPwd = (String) request.getParameter("userPwd");
		String changeUserPwd = (String) request.getParameter("changeUserPwd");
		
		System.out.println(userId);
		System.out.println(userPwd);
		System.out.println(changeUserPwd);
		
		// int result = new MemberServiceImpl().updateMemberPwd(new Member(userId, changeUserPwd));
		int result = new MemberServiceImpl().updatePassword(userId, userPwd, changeUserPwd);
		if(result > 0) {
			// 세션 영역을 비워주고 메시지 저장
			request.getSession().invalidate();
			request.getSession().setAttribute("alertMsg", "비밀번호가 변경되었으므로 다시 로그인 해주세요.");
			
			response.sendRedirect(request.getContextPath());
		} else {
			request.setAttribute("errorMsg", "에러가 발생했습니다.");
			request.getRequestDispatcher("WEB-INF/views/common/errorPage.jsp").forward(request, response);
		}
	}

}
