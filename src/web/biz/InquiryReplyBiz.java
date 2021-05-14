package web.biz;

import static common.JDBCTemplate.*;

import java.sql.Connection;

import web.dao.InquiryReplyDao;
import web.dto.InquiryReplyDto;


public class InquiryReplyBiz {
	private InquiryReplyDao dao = new InquiryReplyDao();
	
	public InquiryReplyDto selectOne(int inq_id) {
		Connection con = getConnection();
		InquiryReplyDto res = dao.selectOne(con, inq_id);
		close(con);
		
		return res;
	}
	
	public boolean insert(InquiryReplyDto dto) {
		Connection con = getConnection();
		boolean res = dao.insert(con, dto);
		if(res) {
			commit(con);
		}
		close(con);
		
		return res;
	}
	
	
	
}
