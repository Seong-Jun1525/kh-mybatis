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
 * Servlet implementation class MemberInsertController
 */
@WebServlet("/insert.me")
public class MemberInsertController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberInsertController() {
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
		// 전달된 데이터 추출
		// => POST 요청인 경우 전달된 데이터에 한글이 포함되어 있을 수 있으므로 인코딩 처리 필요
		request.setCharacterEncoding("UTF-8");
		
		// 데이터 추출 메서드 : getParameter("키값")
		String userId = (String) request.getParameter("userId");
		String userPwd = (String) request.getParameter("userPwd");
		String userName = (String) request.getParameter("userName");
		String email = (String) request.getParameter("userEmail");
		String gender = (String) request.getParameter("gender");
		String birthDay = (String) request.getParameter("birthDay");
		String phone = (String) request.getParameter("phone");
		String address = (String) request.getParameter("address");
		
		System.out.println(userId);
		System.out.println(userPwd);
		System.out.println(userName);
		System.out.println(email);
		System.out.println(gender);
		System.out.println(birthDay);
		System.out.println(phone);
		System.out.println(address);
		// -> spring 사용 시 위의 내용이 간단해질 부분
		
		// Service 객체에게 전달된 데이터를 전달 -> 데이터 추가요청
		int result = new MemberServiceImpl().insertMember(new Member(userId, userPwd, userName, email, gender, birthDay, phone, address));
		
		// 결과에 따라 페이지 구분
		if(result > 0) {
			// 회원가입 성공! -> 메인 페이지 url 재요청 (이미 매핑되어 있는 url로 재요청 가능)
			// response.sendRedirect("/mybatis");
			
			// session 영역에 메시지 저장
			request.getSession().setAttribute("alertMsg", "회원가입에 성공했습니다.");
			
			response.sendRedirect(request.getContextPath()); // 문자열로 작성하는 것이 아닌 request의 contextPath를 불러와서 작성!
		} else {
			// 회원가입 실패! -> 에러페이지 포워딩
			request.setAttribute("errorMsg", "회원가입에 실패했습니다.");
			request.getRequestDispatcher("WEB-INF/views/common/errorPage.jsp").forward(request, response);
		}
		
	}

}
