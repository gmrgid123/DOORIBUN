package web.biz;

import static common.JDBCTemplate.close;
import static common.JDBCTemplate.commit;
import static common.JDBCTemplate.getConnection;

import java.sql.Connection;
import java.util.List;

import web.dao.MemberDao;
import web.dao.TaxiDao;
import web.dto.MemberDto;
import web.dto.TaxiDto;



public class TaxiBiz {

	
		private TaxiDao dao = new TaxiDao();
		
		public List<TaxiDto> selectAll(){
			Connection con = getConnection();
			List<TaxiDto> res=dao.selectAll(con);
			close(con);
			return res;
			
		}
		
		public TaxiDto selectOne(int seq) {
			Connection con = getConnection();
			TaxiDto dto = dao.selectOne(con, seq);
			close(con);
			
			return dto;
		}
		
		public List<TaxiDto> myListSelect(int myno){
			Connection con = getConnection();
			List<TaxiDto> mylist = dao.myListSelect(con, myno);
			close(con);
			
			return mylist;
		}
	
		public List<TaxiDto> driverListSelect(){
			Connection con = getConnection();
			List<TaxiDto> driverlist = dao.driverListSelect(con);
			close(con);
			
			return driverlist;
		}
		
		public boolean delete(int seq) {
			Connection con =getConnection();
			boolean res = dao.delete(con,seq);
			if(res) {
				commit(con);
			}
			close(con);
			return res;
		}
		
		public boolean insert(TaxiDto dto) {
			Connection con = getConnection();
			boolean res=dao.insert(con,dto);
			
			if(res) {
				commit(con);
			}
			
			return res;
		}
		
		public boolean update(TaxiDto dto) {
			Connection con= getConnection();
			boolean res = dao.update(con, dto);
			if(res) {
				commit(con);
			}
			close(con);
			return res;
		}
		
		public List<TaxiDto> applyAll(){
			Connection con = getConnection();
			List<TaxiDto> res=dao.applyAll(con);
			close(con);
			return res;
			
		}
		
		
		public boolean driverConfirm(int taxi_id, int driver_no) {
			Connection con= getConnection();
			boolean res = dao.driverConfirm(con, taxi_id, driver_no);
			if(res) {
				commit(con);
			}
			close(con);
			return res;
		}
		
		public boolean driverCancel(int taxi_id) {
			Connection con= getConnection();
			boolean res = dao.driverCancel(con, taxi_id);
			if(res) {
				commit(con);
			}
			close(con);
			return res;
		}
		
		
}
