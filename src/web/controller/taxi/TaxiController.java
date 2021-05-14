package web.controller.taxi;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import web.biz.TaxiBiz;
import web.biz.TourBiz;
import web.dao.MemberDao;
import web.dto.MemberDto;
import web.dto.TaxiDto;
import web.dto.TourDto;



	
	

@WebServlet("/taxiControl")
public class TaxiController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		String command = request.getParameter("command");
		System.out.println("["+command+"]");
		
		TaxiBiz biz = new TaxiBiz();
		TourBiz tourBiz = new TourBiz();
		MemberDao member_dao = new MemberDao();
		
		if(command.equals("list")) {
			HttpSession session = request.getSession();
			if(session.getAttribute("loginMember") != null) {
				MemberDto logindata = (MemberDto)session.getAttribute("loginMember");
				
				if(logindata.getM_type().equals("관리자")) {
					List<TaxiDto> list=biz.selectAll();
					request.setAttribute("member_dao", member_dao);
					request.setAttribute("list", list);
					dispatch("page/taxi/taxi_list.jsp",request,response);
				
				}else if(logindata.getM_type().equals("일반회원")){
					List<TaxiDto> mylist = biz.myListSelect(logindata.getM_no());
					request.setAttribute("member_dao", member_dao);
					request.setAttribute("list", mylist);
					dispatch("page/taxi/taxi_list.jsp",request,response);
				
				}else if(logindata.getM_type().equals("택시기사")) {
					List<TaxiDto> driverlist = biz.driverListSelect();
					request.setAttribute("member_dao", member_dao);
					request.setAttribute("list", driverlist);
					dispatch("page/taxi/driver_list.jsp",request,response);
				}
				
			} else {
				String url = request.getContextPath() + "/page/login/login.jsp";
				jsResponse("로그인을 하신 후 이용해주세요." , url, response);
			}
			
		}else if(command.equals("updateform")) {
			int seq=Integer.parseInt(request.getParameter("seq"));
			
			TaxiDto dto = biz.selectOne(seq);
			System.out.println(dto.getBook_date());
			
			request.setAttribute("dto", dto);
			dispatch("/page/taxi/taxi_update.jsp",request,response);
			
		}else if(command.equals("delete")) {
			int seq = Integer.parseInt(request.getParameter("seq"));
			
			boolean res=biz.delete(seq);
			
			if(res) {
				dispatch("taxi.do?command=list",request,response);
			}else {
				dispatch("taxi.do?command=list",request,response);
			}
		}else if(command.equals("list_insert")) {
			System.out.println(request.getContextPath());
			response.sendRedirect("page/taxi/taxi_insert.jsp");

		}else if(command.equals("insert")) {
			HttpSession session = request.getSession();
			MemberDto logindata = (MemberDto)session.getAttribute("loginMember");
			String start_addr= request.getParameter("start_addr");
			String end_addr=request.getParameter("end_addr");
			String date= request.getParameter("date");
			String time= request.getParameter("time");
			System.out.println(date);
			System.out.println(time);
			
			
			TaxiDto dto = null;
			
			String from =date+" "+time;
			
			SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");

			Date book_date;
			try {
				book_date = transFormat.parse(from);
				dto = new TaxiDto();
				dto.setMember_no(logindata.getM_no());
				dto.setStart_addr(start_addr);
				dto.setEnd_addr(end_addr);
				dto.setBook_date(book_date);
			} catch (ParseException e) {
				e.printStackTrace();
			}

		boolean res = biz.insert(dto);
			
			if(res) {	
				jsResponse("글 작성 성공","taxi.do?command=list",response);
			}else {	
				dispatch("taxi.do?command=list_insert",request,response);
			}

			
			
			
		}else if(command.equals("taxi_update")) {
			
			int seq = Integer.parseInt(request.getParameter("seq"));
			String start_addr= request.getParameter("start_addr");
			String end_addr=request.getParameter("end_addr");
			String date= request.getParameter("date");
			String time= request.getParameter("time");
			System.out.println(date);
			System.out.println(time);
			
			
			TaxiDto dto = null;
			
			String from =date+" "+time;
			
			SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");

			Date book_date;
			try {
				book_date = transFormat.parse(from);
				dto = new TaxiDto(seq,start_addr,end_addr,book_date);
				
			} catch (ParseException e) {
				e.printStackTrace();
			}

		boolean res = biz.update(dto);
			
			if(res) {	
				jsResponse("예약 수정 성공","/semi_temp/taxi.do?command=list",response);
			}else {	
				dispatch("taxi.do?command=list_insert",request,response);
			}
		
		}else if(command.equals("driver_list")) {
			List<TaxiDto> list=biz.applyAll();
			
			request.setAttribute("list", list);
			
			dispatch("page/taxi/driver_list.jsp",request,response);
			
					
		}else if(command.equals("driverinfo")){
			int seq = Integer.parseInt(request.getParameter("seq"));
			MemberDao m_dao = new MemberDao();
			MemberDto dto = m_dao.getMember(seq);
			
			request.setAttribute("dto", dto);
			dispatch("/page/taxi/driver_info.jsp",request, response);
			
		}else if(command.equals("tourlist1")) {
		
			List<TourDto> list = tourBiz.selectAll();
		
			request.setAttribute("list", list);
			dispatch("/page/taxi/site_start.jsp", request, response);
			
		}else if(command.equals("tourlist2")) {
		
			List<TourDto> list = tourBiz.selectAll();
		
			request.setAttribute("list", list);
			dispatch("/page/taxi/site_end.jsp", request, response);
		
		}else if(command.equals("driverConfirm")) {
			
			int taxi_id = Integer.parseInt(request.getParameter("seq"));
			
			System.out.println("드라이버 컨펌 컨트롤러");
			
			HttpSession session = request.getSession();
			MemberDto logindata = (MemberDto)session.getAttribute("loginMember");
			int driver_no = logindata.getM_no();
			
	

				boolean res = biz.driverConfirm(taxi_id, driver_no);
			
			if(res) {	
				jsResponse("매칭이 성공되었습니다.","taxi.do?command=list",response);
			}else {	
				dispatch("taxi.do?command=list",request,response);
			}
			
			
		}else if(command.equals("driverConfirmForm")) {
			
			List<TaxiDto> list=biz.applyAll();
			//request.setAttribute("member_dao", member_dao);
			request.setAttribute("list", list);
			dispatch("taxi.do?command=list",request,response);
			
		}else if(command.equals("driverCancel")) {
			int seq = Integer.parseInt(request.getParameter("seq"));
			
			

				boolean res = biz.driverCancel(seq);
			
			if(res) {	
				jsResponse("매칭이 취소되었습니다.","taxi.do?command=list",response);
			}else {	
				dispatch("taxi.do?command=list",request,response);
			}
			
		}else if(command.equals("driverCancelForm")) {
			List<TaxiDto> list=biz.applyAll();
			//request.setAttribute("member_dao", member_dao);
			request.setAttribute("list", list);
			dispatch("page/taxi/driver_list.jsp",request,response);
		}
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	
	}
	private void jsResponse(String msg, String url, HttpServletResponse response) throws IOException {
		String s = "<script type='text/javascript'>"+
				"alert('"+msg+"');"+
				"location.href='"+url+"';"+
				"</script>";
		PrintWriter out = response.getWriter();
		out.print(s);
	}
	
	
	
	private void dispatch(String url, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatch = request.getRequestDispatcher(url);
		dispatch.forward(request, response);
	}
}