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
import web.biz.InquiryReplyBiz;
import web.dto.InquiryReplyDto;
import web.dto.MemberDto;

@WebServlet("/inquiry-reply-regist")
public class MyInquiryReplyRegistController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		HttpSession session = request.getSession();
		MemberDto admindata =  (MemberDto)session.getAttribute("loginMember");
		
		
		int inquiry_id = Integer.parseInt(request.getParameter("inquiry_id"));
		String reply_title = request.getParameter("reply_title");
		String reply_content = request.getParameter("reply_content");
		
		InquiryReplyBiz reply_biz = new InquiryReplyBiz();
		InquiryReplyDto reply_dto = new InquiryReplyDto();
		reply_dto.setInquiry_id(inquiry_id);
		reply_dto.setMember_no(admindata.getM_no());
		reply_dto.setReply_title(reply_title);
		reply_dto.setReply_content(reply_content);
		System.out.println("답변 입력 데이터"+reply_dto);
		if(reply_biz.insert(reply_dto)) {
			InquiryBiz biz = new InquiryBiz();
			if(biz.StateUpdate(inquiry_id)) {
				jsResponse("답변등록을 하셨습니다.", "/semi_temp/myinquiry-view?inq_id="+inquiry_id, response);
			}
		} else {
			jsResponse("답변등록을 실패했습니다..", "/semi_temp/myinquiry-view?inq_id="+inquiry_id, response);
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
