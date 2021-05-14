package web.controller.login;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import web.dao.MemberDao;
import web.dto.MemberDto;


@WebServlet("/UserPhoneCheckServlet")
public class UserPhoneCheckServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public UserPhoneCheckServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		String m_phone = request.getParameter("m_phone");
		HttpSession session = request.getSession();
		
		if(session.getAttribute("loginMember")!=null) {
			MemberDto logindata = (MemberDto)session.getAttribute("loginMember");
			response.getWriter().write(new MemberDao().phoneCheck(m_phone,logindata.getM_no())+ ""); //""로 스트링화
		}else {
			response.getWriter().write(new MemberDao().phoneCheck(m_phone)+ ""); //""로 스트링화
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
