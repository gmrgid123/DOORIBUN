package web.biz;

import java.sql.Connection;
import java.util.List;

import web.dao.TourDao;
import web.dto.TourDto;

import static common.JDBCTemplate.*;

public class TourBiz {
	
	private TourDao dao = new TourDao();
	
	public TourDto selectOne(int tour_id) {
		Connection con = getConnection();
		TourDto dto = dao.selectOne(con, tour_id);
		close(con);
		return dto;
	}
	
	public List<TourDto> selectAll(){
		Connection con = getConnection();
		List<TourDto> res = dao.selectAll(con);
		close(con);
		return res;
	}
	
	
	public TourDto selectGangseoGu(int num){
		Connection con = getConnection();
		TourDto res = dao.selectGangseoGu(con, num);
		
		close(con);
		
		return res;
	}
	public TourDto selectGwanakGu(int num){
		Connection con = getConnection();
		TourDto res = dao.selectGwanakGu(con, num);
		
		close(con);
		
		return res;
	}
	
	public TourDto selectSeongbukGu(int num){
		Connection con = getConnection();
		TourDto res = dao.selectSeongbukGu(con, num);
		
		close(con);

		return res;
	}
	
	public TourDto selectEunpyeongGu(int num){
		Connection con = getConnection();
		TourDto res = dao.selectEunpyeongGu(con, num);
		
		close(con);

		return res;
	}
	
	public TourDto selectJungnangGu(int num){
		Connection con = getConnection();
		TourDto res = dao.selectJungnangGu(con, num);
		
		close(con);

		return res;
	}
	
	public TourDto selectSeochoGu(int num){
		Connection con = getConnection();
		TourDto res = dao.selectSeochoGu(con, num);
		
		close(con);

		return res;	
	}
	
	
	
}
