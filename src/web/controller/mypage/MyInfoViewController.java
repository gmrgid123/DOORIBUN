package web.controller.mypage;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import web.dto.MemberDto;

/**
 * Servlet implementation class MyInfoViewController
 */
@WebServlet("/myinfoView")
public class MyInfoViewController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public MyInfoViewController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		HttpSession session = request.getSession();
		if(session.getAttribute("loginMember")!=null) {
			String pw = request.getParameter("pw");
			
			MemberDto member_dto = (MemberDto)session.getAttribute("loginMember");
			if(member_dto.getM_pw().equals(pw)) {
				String url = request.getContextPath()+"/page/mypage/myinfo.jsp";
				response.sendRedirect(url);
			} else {
				String url = request.getContextPath()+"/page/mypage/myinfocheck.jsp";
				jsResponse("비밀번호가 틀립니다.", url, response);
			}
			
		} else {
			String url = request.getContextPath() + "/page/login/login.jsp";
			jsResponse("로그인을 해주세요.", url, response);
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
