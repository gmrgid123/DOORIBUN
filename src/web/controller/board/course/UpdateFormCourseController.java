package web.controller.board.course;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import web.biz.CourseBiz;
import web.biz.TourBiz;
import web.dto.CourseDto;
import web.dto.MemberDto;

@WebServlet("/UpdateFormCourseController")
public class UpdateFormCourseController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public UpdateFormCourseController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		int course_id = Integer.parseInt(request.getParameter("course_id"));
		


		CourseBiz biz = new CourseBiz();
		CourseDto dto = biz.selectOne(course_id);
		
		HttpSession session = request.getSession();
		MemberDto member = (MemberDto)session.getAttribute("loginMember");
		
		
		
		if( dto.getMember_no() == member.getM_no() || member.getM_type().equals("관리자") ) {
			request.setAttribute("dto", dto);
			TourBiz tour_biz = new TourBiz();
			request.setAttribute("tour_biz", tour_biz);
			dispatch("/page/course/CourseUpdate.jsp", request, response);
		} else {
			String url = "DetailCourseController?course_id="+course_id;
			jsResponse("로그인을 하신 후 이용해주세요." , url, response);
		}
		
		
		
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}
	
	private void dispatch(String url, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatch = request.getRequestDispatcher(url);
		dispatch.forward(request, response);
		
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
