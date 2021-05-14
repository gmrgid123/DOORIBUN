package web.controller.board.course;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.biz.CourseBiz;
import web.dao.MemberDao;
import web.dto.CourseDto;

@WebServlet("/DetailCourseController")
public class DetailCourseController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		System.out.println("Course Detail Controller");
		
		int course_id = Integer.parseInt(request.getParameter("course_id"));
		
		CourseBiz biz = new CourseBiz();
		MemberDao member_dao = new MemberDao();
		
		
		if(biz.HitUpdate(course_id)) {
		CourseDto dto = biz.selectOne(course_id);
		request.setAttribute("dto", dto);
		request.setAttribute("member_dao", member_dao);
		dispatch("/page/course/CourseDetail.jsp", request, response);
		}
		
	}
	
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}
	
	private void dispatch(String url, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatch = request.getRequestDispatcher(url);
		dispatch.forward(request, response);
	}

}
