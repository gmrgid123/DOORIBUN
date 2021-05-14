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

import web.dao.CommunityDao;
import web.dao.MemberDao;
import web.dto.CommunityDto;
import web.dto.MemberDto;

@WebServlet("/mycommunity")
public class MyCommunityListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public MyCommunityListController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");  
		
		HttpSession session = request.getSession();
		MemberDto myUser = (MemberDto)session.getAttribute("loginMember");
		
		int pageSize = 5; // 한 페이지에 출력할 레코드 수
		// 페이지 링크를 클릭한 번호 / 현재 페이지
		String pageNum = request.getParameter("p");
		if (pageNum == null){ // 클릭한게 없으면 1번 페이지
			pageNum = "1";
		}
		// 연산을 하기 위한 pageNum 형변환 / 현재 페이지
		int currentPage = Integer.parseInt(pageNum);

		// 해당 페이지에서 시작할 레코드 / 마지막 레코드
		int startRow = ((currentPage - 1) * pageSize+1);
		int endRow = currentPage * pageSize;
		
		int count = 0;
		
		
		CommunityDao manager = new CommunityDao();
		count = manager.getMyListCount(myUser.getM_no()); // 데이터베이스에 저장된 총 갯수
		
		List<CommunityDto> list = manager.getMyList(startRow, endRow, myUser.getM_no());
		int currentPageSize=count < endRow?count%pageSize:pageSize;
		
		MemberDao member_dao = new MemberDao();
		request.setAttribute("member_dao", member_dao);
		request.setAttribute("list", list);
		request.setAttribute("pageSize", currentPageSize-1);
		request.setAttribute("startRow", startRow);
		request.setAttribute("endRow", endRow);
		request.setAttribute("count", count);
		request.setAttribute("currentPage", currentPage);
		
		dispatch("/page/mypage/myboardlist.jsp", request, response);
	
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}
	
	
	private void dispatch(String url, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatch = request.getRequestDispatcher(url);
		dispatch.forward(request, response);
		
	}

}
