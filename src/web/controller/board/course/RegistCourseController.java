package web.controller.board.course;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import web.biz.CourseBiz;
import web.dto.CourseDto;
import web.dto.MemberDto;

@WebServlet("/RegistCourseController")
public class RegistCourseController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		System.out.println("[ Controller : RegistCourseController ]");
		HttpSession session = request.getSession();
		MemberDto logindata = (MemberDto)session.getAttribute("loginMember");
		String course_title = request.getParameter("course_title");
		int tour1 = Integer.parseInt(request.getParameter("tour1"));
		int tour2 = Integer.parseInt(request.getParameter("tour2"));
		int tour3 = Integer.parseInt(request.getParameter("tour3"));
		int tour4 = Integer.parseInt(request.getParameter("tour4"));
		int tour5 = Integer.parseInt(request.getParameter("tour5"));
		String transport = request.getParameter("options_trans");
		String course_memo = request.getParameter("course_memo");
		
		CourseBiz biz = new CourseBiz();
		
		CourseDto dto = new CourseDto();
		dto.setMember_no(logindata.getM_no()); // 회원번호
		dto.setCourse_title(course_title);
		dto.setTour1(tour1);
		dto.setTour2(tour2);
		dto.setTour3(tour3);
		dto.setTour4(tour4);
		dto.setTour5(tour5);
		dto.setTransport(transport);
		dto.setCourse_memo(course_memo);
		
		boolean res;
		res = biz.insert(dto);
		if(res) {
			jsResponse("코스 작성 성공", "courseList.view?p=1", response);
		} else {
			jsResponse("코스 작성 실패","coursewrite", response);
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
