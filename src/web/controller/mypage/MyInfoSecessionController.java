package web.controller.mypage;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import web.dao.MemberDao;
import web.dto.MemberDto;

@WebServlet("/myinfo-secession")
public class MyInfoSecessionController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		HttpSession session = request.getSession();
		MemberDto MyData =  (MemberDto)session.getAttribute("loginMember");
		MemberDao dao = new MemberDao();
		
		if(dao.secession(MyData.getM_no())) {
			session.setAttribute("loginMember", null);
			jsResponse("그 동안 감사했습니다.", "/semi_temp/index.jsp", response);
		}else {
			jsResponse("탈퇴 과정에 문제가 생겼습니다.", "/semi_temp/page/mypage/mypagemain.jsp", response);
		}
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
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
