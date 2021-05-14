package web.controller.mypage;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.biz.InquiryBiz;
import web.biz.InquiryReplyBiz;
import web.dao.MemberDao;
import web.dto.InquiryDto;
import web.dto.InquiryReplyDto;


@WebServlet("/myinquiry-view")
public class MyInquiryViewController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		int inquiry_id = Integer.parseInt(request.getParameter("inq_id"));
		
		MemberDao member_dao = new MemberDao();
		InquiryDto inq_dto = new InquiryDto();
		InquiryBiz inq_biz = new InquiryBiz();
		inq_dto = inq_biz.selectOne(inquiry_id);
		System.out.println(inq_dto);
		if(inq_dto.getInquiry_state().equals("미완료")) {
			request.setAttribute("inq_dto", inq_dto);
			request.setAttribute("member_dao", member_dao);
			dispatch("/page/mypage/questionview.jsp", request, response);
		} else {
			InquiryReplyDto reply_dto = new InquiryReplyDto();
			InquiryReplyBiz reply_biz = new InquiryReplyBiz();
			reply_dto = reply_biz.selectOne(inquiry_id);
			request.setAttribute("reply_dto", reply_dto);
			request.setAttribute("inq_dto", inq_dto);
			request.setAttribute("member_dao", member_dao);
			dispatch("/page/mypage/questionview.jsp", request, response);
		}
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
	
	private void dispatch(String url, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatch = request.getRequestDispatcher(url);
		dispatch.forward(request, response);
	}

}
