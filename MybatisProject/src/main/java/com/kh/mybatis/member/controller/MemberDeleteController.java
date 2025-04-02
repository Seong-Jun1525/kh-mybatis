package com.kh.mybatis.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.mybatis.member.service.MemberServiceImpl;

/**
 * Servlet implementation class DeleteMemberController
 */
@WebServlet("/delete.me")
public class MemberDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberDeleteController() {
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
		
		System.out.println(userId);
		System.out.println(userPwd);
		
		int result = new MemberServiceImpl().deleteMember(userId, userPwd);
		
		if(result > 0) {
			request.getSession().invalidate(); // session 객체 자체를 지움
			request.getSession().setAttribute("alertMsg", "회원탈퇴가 되었습니다");
			// request.getSession().removeAttribute("loginuser");
			response.sendRedirect(request.getContextPath());
		} else {
			request.setAttribute("errorMsg", "회원정보 수정을 실패했습니다.");
			request.getRequestDispatcher("WEB-INF/views/common/errorPage.jsp").forward(request, response);
		}
		
	}

}
