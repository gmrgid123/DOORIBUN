package web.controller.board.course;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.biz.CourseBiz;
import web.dto.CourseDto;

@WebServlet("/UpdateCourseController")
public class UpdateCourseController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		int course_id = Integer.parseInt(request.getParameter("course_id"));
		String course_title = request.getParameter("course_title");
		int tour1 = Integer.parseInt(request.getParameter("tour1"));
		int tour2 = Integer.parseInt(request.getParameter("tour2"));
		int tour3 = Integer.parseInt(request.getParameter("tour3"));
		int tour4 = Integer.parseInt(request.getParameter("tour4"));
		int tour5 = Integer.parseInt(request.getParameter("tour5"));
		String transport = request.getParameter("options_trans");
		String course_memo = request.getParameter("course_memo");
		
		CourseDto dto = new CourseDto();
		dto.setCourse_id(course_id);
		dto.setCourse_title(course_title);
		dto.setTour1(tour1);
		dto.setTour2(tour2);
		dto.setTour3(tour3);
		dto.setTour4(tour4);
		dto.setTour5(tour5);
		dto.setTransport(transport);
		dto.setCourse_memo(course_memo);
		
		System.out.println("컨트롤러 : " + dto);
		
		CourseBiz biz = new CourseBiz();
		if(biz.CourseUpdate(dto)) {
			String url = "DetailCourseController?course_id="+course_id;
			jsResponse("코스 수정을 성공했습니다.", url, response);
		} else {
			String url = "DetailCourseController?course_id="+course_id;
			jsResponse("코스 수정을 실패했습니다...!", url, response);
		}
		
		
	}
	
	
	private void jsResponse(String msg, String url, HttpServletResponse response) throws IOException {
		String s = "<script type='text/javascript'>"+
					"alert('"+ msg +"');" + 
					"location.href='"+ url +"';"+
					"</script>";
		PrintWriter out = response.getWriter();
		out.print(s);
	}
	
	

}
