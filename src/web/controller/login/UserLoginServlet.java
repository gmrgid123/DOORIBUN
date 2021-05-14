package web.controller.login;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import web.dao.MemberDao;
import web.dao.MemberDaoInterface;
import web.dto.MemberDto;


@WebServlet("/UserLoginServlet")
public class UserLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private MemberDaoInterface dao = new MemberDao();
       
    public UserLoginServlet() {
        super();
      
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		System.out.println("call login");
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
		

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		String m_id = request.getParameter("m_id");
		String m_pw = request.getParameter("m_pw");
		System.out.println("id = "+m_id +", "+ m_pw);
	

		int res = dao.login(m_id, m_pw);
		System.out.println("res =" + res);
		
		if(res == 1) {
			//로그인한 정보가 일치할경우 session에 담아줘야한다. 
			MemberDto loginMember = dao.getMember(m_id);
			
			if(loginMember.getM_enabled().equals("Y")) {
				//세션 생성
				HttpSession session = request.getSession(true);//세션이 없으면 생성시켜줘라
				
				//세션 객체에 아이디만 넣으면 로그인됐다는 거를 세션에 넣기
				session.setAttribute("loginMember", loginMember);
				
				
				dispatch("/page/login/loginwelcome.jsp", request, response); 
			} else {
				request.setAttribute("fail", 404);
				dispatch("/page/login/error.jsp", request, response); 
			}
			
		}else {
			request.setAttribute("fail", res);
			dispatch("/page/login/error.jsp", request, response); 
		}
		
		
	}

	
	private void dispatch(String url, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		RequestDispatcher dispatch = request.getRequestDispatcher(url);
		dispatch.forward(request, response); //포워드 : ~.jsp라는 파일을 전달한다.
	
	}
	
	
}
