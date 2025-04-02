package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import model.vo.User;

/**
 * Servlet implementation class JqAjaxFourController
 */
@WebServlet("/jqAjax4.do")
public class JqAjaxFourController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public JqAjaxFourController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<User> uList = new ArrayList<>();
		
		// Service를 통해서 작업해야하는 부분(여기서는 실습이므로 임의의 데이터 추가)
		uList.add(new User(1, "sj", "임성준", "인천"));
		uList.add(new User(2, "ee", "에일리", "서울"));
		uList.add(new User(3, "hh", "이홍기", "제주"));
		
		Gson gson = new Gson();

		response.setContentType("application/json; charset=utf-8");
		gson.toJson(uList, response.getWriter());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
