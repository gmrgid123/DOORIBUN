package web.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import common.JDBCTemplate;
import web.dto.QnaDto;

public class QnaDao extends JDBCTemplate {
	
	//selectAll
	public List<QnaDto> selectAll(){
		Connection con = getConnection();
		Statement stmt = null;
		ResultSet rs = null;
		List<QnaDto> res = new ArrayList<QnaDto>();
		
		String sql = " SELECT * FROM QNA ORDER BY QNA_ID DESC ";
		
		try {
			stmt = con.createStatement();
			System.out.println("03.query 준비: " +sql);
			
			rs = stmt.executeQuery(sql);
			System.out.println("04. query 실행 및 리턴");
			
			while(rs.next()) {
				QnaDto tmp = new QnaDto();
				tmp.setQna_id(rs.getInt(1));
				tmp.setMember_no(rs.getInt(2));
				tmp.setQna_title(rs.getString(3));
				tmp.setQna_content(rs.getString(4));
				tmp.setQna_regdate(rs.getDate(5));
				tmp.setQna_hit(rs.getInt(6));
				tmp.setQna_oringin_filename(rs.getString(7));
				
				res.add(tmp);
			}
			
		} catch (SQLException e) {
			System.out.println("3/4 단계 오류");
			e.printStackTrace();
		} finally {
			close(rs);
			close(stmt);
			close(con);
			System.out.println("05. db 종료 \n");
		}
		
		return res;
	}
	
	//글 작성 insert
	public int insert(QnaDto dto) {
		Connection con = getConnection();
		PreparedStatement pstm =null;
		int res = 0;
			
		String sql = "INSERT INTO QNA VALUES(QNA_SEQ.NEXTVAL,?,?,?,SYSDATE,0,?)";
			
		try {
			pstm = con.prepareStatement(sql);
			pstm.setLong(1, dto.getMember_no());
			pstm.setString(2, dto.getQna_title());
			pstm.setString(3, dto.getQna_content());
			pstm.setString(4, dto.getQna_oringin_filename());
			System.out.println("03. query 준비: " + sql);
			
			System.out.println(dto.getQna_title());
			System.out.println(dto.getQna_content());
			res = pstm.executeUpdate();
			System.out.println("04. query 실행 및 리턴");
				
			if(res>0) {
				commit(con);
			}
				
		} catch (SQLException e) {
			System.out.println("3/4 단계 오류");
			e.printStackTrace();
		} finally {
			close(pstm);
			close(con);
			System.out.println("05. db 종료 \n");
		}
			
			return res;
		}
	
	//selectOne
	public QnaDto selectOne(int qna_id) {
		Connection con = getConnection();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		QnaDto res = new QnaDto();
		
		String sql = "SELECT * FROM QNA WHERE QNA_ID=?";
		
		try {
			pstm = con.prepareStatement(sql);
			pstm.setInt(1, qna_id);
			System.out.println("03. 쿼리 준비" + sql);
			
			rs = pstm.executeQuery();
			System.out.println("04. 쿼리 실행 및 리턴");
			
			while(rs.next()) {
				res.setQna_id(rs.getInt(1));
				res.setMember_no(rs.getInt(2));
				res.setQna_title(rs.getString(3));
				res.setQna_content(rs.getString(4));
				res.setQna_regdate(rs.getDate(5));
				res.setQna_hit(rs.getInt(6));
				res.setQna_oringin_filename(rs.getString(7));
			}
			
		} catch (SQLException e) {
			System.out.println("3/4 단계 오류");
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstm);
			close(con);
			System.out.println("05. db 종료 \n");
		}
		
		
		return res;
	} 
	
	//수정 페이지
	public int update(QnaDto dto) {
		Connection con = getConnection();
		PreparedStatement pstm = null;
		int res = 0;
		
		String sql =" UPDATE QNA SET QNA_TITLE=?, QNA_CONTENT=?, QNA_ORINGIN_FILENAME=? WHERE QNA_ID=? ";
		
		try {
			pstm = con.prepareStatement(sql);
			pstm.setString(1, dto.getQna_title());
			pstm.setString(2, dto.getQna_content());
			pstm.setString(3, dto.getQna_oringin_filename());
			pstm.setInt(4, dto.getQna_id());
			System.out.println("03. 쿼리 준비: " +sql);
			
			res = pstm.executeUpdate();
			System.out.println("04. 쿼리 실행 및 리턴");
			
			if(res>0) {
				commit(con);
			}
			
			
		} catch (SQLException e) {
			System.out.println("3/4 단계 오류");
			e.printStackTrace();
		} finally {
			close(pstm);
			close(con);
			System.out.println("05. db 종료 \n");
		}
		
		
		return res;
	}
	
	//삭제 페이지
	public int delete(String[] seq) {
		Connection con = getConnection();
		PreparedStatement pstm = null;
		int res = 0;
		int[] cnt = null;
		
		String sql = " DELETE FROM QNA WHERE QNA_ID=? ";
		
		try {
			pstm = con.prepareStatement(sql);
			
			for(int i = 0; i<seq.length; i++) {
				pstm.setString(1, seq[i]);
				pstm.addBatch();
				System.out.println("03. 쿼리 준비: " + sql + "[seq= " + seq[i] + "]");
			}
			
			cnt = pstm.executeBatch();
			System.out.println("04. 쿼리 실행 및 리턴");
			
			for(int i=0; i <cnt.length; i++) {
				if(cnt[i]==-2) {
					res++;
				}
			}
			
			if(seq.length == res) {
				commit(con);
			} else {
				rollback(con);
			}
			
			
		} catch (SQLException e) {
			System.out.println("3/4 단계 에러");
			e.printStackTrace();
		} finally {
			close(pstm);
			close(con);
			System.out.println("05. db 종료 \n");
		}
		
		return res;
	}
	
	//조회 수 증가 "업데이트"
	public int count(int id) {
	    Connection con = getConnection();
	    PreparedStatement pstm = null;
	    int res= 0;
	    
	    String sql = " UPDATE QNA SET QNA_HIT = QNA_HIT+1 WHERE QNA_ID=? ";
	    
	    
	    try {
	    	pstm = con.prepareStatement(sql);
	    	
	    	pstm.setInt(1, id);
	    	
	    	System.out.println("03. 쿼리 준비: " +sql);
	    	
	    	
	        pstm.executeUpdate();
	    	System.out.println("04. 쿼리 실행 및 리턴");
			
	    	
			if(res>0) {
				commit(con);
			}
	    	
	    } catch (SQLException e) {
	    	System.out.println("3/4단계 오류");
	        e.printStackTrace();
	    } finally {
	    	close(pstm);
			close(con);
			System.out.println("05.db 종료 /n");
	    }
	    
	    return res;
	}
	
	//페이징
	// 리스트 페이지에 보여줄 로직(페이징 처리)
	// PreparedStatement(오라클 자주 사용하는 애들을 관리 차원에서 한 곳에 묶어서 하기 때문에 쿼리 속도가 빠름 권장) 에서는 sql문 order by를 무조건 마지막에 처리하니까 Statement로 처리해줘야 함
		public List<QnaDto> getList(int startRow, int endRow) {
			 Connection con = getConnection();
			 PreparedStatement pstm = null;
			 ResultSet rs = null;
			
			// 페이징 처리를 위한 sql / 인라인뷰, rownum 사용
			String sql = " SELECT * FROM (SELECT ROW_NUMBER() OVER(order by QNA_ID DESC) AS NUM, Q.* FROM QNA Q) WHERE NUM BETWEEN ? AND ? ";
				
			List<QnaDto> list = null;
			try {
				pstm = con.prepareStatement(sql); // sql 정의
				pstm.setInt(1, startRow);
				pstm.setInt(2, endRow);
				System.out.println("03. 쿼리 준비: " +sql);
				
				rs = pstm.executeQuery();
				System.out.println("04. 쿼리 실행 및 리턴: " );
				
				
				list = new ArrayList<>(); // list 객체 생성
				while (rs.next()) {
					// 반복할 때마다 ExboardDTO 객체를 생성 및 데이터 저장
					QnaDto tmp = new QnaDto();
					tmp.setQna_id(rs.getInt(2));
					tmp.setMember_no(rs.getInt(3));
					tmp.setQna_title(rs.getString(4));
					tmp.setQna_content(rs.getString(5));
					tmp.setQna_regdate(rs.getDate(6));
					tmp.setQna_hit(rs.getInt(7));
					tmp.setQna_oringin_filename(rs.getString(8));
					System.out.println("list :"+tmp.getQna_id());
					list.add(tmp); // list에 0번 인덱스부터 tmp 객체의 참조값을 저장
				} 
				
			} catch (Exception e) {
				System.out.println("05. db 종료\n");
				e.printStackTrace();
			} finally {
				close(pstm);
				close(con); // DB 연결 종료 / Connection 반환
			}
			return list; // list 반환
		}
	

		// 총 레코드 수 구하는 로직
		public int getCount(){
			Connection con = getConnection();
			PreparedStatement pstm = null;
			int count = 0;
			ResultSet rs = null;
			
			String sql = " select count(*) from QNA ";
			try {
				pstm = con.prepareStatement(sql);
				System.out.println("03. 쿼리 준비: " +sql);
				
				rs = pstm.executeQuery();
				System.out.println("04. 쿼리 실행 및 리턴: ");
				
				if(rs.next()){
					count = rs.getInt(1);
				}
			} catch (Exception e) {
				System.out.println("05. db 종료\n");
				e.printStackTrace();
			} finally {
				close(pstm);
				close(con); 
			}
			return count; // 총 레코드 수 리턴
		}
	
	
	
}

