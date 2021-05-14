package web.biz;

import static common.JDBCTemplate.close;
import static common.JDBCTemplate.commit;
import static common.JDBCTemplate.getConnection;

import java.sql.Connection;
import java.util.List;

import web.dao.InquiryDao;
import web.dto.InquiryDto;

public class InquiryBiz {
	private InquiryDao dao = new InquiryDao();
	
	public InquiryDto selectOne(int inquiry_id) {
		Connection con = getConnection();
		InquiryDto res =  dao.selectOne(con , inquiry_id);
		close(con);
		
		return res;
	}
	
	public boolean insert(InquiryDto dto) {
		Connection con = getConnection();
		boolean res = dao.insert(con, dto);
		if(res) {
			commit(con);
		}
		close(con);
		
		return res;
	}
	
	public List<InquiryDto> selectPageList(int p){
		Connection con = getConnection();
		List<InquiryDto> res = dao.selectPageList(con, p);
		close(con);
		
		return res;
	}
	

	
	public int selectPageLength() {
		Connection con = getConnection();
		int res = dao.selectPageLength(con);
		close(con);
		
		return res;
	}
	
	public List<InquiryDto> mySelectPageList(int member_no , int p){
		Connection con = getConnection();
		List<InquiryDto> res = dao.mySelectPageList(con, member_no, p);
		close(con);
		
		return res;
	}
	
	public int mySelectPageLength(int member_no) {
		Connection con = getConnection();
		int res = dao.mySelectPageLength(con,member_no);
		close(con);
		
		return res;
	}
	
	public boolean StateUpdate(int id) {
		Connection con = getConnection();
		boolean res = dao.StateUpdate(con, id);
		if(res) {
			commit(con);
		}
		close(con);
		
		return res;
	}
	
	
}
