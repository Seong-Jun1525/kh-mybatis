package com.kh.mybatis.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.mybatis.member.model.vo.Member;
import com.kh.mybatis.member.service.MemberServiceImpl;

/**
 * Servlet implementation class UpdateMemberController
 */
@WebServlet("/update.me")
public class MemberUpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberUpdateController() {
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
		// System.out.println(request.getSession().getAttribute("loginUser"));
		String userId = (String) request.getParameter("userId");
		String email = (String) request.getParameter("userEmail");
		String birthDay = (String) request.getParameter("birthDay");
		String gender = (String) request.getParameter("gender");
		String phone = (String) request.getParameter("phone");
		String address = (String) request.getParameter("address");
		
		System.out.println(userId);
		System.out.println(email);
		System.out.println(gender);
		System.out.println(phone);
		System.out.println(address);
		System.out.println(birthDay);
		int result = new MemberServiceImpl().updateMember(new Member(userId, email, gender, phone, address, birthDay));
		
		if(result > 0) {
			Member m = (Member)(request.getSession().getAttribute("loginUser"));
			Member loginUser = new MemberServiceImpl().selectMember(m);
			// System.out.println(loginUser);
			request.getSession().setAttribute("loginUser", loginUser);
			request.getSession().setAttribute("alertMsg", "회원정보 수정을 성공했습니다.");
			
			response.sendRedirect(request.getContextPath());
		} else {
			request.setAttribute("errorMsg", "회원정보 수정을 실패했습니다.");
			request.getRequestDispatcher("WEB-INF/views/common/errorPage.jsp").forward(request, response);
		}
	}

}
