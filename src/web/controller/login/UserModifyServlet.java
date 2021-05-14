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



@WebServlet("/UserModifyServlet")
public class UserModifyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public UserModifyServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		HttpSession session = request.getSession(true); //세션이 없으면 생성시켜줘라 ....ㅎ
		MemberDto dto = (MemberDto)session.getAttribute("loginMember");
		
		String m_id = dto.getM_id();
		String m_pw = request.getParameter("m_pw"); //modify.jsp 에 있는 수정될 데이터를 가져온거.
		String m_email = request.getParameter("m_email");
		String m_phone = request.getParameter("m_phone");
		System.out.println(m_id + m_pw + m_email + m_phone);
		
		MemberDaoInterface dao = new MemberDao();
		int res = dao.updateMember(m_id, m_pw, m_email, m_phone); //데이터베이스에 업데이트함.

		
		if(res == 1) {	
			
			dto = dao.getMember(m_id); //업데이트된거에서 다시가져와라
			//세션 생성
			session.setAttribute("loginMember", dto);
			
			dispatch("/page/login/modifycomplete.jsp", request, response); 
			
			
		}else { //다오에서 0이나 -1일때
			request.setAttribute("fail", res);
			dispatch("/page/login/error.jsp", request, response); 
		}

		
		
	}

	private void dispatch(String url, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		RequestDispatcher dispatch = request.getRequestDispatcher(url);
		dispatch.forward(request, response); 
	
	}
	
	
	
	
	
}
