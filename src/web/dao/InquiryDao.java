package web.dao;

import static common.JDBCTemplate.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import web.dto.InquiryDto;

public class InquiryDao {
	
	public InquiryDto selectOne(Connection con, int id) {
		PreparedStatement pstm = null;
		ResultSet rs = null;
		InquiryDto res = null;
		
		String sql = " SELECT * FROM INQUIRY WHERE INQUIRY_ID = ? ";
		
		try {
			pstm = con.prepareStatement(sql);
			pstm.setInt(1, id);
			System.out.println("03. query 준비 : " + sql);
			
			rs = pstm.executeQuery();
			System.out.println("04. quert 실행 및 리턴");
			while(rs.next()) {
				res = new InquiryDto(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4), rs.getTimestamp(5), rs.getString(6));
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
	
	public boolean insert(Connection con, InquiryDto dto) {
		PreparedStatement pstm = null;
		int res = 0;
		String sql = " INSERT INTO INQUIRY VALUES( INQUIRY_SEQ.NEXTVAL, ? , ? , ? , SYSDATE, '미완료') ";
		
		try {
			pstm = con.prepareStatement(sql);
			pstm.setInt(1, dto.getMember_no());
			pstm.setString(2, dto.getInquiry_title());
			pstm.setString(3, dto.getInquiry_content());
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
	
	
	
	public List<InquiryDto> mySelectPageList(Connection con, int member_no , int p){
		PreparedStatement pstm = null;
		ResultSet rs = null;
		List<InquiryDto> res = new ArrayList<InquiryDto>();

		int end_num = p*10;
		int start_nun = end_num-9;
		
		String sql = " SELECT * FROM (SELECT ROW_NUMBER() OVER(ORDER BY INQUIRY_ID DESC) AS NUM, C.* FROM INQUIRY C WHERE MEMBER_NO = ?) WHERE NUM <= ? AND NUM >= ? ";
		
		try {
			pstm = con.prepareStatement(sql);
			pstm.setInt(1, member_no);
			pstm.setInt(2, end_num);
			pstm.setInt(3, start_nun);
			
			System.out.println("03. query 준비 : " + sql);
			
			rs = pstm.executeQuery();
			System.out.println("04. query 실행 및 리턴");
			
			while(rs.next()) {
				InquiryDto temp = new InquiryDto(
						rs.getInt(2), rs.getInt(3),rs.getString(4),
						rs.getString(5),rs.getTimestamp(6),rs.getString(7)
						);
				
				res.add(temp);
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
	
	
	public int mySelectPageLength(Connection con , int member_no) {
		PreparedStatement pstm = null;
		ResultSet rs = null;
		int res = 0;
		String sql = " SELECT MAX(ROWNUM) FROM INQUIRY WHERE MEMBER_NO = ? ORDER BY INQUIRY_ID DESC ";
		
		try {
			pstm = con.prepareStatement(sql);
			pstm.setInt(1, member_no);
			System.out.println("03. query 준비 : " + sql);
			
			rs = pstm.executeQuery();
			System.out.println("04. query 실행 및 리턴");
			
			while(rs.next()) {
				res = rs.getInt(1);
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
	
	
	public List<InquiryDto> selectPageList(Connection con, int p){
		PreparedStatement pstm = null;
		ResultSet rs = null;
		List<InquiryDto> res = new ArrayList<InquiryDto>();

		int end_num = p*10;
		int start_nun = end_num-9;
		
		String sql = " SELECT * FROM (SELECT ROW_NUMBER() OVER(ORDER BY INQUIRY_ID DESC) AS NUM, C.* FROM INQUIRY C) WHERE NUM <= ? AND NUM >= ? ";
		
		try {
			pstm = con.prepareStatement(sql);
			pstm.setInt(1, end_num);
			pstm.setInt(2, start_nun);
			
			System.out.println("03. query 준비 : " + sql);
			
			rs = pstm.executeQuery();
			System.out.println("04. query 실행 및 리턴");
			
			while(rs.next()) {
				InquiryDto temp = new InquiryDto(
							rs.getInt(2), rs.getInt(3),rs.getString(4),
							rs.getString(5),rs.getTimestamp(6),rs.getString(7)
						);
				
				res.add(temp);
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
	
	
		
	
	public int selectPageLength(Connection con) {
		PreparedStatement pstm = null;
		ResultSet rs = null;
		int res = 0;
		String sql = " SELECT MAX(ROWNUM) FROM INQUIRY ORDER BY INQUIRY_ID DESC ";
		
		try {
			pstm = con.prepareStatement(sql);
			System.out.println("03. query 준비 : " + sql);
			
			rs = pstm.executeQuery();
			System.out.println("04. query 실행 및 리턴");
			
			while(rs.next()) {
				res = rs.getInt(1);
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
	
	public boolean StateUpdate(Connection con, int id) {
		PreparedStatement pstm = null;
		int res = 0;
		String sql = " UPDATE INQUIRY SET INQUIRY_STATE = '완료' WHERE INQUIRY_ID = ? ";
		
		try {
			pstm = con.prepareStatement(sql);
			pstm.setInt(1, id);
			
			res = pstm.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstm);
		}
		
		return (res>0)?true:false;
		
	}
	
	
}
