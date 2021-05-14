package web.controller.board.course;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.biz.CourseBiz;


@WebServlet("/DeleteCourseController")
public class DeleteCourseController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		int course_id = Integer.parseInt(request.getParameter("course_id"));
		CourseBiz biz = new CourseBiz();
		
		if(biz.CourseDelete(course_id)) {
			String url = "courseList.view?p=1";
			jsResponse("코스 삭제를 성공했습니다.", url, response);
		} else {
			String url = "DetailCourseController?course_id="+course_id;
			jsResponse("코스 삭제를 실패했습니다.", url, response);
		}
		
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
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
