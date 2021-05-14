package web.controller.tour.introduce;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.biz.TourBiz;
import web.dto.TourDto;

@WebServlet("/introduceServlet")
public class introduceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		TourBiz biz = new TourBiz();
		
		String command = request.getParameter("command");
		System.out.println("command : " + command);
		
		if(command.equals("GangseoGu")) {
			int id = Integer.parseInt(request.getParameter("tour_id"));
			
			TourDto dto = biz.selectGangseoGu(id);
			
			request.setAttribute("dto", dto);
			dispatch("about.jsp", request, response);
			System.out.println(dto);
			
		}else if(command.equals("GwanakGu")) {
			int id = Integer.parseInt(request.getParameter("tour_id"));
			
			TourDto dto = biz.selectGwanakGu(id);
			
			request.setAttribute("dto", dto);
			dispatch("about.jsp", request, response);
			System.out.println(dto);
			
		}else if(command.equals("SeongbukGu")) {
			int id = Integer.parseInt(request.getParameter("tour_id"));
			
			TourDto dto = biz.selectSeongbukGu(id);
			
			request.setAttribute("dto", dto);
			dispatch("about.jsp", request, response);
			System.out.println(dto);
		}else if(command.equals("EunpyeongGu")) {
			int id = Integer.parseInt(request.getParameter("tour_id"));
			
			TourDto dto = biz.selectEunpyeongGu(id);
			
			request.setAttribute("dto", dto);
			dispatch("about.jsp", request, response);
			System.out.println(dto);
		}else if(command.equals("JungnangGu")) {
			int id = Integer.parseInt(request.getParameter("tour_id"));
			
			TourDto dto = biz.selectJungnangGu(id);
			
			request.setAttribute("dto", dto);
			dispatch("about.jsp", request, response);
			System.out.println(dto);
		}else if(command.equals("SeochoGu")) {
			int id = Integer.parseInt(request.getParameter("tour_id"));
			
			TourDto dto = biz.selectSeochoGu(id);
			
			request.setAttribute("dto", dto);
			dispatch("about.jsp", request, response);
			System.out.println(dto);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	private void dispatch(String url, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatch = request.getRequestDispatcher(url);
		dispatch.forward(request, response);
	}
}
