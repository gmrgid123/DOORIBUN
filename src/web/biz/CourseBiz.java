package web.biz;

import web.dao.CourseDao;
import web.dto.CourseDto;

import static common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.List;

public class CourseBiz {
	private CourseDao dao = new CourseDao();
	
	public CourseDto selectOne(int course_id) {
		Connection con = getConnection();
		CourseDto res = dao.selectOne(con, course_id);
		close(con);
		
		return res;
	}
	
	public List<CourseDto> selectAll(){
		Connection con = getConnection();
		List<CourseDto> res = dao.selectAll(con);
		close(con);
		
		return res;
	}
	
	public List<CourseDto> mySelectPageList(int member_no , int p){
		Connection con = getConnection();
		List<CourseDto> res = dao.mySelectPageList(con, member_no, p);
		close(con);
		
		return res;
	}
	
	public int mySelectPageLength(int member_no) {
		Connection con = getConnection();
		int res = dao.mySelectPageLength(con,member_no);
		close(con);
		
		return res;
	}
	
	public boolean insert(CourseDto dto) {
		Connection con = getConnection();
		boolean res = dao.insert(con, dto);
		if(res) {
			commit(con);
		}
		close(con);
		
		return res;
	}
	
	public List<CourseDto> selectPageList(int p){
		Connection con = getConnection();
		List<CourseDto> res = dao.selectPageList(con, p);
		close(con);
		
		return res;
	}
	

	
	public int selectPageLength() {
		Connection con = getConnection();
		int res = dao.selectPageLength(con);
		close(con);
		
		return res;
	}
	
	public boolean CourseUpdate(CourseDto dto) {
		Connection con = getConnection();
		boolean res = dao.CourseUpdate(con, dto);
		if(res) {
			commit(con);
		}
		close(con);
		
		return res;
	}
	
	public boolean CourseDelete(int course_id) {
		Connection con = getConnection();
		boolean res = dao.CourseDelete(con, course_id);
		if(res) {
			commit(con);
		}
		close(con);
		
		return res;
	}
	
	public boolean HitUpdate(int course_id) {
		Connection con = getConnection();
		boolean res = dao.HitUpdate(con, course_id);
		if(res) {
			commit(con);
		}
		close(con);
		
		return res;
	}
	
	
	
	
}
