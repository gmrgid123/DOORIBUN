package web.dao;

import static common.JDBCTemplate.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import web.dto.MemberDto;
import web.dto.TaxiDto;

public class TaxiDao {
	public List<TaxiDto> selectAll(Connection con){
		
		PreparedStatement pstm=null;
		ResultSet rs= null;
		List<TaxiDto> res= new ArrayList<TaxiDto>();
		
		String sql="SELECT*FROM TAXI ORDER BY TAXI_id DESC";
		
		
		try {
			pstm=con.prepareStatement(sql);
			System.out.println("03.query 준비: "+sql);
			
			rs=pstm.executeQuery();
			System.out.println("04.query 실행 및 리턴");
			
			while(rs.next()) {
				TaxiDto tmp= new TaxiDto(rs.getInt(1),rs.getInt(2),
						rs.getString(3),rs.getString(4),rs.getTimestamp(5),
						rs.getDate(6),rs.getString(7),rs.getInt(8));
				res.add(tmp);
			}
		} catch (SQLException e) {
			System.out.println("3/4단계 에러");
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstm);
			System.out.println("05. db 종료\n");
		}
		return res;
		
	}
	
	public TaxiDto selectOne(Connection con, int seq) {
		
		PreparedStatement pstm = null;
		ResultSet rs= null;
		TaxiDto res=null;
		
		String sql="SELECT * FROM TAXI WHERE TAXI_ID=?";
		
		
		try {
			pstm=con.prepareStatement(sql);
			pstm.setInt(1,seq);
			System.out.println("03.query 준비: "+sql);
			
			rs=pstm.executeQuery();
			System.out.println("04.query 실행 및 리턴");
			
			while(rs.next()) {
				res= new TaxiDto(rs.getInt(1),rs.getInt(2),
						rs.getString(3),rs.getString(4),rs.getTimestamp(5),
						rs.getDate(6),rs.getString(7),rs.getInt(8));
			}
		} catch (SQLException e) {
			System.out.println("3/4단계 오류");
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstm);
			System.out.println("05. db 종료\n");
		}
		return res;
		
	}
	
	public List<TaxiDto> myListSelect(Connection con, int myno){
		PreparedStatement pstm = null;
		ResultSet rs= null;
		List<TaxiDto> mylist = new ArrayList<TaxiDto>();
		String sql = " SELECT * FROM TAXI WHERE MEMBER_NO = ? ORDER BY TAXI_ID DESC ";
		
		try {
			pstm = con.prepareStatement(sql);
			pstm.setInt(1, myno);
			System.out.println("03.query 준비: "+sql);
			
			
			rs=pstm.executeQuery();
			System.out.println("04.query 실행 및 리턴");
			
			while(rs.next()) {
				TaxiDto tmp= new TaxiDto(rs.getInt(1),rs.getInt(2),
						rs.getString(3),rs.getString(4),rs.getTimestamp(5),
						rs.getDate(6),rs.getString(7),rs.getInt(8));
				mylist.add(tmp);
			}
		} catch (SQLException e) {
			System.out.println("3/4단계 에러");
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstm);
			System.out.println("05. db 종료\n");
		}
		return mylist;
		
	}
	
	public List<TaxiDto> driverListSelect(Connection con){
		PreparedStatement pstm = null;
		ResultSet rs= null;
		List<TaxiDto> mylist = new ArrayList<TaxiDto>();
		String sql = " SELECT * FROM TAXI ORDER BY TAXI_ID DESC ";
		
		try {
			pstm = con.prepareStatement(sql);
			System.out.println("03.query 준비: "+sql);
			
			
			rs=pstm.executeQuery();
			System.out.println("04.query 실행 및 리턴");
			
			while(rs.next()) {
				TaxiDto tmp= new TaxiDto(rs.getInt(1),rs.getInt(2),
						rs.getString(3),rs.getString(4),rs.getTimestamp(5),
						rs.getDate(6),rs.getString(7),rs.getInt(8));
				mylist.add(tmp);
			}
		} catch (SQLException e) {
			System.out.println("3/4단계 에러");
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstm);
			System.out.println("05. db 종료\n");
		}
		return mylist;
		
	}
	
	public boolean delete(Connection con, int seq) {
		PreparedStatement pstm=null;
		int res=0;
		
		String sql="DELETE FROM TAXI WHERE TAXI_ID=?";
		
		
		try {
			pstm=con.prepareStatement(sql);
			pstm.setInt(1, seq);
			System.out.println("03.query 준비: "+sql);
			
			res=pstm.executeUpdate();
			System.out.println("04.query 실행 및 리턴");
		
		} catch (SQLException e) {
			System.out.println("3/4단계 오류");
			e.printStackTrace();
		}finally {
			close(pstm);
			System.out.println("05.db 종료\n");
		}
		
		
		return (res>0)?true:false;
	}
	
	
	public boolean insert(Connection con, TaxiDto dto) {
		PreparedStatement pstm= null;
		int res=0;
		
		String sql="INSERT INTO TAXI VALUES(TAXI_SEQ.NEXTVAL,?,?,?,?,SYSDATE,'매칭중',NULL)";
	
		try {
			Time sqlTime = new Time(dto.getBook_date().getTime());
			//java.sql.Date sqlDate = new java.sql.Date(dto.getBook_date().getTime());
			//System.out.println(sqlDate);
			System.out.println(sqlTime);
			//Timestamp sqlDate = new Timestamp(dto.getBook_date().getTime());
			
			
			pstm=con.prepareStatement(sql);
			pstm.setInt(1, dto.getMember_no());
			pstm.setString(2,dto.getStart_addr());	
			pstm.setString(3,dto.getEnd_addr());	
			//pstm.setTimestamp(3, sqlDate);
			pstm.setTime(4, sqlTime);
			System.out.println("03. query 준비: "+ sql);	
				
			res=pstm.executeUpdate();	
			System.out.println("04. query 실행 및 리턴");	

		
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstm);
			System.out.println("05. db 종료\n");

		}
		
		
		return (res>0)?true:false;
	}
	
	public boolean update(Connection con, TaxiDto dto) {
		PreparedStatement pstm =null;
		int res=0;
		String sql="UPDATE TAXI SET START_ADDR=?, END_ADDR=?, BOOK_DATE=? WHERE TAXI_ID=? ";
		
		Time sqlTime = new Time(dto.getBook_date().getTime());
		try {
			
			
			pstm=con.prepareStatement(sql);
			pstm.setString(1, dto.getStart_addr());
			pstm.setString(2, dto.getEnd_addr());
			pstm.setTime(3, sqlTime);
			pstm.setInt(4, dto.gettaxi_id());
			System.out.println("03. query 준비: "+sql);
			
			res=pstm.executeUpdate();
			System.out.println("04.query 실행 및 리턴");
		} catch (SQLException e) {
			System.out.println("3/4 단계 오류");
			e.printStackTrace();
		}finally {
			close(pstm);
			close(con);
			System.out.println("05. db 종료\n");
		}
		return (res>0)?true:false;
	}
	
	public List<TaxiDto> applyAll(Connection con){
		
		PreparedStatement pstm=null;
		ResultSet rs= null;
		List<TaxiDto> res= new ArrayList<TaxiDto>();
		
		String sql="SELECT*FROM TAXI ORDER BY TAXI_ID DESC";
		
		String taxi_state=null;
		try {
			pstm=con.prepareStatement(sql);
			System.out.println("03.query 준비: "+sql);
			
			rs=pstm.executeQuery();
			System.out.println("04.query 실행 및 리턴");
			
			while(rs.next()) {
				TaxiDto tmp= new TaxiDto(rs.getInt(1),rs.getInt(2),
						rs.getString(3),rs.getString(4),rs.getTimestamp(5),
						rs.getDate(6),rs.getString(7),rs.getInt(8));
				res.add(tmp);
			}
		} catch (SQLException e) {
			System.out.println("3/4단계 에러");
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstm);
			System.out.println("05. db 종료\n");
		}
		return res;
		
	}
	
public MemberDto driverInfo(Connection con, int M_NO) {
		
		PreparedStatement pstm = null;
		ResultSet rs= null;
		MemberDto res=null;
		
		String sql="SELECT * FROM DURI_MEMBER WHERE M_NO=? ";
		
		
		try {
			pstm=con.prepareStatement(sql);
			pstm.setInt(1,M_NO);
			System.out.println("03.query 준비: "+sql);
			
			rs=pstm.executeQuery();
			System.out.println("04.query 실행 및 리턴");
			
			while(rs.next()) {
				res= new MemberDto(rs.getInt(1),rs.getString(2),
						rs.getString(3),rs.getString(4),rs.getString(5),
						rs.getString(6),rs.getString(7),rs.getDate(8),rs.getString(9),rs.getDate(10));
			}
		} catch (SQLException e) {
			System.out.println("3/4단계 오류");
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstm);
			System.out.println("05. db 종료\n");
		}
		return res;
		
	}
	
	
public boolean driverConfirm(Connection con, int taxi_id,int driver_no) {
	PreparedStatement pstm =null;
	int res=0;
	String sql="UPDATE TAXI SET TAXI_STATE='매칭완료' , DRIVER_NO=? WHERE TAXI_ID=? ";
	
	try {
		
		
		pstm=con.prepareStatement(sql);
		pstm.setInt(1, driver_no);
		pstm.setInt(2, taxi_id);
		System.out.println("03. query 준비: "+sql);
		
		res=pstm.executeUpdate();
		System.out.println("04.query 실행 및 리턴");
	} catch (SQLException e) {
		System.out.println("3/4 단계 오류");
		e.printStackTrace();
	}finally {
		close(pstm);
		System.out.println("05. db 종료\n");
	}
	return (res>0)?true:false;
}
	
public boolean driverCancel(Connection con, int taxi_id) {
	PreparedStatement pstm =null;
	int res=0;
	String sql="UPDATE TAXI SET TAXI_STATE='매칭중', DRIVER_NO = NULL WHERE TAXI_ID=? ";
	
	try {
		
		
		pstm=con.prepareStatement(sql);
		pstm.setInt(1, taxi_id);
		System.out.println("03. query 준비: "+sql);
		
		res=pstm.executeUpdate();
		System.out.println("04.query 실행 및 리턴");
	} catch (SQLException e) {
		System.out.println("3/4 단계 오류");
		e.printStackTrace();
	}finally {
		close(pstm);
		System.out.println("05. db 종료\n");
	}
	return (res>0)?true:false;
}
	
	
	
	
	
}
