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
import web.dto.MemberDto;


@WebServlet("/UserRegisterServlet")
public class UserRegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public UserRegisterServlet() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
//		System.out.println("중복 컨트롤런");
//		//아이디는 겟해서 밝혀줘도 노상관
//		String m_id = request.getParameter("m_id");
//		
//		int res = new MemberDao().registercheck(m_id);
//		System.out.println(res+ m_id);
//		
//		if(res == 1) {
//			response.getWriter().println("{\"result\":true}"); //res가 1일때 true이고 나머지일때 false로 아작스한테 넘겨줌
//			return;
//		}else { response.getWriter().println("{\"result\":false}");
//		return;
//		}
//		
	
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		
		//""는 회원가입 페이지에서 보내는 각각의 파랄미터 값들
		String m_id = request.getParameter("m_id");
		String m_pw1 = request.getParameter("m_pw1");
		String m_pw2 = request.getParameter("m_pw2");
		String m_name = request.getParameter("m_name");
		String m_email = request.getParameter("m_email");
		String m_phone = request.getParameter("m_phone");
		String m_type = request.getParameter("m_type");
		
		MemberDao dao = new MemberDao();		
		int res = dao.register(m_id, m_pw1, m_name, m_email, m_phone , m_type); //pw랑 phone에서 숫자로만 적어도 스트링으로 인식시켜줌 ""
		MemberDto dto = dao.getMember(m_id);
		
		
		if(res == 1) {	//어차피 한줄이 들어가니까 1, 성공했으면
			
			//세션 생성
			HttpSession session = request.getSession(true);
			
			//세션 객체에 아이디 넣으면 회원가입하면서 동시에 로그인 시켜준거
			session.setAttribute("loginMember", dto);
			
			dispatch("/page/login/welcome.jsp", request, response); 
			
			
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
