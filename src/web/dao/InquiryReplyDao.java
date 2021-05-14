package web.dao;

import static common.JDBCTemplate.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import web.dto.InquiryReplyDto;

public class InquiryReplyDao {

	public InquiryReplyDto selectOne(Connection con,int inq_id) {
		PreparedStatement pstm = null;
		ResultSet rs = null;
		InquiryReplyDto res = null;
		
		String sql = " SELECT * FROM INQUIRY_REPLY WHERE INQUIRY_ID = ? ";
		
		try {
			pstm = con.prepareStatement(sql);
			pstm.setInt(1, inq_id);
			System.out.println("03. query 준비 : " + sql);
			
			rs = pstm.executeQuery();
			System.out.println("04. query 실행 및 리턴");
			
			while(rs.next()) {
				res = new InquiryReplyDto(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getString(4), rs.getString(5), rs.getTimestamp(6));
			}
			
			
		} catch (SQLException e) {
			System.out.println("3/4단계 에러");
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstm);
			System.out.println("05. db 종료\n");
		}
		
		
		return res;
		
	}
	
	
	public boolean insert(Connection con , InquiryReplyDto dto) {
		PreparedStatement pstm = null;
		int res = 0;
		
		String sql = " INSERT INTO INQUIRY_REPLY VALUES(INQUIRY_REPLY_SEQ.NEXTVAL, ?, ?, ?, ?, SYSDATE) ";
		
		try {
			pstm=con.prepareStatement(sql);
			pstm.setInt(1, dto.getInquiry_id());
			pstm.setInt(2, dto.getMember_no());
			pstm.setString(3, dto.getReply_title());
			pstm.setString(4, dto.getReply_content());
			System.out.println("03. query 준비 : " + sql);
			
			res = pstm.executeUpdate();
			System.out.println("04. quert 실행 및 리턴");
			
		} catch (SQLException e) {
			System.out.println("3/4단계 에러");
			e.printStackTrace();
		} finally {
			close(pstm);
			System.out.println("05. db 종료 \n");
		}
		
		return (res>0)?true:false;
	}
	
	
}
