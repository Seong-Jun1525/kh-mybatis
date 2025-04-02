package com.kh.mybatis.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.mybatis.member.model.vo.Member;
import com.kh.mybatis.member.service.MemberService;
import com.kh.mybatis.member.service.MemberServiceImpl;

// login.me 요청을 받아 아이디, 비밀번호 데이터 추출 -> 출력
/**
 * 1) HttpServlet 상속받기 
 * 2) WebServlet("요청받을URL") URL 매핑
 * 3) 요청 방식에 따라 메서드 오버라이딩
 */
@WebServlet("/login.me")
public class LoginController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	public LoginController() {
        super();
    }

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8"); // 데이터 추출 전에 인코딩 처리
		String userId = (String) request.getParameter("userId");
		String userPwd = (String) request.getParameter("userPwd");
		
		System.out.println("아이디 : " + userId);
		System.out.println("비밀번호 : " + userPwd);
		
		// 데이터 가공
		Member m = new Member(userId, userPwd);
		
		MemberService service = new MemberServiceImpl();
		Member loginUser = service.selectMember(m);

		HttpSession session = request.getSession();
		if(loginUser != null) {
			session.setAttribute("loginUser", loginUser);
			session.setAttribute("alertMsg", "로그인 성공했습니다.");
			response.sendRedirect(request.getContextPath()); // 리다이랙션 사용
		} else {
			request.setAttribute("errorMsg", "로그인 실패했습니다."); // 에러페이지는 굳이 세션처리할 필요없음
			request.getRequestDispatcher("/WEB-INF/views/common/errorPage.jsp").forward(request, response);
		}
		
	}
}
