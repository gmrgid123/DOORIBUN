package web.controller.board.notice;

import java.io.File;
import java.io.IOException;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import web.dao.MemberDao;
import web.dao.NoticeDao;
import web.dto.MemberDto;
import web.dto.NoticeDto;




@WebServlet("/NoticeController")
public class NoticeController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public NoticeController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");  
		
		File userpath = new File(".");
		System.out.println("userpath: "+ userpath.getAbsolutePath());
		
		String command = request.getParameter("command");
		System.out.println("[" + command + "]");
		NoticeDao dao = new NoticeDao();
		
		if(command.equals("list")) {
			int pageSize = 5; // 한 페이지에 출력할 레코드 수
			// 페이지 링크를 클릭한 번호 / 현재 페이지
			String pageNum = request.getParameter("pageNum");
			if (pageNum == null){ // 클릭한게 없으면 1번 페이지
				pageNum = "1";
			}
			// 연산을 하기 위한 pageNum 형변환 / 현재 페이지
			int currentPage = Integer.parseInt(pageNum);

			// 해당 페이지에서 시작할 레코드 / 마지막 레코드
			int startRow = ((currentPage - 1) * pageSize+1);
			int endRow = currentPage * pageSize;
			
			int count = 0;
			
			
			NoticeDao manager = new NoticeDao();
			count = manager.getCount(); // 데이터베이스에 저장된 총 갯수

			List<NoticeDto> list = manager.getList(startRow, endRow);
			int currentPageSize=count < endRow?count%pageSize:pageSize;
			
			
			MemberDao member_dao = new MemberDao();
			request.setAttribute("member_dao", member_dao);
			request.setAttribute("list", list);
			request.setAttribute("pageSize", currentPageSize-1);
			request.setAttribute("startRow", startRow);
			request.setAttribute("endRow", endRow);
			request.setAttribute("count", count);
			request.setAttribute("currentPage", currentPage);
			
			dispatch("/page/board/notice/noticelist.jsp", request, response);
		
			} else if (command.equals("boardupdate")) {
			int seq = Integer.parseInt(request.getParameter("notice_id"));
			String title = request.getParameter("notice_title");
			String content = request.getParameter("notice_content");

			NoticeDto dto = new NoticeDto(seq, title, content);
			int res1 = dao.update(dto);
			
			
			if (res1>0) {
				dispatch("NoticeController?command=detail&notice_id="+ seq, request, response);
			} else {
				dispatch("NoticeController?command=updateform&notice_id="+ seq, request, response);
			} 
			
		} else if (command.equals("detail")) {
			int seq = Integer.parseInt(request.getParameter("notice_id"));
			
			int rs = dao.count(seq);
			NoticeDto dto = dao.selectOne(seq); 
			//dto에 dao의 selectone(글번호)을 가져와서 보여주는 게 detail 페이지
			
			
			
			//다오의 카운트를 가져와서 히트랑 연결시켜
			
			//dto.getNotice_hit();
			MemberDao member_dao = new MemberDao();
			request.setAttribute("member_dao", member_dao);
			request.setAttribute("dto", dto);
			dispatch("/page/board/notice/boarddetail.jsp", request, response);
			
		} else if (command.equals("updateform")) {
			int seq = Integer.parseInt(request.getParameter("notice_id"));

			NoticeDto dto = dao.selectOne(seq);
			
			MemberDao member_dao = new MemberDao();
			request.setAttribute("member_dao", member_dao);
			request.setAttribute("dto", dto);
			dispatch("/page/board/notice/boardupdate.jsp", request, response);
		
		} else if (command.equals("boarddelete")){
			 String[] seqArr = new String[1];
			 seqArr[0] = request.getParameter("notice_id"); 
					 
			 int res = dao.delete(seqArr);
			 
			 if(res>0){
				 dispatch("NoticeController?command=list", request, response);
			} else {
				dispatch("NoticeController?command=list", request, response);
			}

		} 
			
			
		
		
	}
	
	private void dispatch(String url, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatch = request.getRequestDispatcher(url);
		dispatch.forward(request, response);
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("post");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");  
		
		NoticeDao dao = new NoticeDao();
		ServletContext context = getServletContext();
		String uploadPath = context.getRealPath("/resources/images");
		System.out.println(uploadPath);
		//String uploadPath = "C:\\workspace\\work05_web\\semi_temp.zip_expanded\\semi_temp\\WebContent\\resources\\images\\";
		//경로 바꿔주기 getRealPath
		
	    int size = 10 * 1024 * 1024;  // 업로드 사이즈 제한 10M 이하
	    int res = -1;
	    try{
		MultipartRequest multi = new MultipartRequest(request, uploadPath, size, "utf-8", new DefaultFileRenamePolicy());
		String command = multi.getParameter("command");
		System.out.println("command:" +  command);
	   
		if(command.equals("boardwrite")) {
			
				HttpSession session = request.getSession();
				MemberDto logindata = (MemberDto)session.getAttribute("loginMember");
				String notice_title = multi.getParameter("notice_title");
				String notice_content = multi.getParameter("notice_content");
				
				String oringin_filename = upload(multi, uploadPath);
				NoticeDto dto = new NoticeDto();
				dto.setMember_no(logindata.getM_no());
				dto.setNotice_title(notice_title);
				dto.setNotice_content(notice_content);
				dto.setOringin_filename(oringin_filename);

				res = dao.insert(dto);
				response.sendRedirect("NoticeController?command=list");
				
			} else if (command.equals("boardupdate")) {
			
				int seq = Integer.parseInt(multi.getParameter("notice_id"));
				System.out.println("업데이트 번호 : " + seq);
				String title = multi.getParameter("notice_title");
				String content = multi.getParameter("notice_content");
				Enumeration files = multi.getFileNames();
				String file = (String)files.nextElement(); 
				String oringin_file = multi.getParameter("oringin_file");
				
				System.out.println("file : "+file);
				NoticeDto dto = new NoticeDto(seq, title, content);
				if(file==null) {
					dto.setOringin_filename(oringin_file);
				}else {
					String oringin_filename = upload(multi, uploadPath);
					dto.setOringin_filename(oringin_filename);
				}
				
				
				//int res = dao.update(dto);
				
				res = dao.update(dto);
				response.sendRedirect("NoticeController?command=list");
				
			}
		
	    }catch(Exception e){
			e.printStackTrace();
		} 
		
		
	}
	
	private String upload(MultipartRequest multi, String uploadPath) {

		
		
		String fileName = ""; // 파일명
		
		
	        // 파일 업로드 및 업로드 후 파일명 가져옴
			
			
			Enumeration files = multi.getFileNames();
			String file = (String)files.nextElement(); 
			fileName = multi.getFilesystemName(file); 
			
		
	    // 업로드된 경로와 파일명을 통해 이미지의 경로를 생성
		if(fileName==null || fileName.isEmpty()) {
			return null;
		}
	
		return "/semi_temp/resources/images/" + fileName;
		
	}
	
}
	
