package web.controller.board.course;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;



import web.biz.TourBiz;
import web.dto.TourDto;

@WebServlet("/TourSelectController")
public class TourSelectController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json; charset=UTF-8");
		TourBiz tourBiz = new TourBiz();
		
		int Tid = Integer.parseInt(request.getParameter("Tid"));
		System.out.println("[ servlet : "+Tid+"]");
		TourDto dto = tourBiz.selectOne(Tid);
		
		String gson = new Gson().toJson(dto); 
		System.out.println(gson);
		
		try {
			response.getWriter().write(gson);
		}catch (JsonIOException e) {
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}
	}

}
