package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

/**
 * Servlet implementation class JqAjaxTwoController
 */
@WebServlet("/jqAjax2.do")
public class JqAjaxTwoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public JqAjaxTwoController() {
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
		// request.setCharacterEncoding("UTF-8"); ajax는 인코딩 처리 해줘서 생략이 가능하다
		String name = request.getParameter("name");
		String strAge = request.getParameter("age");
		
		int age = !strAge.isEmpty() ? Integer.parseInt(strAge) : -1;
		
		System.out.println(name + " " + age);
		
		String result = "이름은 " + name + "이고 나이는 " + (age == -1 ? "알 수 없음" : age) + " 입니다."; 
		
		// response.setContentType("text/html; charset=utf-8"); // => 문자열 타입으로 데이터가 전달됨
		// response.getWriter().print("<span>" + result + "</span>");
		// response.getWriter().print(name);
		// response.getWriter().print(age);
		
		/**
		 * 여러 개의 데이터를 응답하고자 할 때 JSON 형태로 응답
		 * - JavaScript Object Notation
		 * => ajax 통신할 때 자주 사용되는 형식 중 하나
		 * 
		 * JS 배열 객체 : [값1, 값2, ...] 			=> JSONArray
		 * JS 일반 객체 : {키1:값1, 키2:값2, ...} 	=> JSONObject
		 * 
		 * 라이브러리 필요 (json-simple-x.x.x.jar)
		 */
		
		// 배열 객체(JSONArray)에 담아 응답 --> ArrayList와 유사
		/*
		JSONArray jsonArr = new JSONArray();
		jsonArr.add(name);
		jsonArr.add(age);
		
		response.setContentType("application/json; charset=utf-8");
		// => 응답데이터를 JSON 형태로 전달하고자 할 때 문서 형식을 JSON 형태로 설정해야 함
		response.getWriter().print(jsonArr);
		*/
		
		// 일반 객체(JSONObject)에 담아 응답 --> HashMap과 유사
		JSONObject jsonObj = new JSONObject(); // {}
		jsonObj.put("name", name);
		jsonObj.put("age", age);
		
		response.setContentType("application/json; charset=utf-8");
		response.getWriter().print(jsonObj);
		
	}

}
