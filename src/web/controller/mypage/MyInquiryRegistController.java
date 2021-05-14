package web.controller.mypage;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import web.biz.InquiryBiz;
import web.dto.InquiryDto;
import web.dto.MemberDto;

@WebServlet("/inquiryregist")
public class MyInquiryRegistController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		String inquiry_title = request.getParameter("title");
		String inquiry_content = request.getParameter("content");
		
		HttpSession session = request.getSession();
		MemberDto myUser = (MemberDto)session.getAttribute("loginMember");
		
		InquiryDto dto = new InquiryDto();
		dto.setMember_no(myUser.getM_no());
		dto.setInquiry_title(inquiry_title);
		dto.setInquiry_content(inquiry_content);
		
		InquiryBiz biz = new InquiryBiz();
		
		if(biz.insert(dto)) {
			jsResponse("작성 완료했습니다.", "/semi_temp/myinquiry?p=1", response);
		} else {
			jsResponse("작성 중에 문제가 발생했습니다.", "/semi_temp/inquirywrite", response);
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
