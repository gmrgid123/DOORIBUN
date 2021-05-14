package web.controller.board.course;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import web.biz.TourBiz;
import web.dto.TourDto;


@WebServlet("/WriteCourseController.do")
public class WriteCourseController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		String command = request.getParameter("command");
		System.out.println("["+command+"]");
		TourBiz tourBiz = new TourBiz();
		
		if(command.equals("tourlist")) {
			int index = Integer.parseInt(request.getParameter("selbox"));
			List<TourDto> list = tourBiz.selectAll();
			request.setAttribute("selbox", index);
			request.setAttribute("list", list);
			dispatch("/page/course/CourseSelect.jsp", request, response);
			
		} else if(command.equals("tourAjax")) {
			int id = Integer.parseInt(request.getParameter("tour_id"));
			TourDto dto = tourBiz.selectOne(id);
			
			Gson gson = new Gson();
			String jsonString = gson.toJson(dto);
			JsonParser parser = new JsonParser();
			JsonObject jsonObj = (JsonObject)parser.parse(jsonString);
			
			response.setContentType("application/json; charset=UTF-8");
			response.getWriter().print(jsonObj);
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
	}
	
	private void dispatch(String url, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatch = request.getRequestDispatcher(url);
		dispatch.forward(request, response);
	}
	
	 

}
