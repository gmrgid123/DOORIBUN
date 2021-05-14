package web.dao;

import static common.JDBCTemplate.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import web.dto.TourDto;

public class TourDao {
	
	public TourDto selectOne(Connection con, int tour_id) {
		PreparedStatement pstm = null;
		ResultSet rs = null;
		TourDto res = null;
		String sql = " SELECT * FROM TOUR WHERE TOUR_ID = ? ";
		
		try {
			pstm = con.prepareStatement(sql);
			pstm.setInt(1, tour_id);
			System.out.println("03.qeury 준비 : " + sql);
			
			rs = pstm.executeQuery();
			System.out.println("04. query 실행 및 리턴");
			
			while(rs.next()) {
				res = new TourDto(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), 
						rs.getDouble(5), rs.getDouble(6), rs.getString(7), rs.getString(8), rs.getInt(9));
			}
			
			
		} catch (SQLException e) {
			System.out.println("3/4단계 에러");
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstm);
			System.out.println("05.db 종료 \n");
		}
		
		return res;
	}
	
	public List<TourDto> selectAll(Connection con){
		PreparedStatement pstm = null;
		ResultSet rs = null;
		List<TourDto> res = new ArrayList<TourDto>();
		String sql = " SELECT * FROM TOUR ";
		
		try {
			pstm = con.prepareStatement(sql);
			System.out.println("03. query 준비 : " + sql);
			
			rs = pstm.executeQuery();
			System.out.println("04. query 실행 및 리턴");
			
			while(rs.next()) {
				TourDto tmp = new TourDto(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), 
						rs.getDouble(5), rs.getDouble(6), rs.getString(7), rs.getString(8), rs.getInt(9));
				res.add(tmp);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstm);
			System.out.println("05. db 종료\n");
		}
		
		return res;
		
	}
	
	
	
	
	public TourDto selectGangseoGu(Connection con, int num){
		PreparedStatement pstm = null;
		ResultSet rs = null;
		TourDto res = null;
		String sql = " SELECT * FROM TOUR WHERE TOUR_ID=? ";
		
		if(num == 7) {
			num = 1;
		}else if(num == 0) {
			num = 6;
		}
			
		try {
			pstm = con.prepareStatement(sql);
			pstm.setInt(1, num);
			System.out.println("query 준비");
			rs = pstm.executeQuery();
			System.out.println("query 실행 및 리턴");
			if(rs.next()) {
				res = new TourDto(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
						rs.getDouble(5), rs.getDouble(6), rs.getString(7), rs.getString(8), rs.getInt(9)); 
				
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstm);
			System.out.println("db 종료");
		}
		
		return res;
	}
	
	public TourDto selectGwanakGu(Connection con, int num) {
		PreparedStatement pstm = null;
		ResultSet rs = null;
		TourDto res = null;
		String sql = " SELECT * FROM TOUR WHERE TOUR_ID=? ";
		
		if(num > 8) {
			num = 7;
		}else if(num < 7) {
			num = 8;
		}
		
		try {
			pstm = con.prepareStatement(sql);
			pstm.setInt(1, num);
			
			rs = pstm.executeQuery();
			
			if(rs.next()) {
				res = new TourDto(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
						rs.getDouble(5), rs.getDouble(6), rs.getString(7), rs.getString(8), rs.getInt(9));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstm);
		}
		
		return res;
	}
	
	public TourDto selectSeongbukGu(Connection con, int num) {
		PreparedStatement pstm = null;
		ResultSet rs = null;
		TourDto res = null;
		String sql = " SELECT * FROM TOUR WHERE TOUR_ID=? ";
		
		if(num >= 12) {
			num = 9;
		}else if(num < 9) {
			num = 11;
		}
		
		try {
			pstm = con.prepareStatement(sql);
			pstm.setInt(1, num);
			
			rs = pstm.executeQuery();
			
			if(rs.next()) {
				res = new TourDto(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
						rs.getDouble(5), rs.getDouble(6), rs.getString(7), rs.getString(8), rs.getInt(9));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstm);
		}
		
		return res;
	}
	
	public TourDto selectEunpyeongGu(Connection con, int num) {
		PreparedStatement pstm = null;
		ResultSet rs = null;
		TourDto res = null;
		String sql = " SELECT * FROM TOUR WHERE TOUR_ID=? ";
		
		if(num > 13) {
			num = 12;
		}else if (num < 12) {
			num = 13;
		}
		
		try {
			pstm = con.prepareStatement(sql);
			pstm.setInt(1, num);
			
			rs = pstm.executeQuery();
			
			if(rs.next()) {
				res = new TourDto(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
						rs.getDouble(5), rs.getDouble(6), rs.getString(7), rs.getString(8), rs.getInt(9));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstm);
		}
		
		return res;	
	}
	
	public TourDto selectJungnangGu(Connection con, int num) {
		PreparedStatement pstm = null;
		ResultSet rs = null;
		TourDto res = null;
		String sql = " SELECT * FROM TOUR WHERE TOUR_ID=? ";
		
		if(num > 17) {
			num = 14;
		}else if(num < 14) {
			num = 17;
		}
		
		try {
			pstm = con.prepareStatement(sql);
			pstm.setInt(1, num);
			
			rs = pstm.executeQuery();
			
			if(rs.next()) {
				res = new TourDto(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
						rs.getDouble(5), rs.getDouble(6), rs.getString(7), rs.getString(8), rs.getInt(9));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstm);
		}
		
		return res;			
	}
	
	public TourDto selectSeochoGu(Connection con, int num) {
		PreparedStatement pstm = null;
		ResultSet rs = null;
		TourDto res = null;
		String sql = " SELECT * FROM TOUR WHERE TOUR_ID=? ";
		
		if(num > 22) {
			num = 18;
		}else if(num < 18) {
			num = 22;
		}
		
		try {
			pstm = con.prepareStatement(sql);
			pstm.setInt(1, num);
			
			rs = pstm.executeQuery();
			
			if(rs.next()) {
				res = new TourDto(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
						rs.getDouble(5), rs.getDouble(6), rs.getString(7), rs.getString(8), rs.getInt(9));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstm);
		}
		
		return res;				
	}
	
}
