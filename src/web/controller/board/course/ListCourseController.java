package web.controller.board.course;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.biz.CourseBiz;
import web.dao.MemberDao;
import web.dto.CourseDto;


@WebServlet("/courseList.view")
public class ListCourseController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		int p = Integer.parseInt(request.getParameter("p"));
		
		
		CourseBiz biz = new CourseBiz();
		List<CourseDto> list = biz.selectPageList(p);
		int list_max = biz.selectPageLength();
		
		MemberDao member_dao = new MemberDao();
		request.setAttribute("member_dao", member_dao);
		
		request.setAttribute("list", list);
		request.setAttribute("list_max", list_max);
		dispatch("/page/course/CourseList.jsp", request, response);
		
		
		
	}
			
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}
	
	
	private void dispatch(String url, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatch = request.getRequestDispatcher(url);
		dispatch.forward(request, response);
	}
	

}
