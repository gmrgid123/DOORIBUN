package web.dao;



import static common.JDBCTemplate.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import web.dto.CourseDto;

public class CourseDao {
	
	public CourseDto selectOne(Connection con , int course_id) {
		PreparedStatement pstm = null;
		ResultSet rs = null;
		CourseDto res = null;
		String sql = " SELECT * FROM COURSE WHERE COURSE_ID = ? ";
		
		try {
			pstm = con.prepareStatement(sql);
			pstm.setInt(1, course_id);
			System.out.println("03. query 준비 : " + sql);
			
			rs = pstm.executeQuery();
			System.out.println("04. query 실행 및 리턴");
			
			while(rs.next()) {
				res = new CourseDto(
						rs.getInt(1), rs.getInt(2), rs.getString(3),
						rs.getInt(4), rs.getInt(5), rs.getInt(6), rs.getInt(7), rs.getInt(8),
						rs.getString(9), rs.getString(10), rs.getTimestamp(11),
						rs.getInt(12), rs.getInt(13)
					);
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
	
	public List<CourseDto> selectAll(Connection con){
		PreparedStatement pstm = null;
		ResultSet rs = null;
		List<CourseDto> res = new ArrayList<CourseDto>();
		String sql = " SELECT * FROM COURSE ORDER BY COURSE_ID DESC ";
		
		try {
			pstm = con.prepareStatement(sql);
			System.out.println("03. query 준비 : " + sql);
			
			rs = pstm.executeQuery();
			System.out.println("04. query 실행 및 리턴");
			
			while(rs.next()) {
				CourseDto temp = new CourseDto(
							rs.getInt(1), rs.getInt(2), rs.getString(3),
							rs.getInt(4), rs.getInt(5), rs.getInt(6), rs.getInt(7), rs.getInt(8),
							rs.getString(9), rs.getString(10), rs.getTimestamp(11),
							rs.getInt(12), rs.getInt(13)
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
	
	public List<CourseDto> mySelectPageList(Connection con, int member_no , int p){
		PreparedStatement pstm = null;
		ResultSet rs = null;
		List<CourseDto> res = new ArrayList<CourseDto>();

		int end_num = p*10;
		int start_nun = end_num-9;
		
		String sql = " SELECT * FROM (SELECT ROW_NUMBER() OVER(ORDER BY COURSE_ID DESC) AS NUM, C.* FROM COURSE C WHERE MEMBER_NO = ?) WHERE NUM <= ? AND NUM >= ? ";
		
		try {
			pstm = con.prepareStatement(sql);
			pstm.setInt(1, member_no);
			pstm.setInt(2, end_num);
			pstm.setInt(3, start_nun);
			
			System.out.println("03. query 준비 : " + sql);
			
			rs = pstm.executeQuery();
			System.out.println("04. query 실행 및 리턴");
			
			while(rs.next()) {
				CourseDto temp = new CourseDto(
							rs.getInt(2), rs.getInt(3), rs.getString(4),
							rs.getInt(5), rs.getInt(6), rs.getInt(7), rs.getInt(8), rs.getInt(9),
							rs.getString(10), rs.getString(11), rs.getTimestamp(12),
							rs.getInt(13), rs.getInt(14)
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
		String sql = " SELECT MAX(ROWNUM) FROM COURSE WHERE MEMBER_NO = ? ORDER BY COURSE_ID DESC ";
		
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
	
	
	public List<CourseDto> selectPageList(Connection con, int p){
		PreparedStatement pstm = null;
		ResultSet rs = null;
		List<CourseDto> res = new ArrayList<CourseDto>();

		int end_num = p*10;
		int start_nun = end_num-9;
		
		String sql = " SELECT * FROM (SELECT ROW_NUMBER() OVER(ORDER BY COURSE_ID DESC) AS NUM, C.* FROM COURSE C) WHERE NUM <= ? AND NUM >= ? ";
		
		try {
			pstm = con.prepareStatement(sql);
			pstm.setInt(1, end_num);
			pstm.setInt(2, start_nun);
			
			System.out.println("03. query 준비 : " + sql);
			
			rs = pstm.executeQuery();
			System.out.println("04. query 실행 및 리턴");
			
			while(rs.next()) {
				CourseDto temp = new CourseDto(
							rs.getInt(2), rs.getInt(3), rs.getString(4),
							rs.getInt(5), rs.getInt(6), rs.getInt(7), rs.getInt(8), rs.getInt(9),
							rs.getString(10), rs.getString(11), rs.getTimestamp(12),
							rs.getInt(13), rs.getInt(14)
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
		String sql = " SELECT MAX(ROWNUM) FROM COURSE ORDER BY COURSE_ID DESC ";
		
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
	
	
	
	public boolean insert(Connection con, CourseDto dto) {
		PreparedStatement pstm = null;
		int res = 0;
		String sql = " INSERT INTO COURSE VALUES( COURSE_SEQ.NEXTVAL, ? , ? , ? , ? , ? , ? , "
												+ "	? , ? , ? , SYSDATE , 0 , 0 ) ";
		
		try {
			pstm = con.prepareStatement(sql);
			pstm.setInt(1, dto.getMember_no());
			pstm.setString(2, dto.getCourse_title());
			pstm.setInt(3, dto.getTour1());
			pstm.setInt(4, dto.getTour2());
			pstm.setInt(5, dto.getTour3());
			pstm.setInt(6, dto.getTour4());
			pstm.setInt(7, dto.getTour5());
			pstm.setString(8, dto.getTransport());
			pstm.setString(9, dto.getCourse_memo());
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
	
	public boolean CourseDelete(Connection con, int course_id) {
		PreparedStatement pstm = null;
		int res = 0;
		String sql = "  DELETE FROM COURSE WHERE COURSE_ID=?  ";
		
		try {
			pstm=con.prepareStatement(sql);
			pstm.setInt(1, course_id);
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
	
	
	public boolean CourseUpdate(Connection con, CourseDto dto) {
		PreparedStatement pstm = null;
		int res = 0;
		String sql =  " UPDATE COURSE SET COURSE_TITLE = ?, TOUR1 = ?, TOUR2 = ?, TOUR3 = ? , TOUR4 = ? , TOUR5 = ?, TRANSPORT = ?, COURSE_MEMO = ? WHERE COURSE_ID = ? ";
		
		try {
			pstm = con.prepareStatement(sql);
			pstm.setString(1, dto.getCourse_title());
			pstm.setInt(2, dto.getTour1());
			pstm.setInt(3, dto.getTour2());
			pstm.setInt(4, dto.getTour3());
			pstm.setInt(5, dto.getTour4());
			pstm.setInt(6, dto.getTour5());
			pstm.setString(7, dto.getTransport());
			pstm.setString(8, dto.getCourse_memo());
			pstm.setInt(9, dto.getCourse_id());
			System.out.println("03. query 준비 : " + sql);
			
			res = pstm.executeUpdate();
			System.out.println("04. query 실행 및 리턴");
			
			
		} catch (SQLException e) {
			System.out.println("3/4단계 에러");
			e.printStackTrace();
		} finally {
			close(pstm);
			System.out.println("05. db 종료 \n");
		}

		return (res>0)?true:false;
		
	}
	
	public boolean HitUpdate(Connection con, int course_id) {
		PreparedStatement pstm = null;
		int res = 0;
		String sql = " UPDATE COURSE SET COURSE_HIT=COURSE_HIT+1 WHERE COURSE_ID = ? ";
		
		try {
			pstm = con.prepareStatement(sql);
			pstm.setInt(1, course_id);
			System.out.println("03. query 준비 : " + sql);
			
			res = pstm.executeUpdate();
			System.out.println("04. query 실행 및 리턴");
			
			
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
