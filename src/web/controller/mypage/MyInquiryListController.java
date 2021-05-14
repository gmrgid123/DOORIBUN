package web.controller.mypage;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import web.biz.InquiryBiz;
import web.dto.InquiryDto;
import web.dto.MemberDto;

@WebServlet("/myinquiry")
public class MyInquiryListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		HttpSession session = request.getSession();
		MemberDto member = (MemberDto)session.getAttribute("loginMember");
		
		int p = Integer.parseInt(request.getParameter("p"));
		InquiryBiz biz = new InquiryBiz();
		if(member.getM_type().equals("관리자")) {
			List<InquiryDto> list = biz.selectPageList(p);
			int list_max = biz.selectPageLength();
			
			
			request.setAttribute("list", list);
			request.setAttribute("list_max", list_max);
			dispatch("/page/mypage/questionlist.jsp", request, response);
		} else {
			List<InquiryDto> list = biz.mySelectPageList(member.getM_no(), p);
			int list_max = biz.mySelectPageLength(member.getM_no());
			request.setAttribute("list", list);
			request.setAttribute("list_max", list_max);
			dispatch("/page/mypage/questionlist.jsp", request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}
	
	
	private void dispatch(String url, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatch = request.getRequestDispatcher(url);
		dispatch.forward(request, response);
	}
	
}
