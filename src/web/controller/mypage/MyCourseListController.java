package web.controller.mypage;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import web.biz.CourseBiz;
import web.dto.CourseDto;
import web.dto.MemberDto;

@WebServlet("/mycourse")
public class MyCourseListController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		HttpSession session = request.getSession();
		MemberDto myUser = (MemberDto)session.getAttribute("loginMember");
		int p = Integer.parseInt(request.getParameter("p"));
		
		
		CourseBiz biz = new CourseBiz();
		List<CourseDto> list = biz.mySelectPageList(myUser.getM_no(), p);
		int list_max = biz.mySelectPageLength(myUser.getM_no());
		
		System.out.println("컨트롤러 진입?"+list_max);
		
		request.setAttribute("list", list);
		request.setAttribute("list_max", list_max);
		dispatch("/page/mypage/mycourselist.jsp", request, response);
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}
	
	
	private void dispatch(String url, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatch = request.getRequestDispatcher(url);
		dispatch.forward(request, response);
	}

}
