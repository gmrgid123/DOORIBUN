package web.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import common.JDBCTemplate;
import web.dto.CommunityDto;

public class CommunityDao extends JDBCTemplate {
	
	//selectAll
	public List<CommunityDto> selectAll(){
		Connection con = getConnection();
		Statement stmt = null;
		ResultSet rs = null;
		List<CommunityDto> res = new ArrayList<CommunityDto>();
		
		String sql = " SELECT * FROM COMMUNITY ORDER BY COMMUNITY_ID DESC ";
		
		try {
			stmt = con.createStatement();
			System.out.println("03.query 준비: " +sql);
			
			rs = stmt.executeQuery(sql);
			System.out.println("04. query 실행 및 리턴");
			
			while(rs.next()) {
				CommunityDto tmp = new CommunityDto();
				tmp.setCommunity_id(rs.getInt(1));
				tmp.setMember_no(rs.getInt(2));
				tmp.setCommunity_title(rs.getString(3));
				tmp.setCommunity_content(rs.getString(4));
				tmp.setCommunity_regdate(rs.getDate(5));
				tmp.setCommunity_hit(rs.getInt(6));
				tmp.setCommunity_oringin_filename(rs.getString(7));
				
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
	public int insert(CommunityDto dto) {
		Connection con = getConnection();
		PreparedStatement pstm =null;
		int res = 0;
			
		String sql = "INSERT INTO COMMUNITY VALUES(COMMUNITY_SEQ.NEXTVAL,?,?,?,SYSDATE,0,?)";
			
		try {
			pstm = con.prepareStatement(sql);
			pstm.setLong(1, dto.getMember_no());
			pstm.setString(2, dto.getCommunity_title());
			pstm.setString(3, dto.getCommunity_content());
			pstm.setString(4, dto.getCommunity_oringin_filename());
			System.out.println("03. query 준비: " + sql);
			System.out.println(dto.getCommunity_title());
			System.out.println(dto.getCommunity_content());
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
	public CommunityDto selectOne(int community_id) {
		Connection con = getConnection();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		CommunityDto res = new CommunityDto();
		
		String sql = "SELECT * FROM COMMUNITY WHERE COMMUNITY_ID=?";
		
		try {
			pstm = con.prepareStatement(sql);
			pstm.setInt(1, community_id);
			System.out.println("03. 쿼리 준비" + sql);
			
			rs = pstm.executeQuery();
			System.out.println("04. 쿼리 실행 및 리턴");
			
			while(rs.next()) {
				res.setCommunity_id(rs.getInt(1));
				res.setMember_no(rs.getInt(2));
				res.setCommunity_title(rs.getString(3));
				res.setCommunity_content(rs.getString(4));
				res.setCommunity_regdate(rs.getDate(5));
				res.setCommunity_hit(rs.getInt(6));
				res.setCommunity_oringin_filename(rs.getString(7));
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
	public int update(CommunityDto dto) {
		Connection con = getConnection();
		PreparedStatement pstm = null;
		int res = 0;
		
		String sql =" UPDATE COMMUNITY SET COMMUNITY_TITLE=?, COMMUNITY_CONTENT=?, COMMUNITY_ORINGIN_FILENAME=? WHERE COMMUNITY_ID=? ";
		
		try {
			pstm = con.prepareStatement(sql);
			pstm.setString(1, dto.getCommunity_title());
			pstm.setString(2, dto.getCommunity_content());
			pstm.setString(3, dto.getCommunity_oringin_filename());
			pstm.setInt(4, dto.getCommunity_id());
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
		
		String sql = " DELETE FROM COMMUNITY WHERE COMMUNITY_ID=? ";
		
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
	    
	    String sql = " UPDATE COMMUNITY SET COMMUNITY_HIT = COMMUNITY_HIT+1 WHERE COMMUNITY_ID=? ";
	    
	    
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
		public List<CommunityDto> getList(int startRow, int endRow) {
			 Connection con = getConnection();
			 PreparedStatement pstm = null;
			 ResultSet rs = null;
			
			// 페이징 처리를 위한 sql / 인라인뷰, rownum 사용
			String sql = " SELECT * FROM (SELECT ROW_NUMBER() OVER(order by COMMUNITY_ID DESC) AS NUM, C.* FROM COMMUNITY C) WHERE NUM BETWEEN ? AND ? ";
			List<CommunityDto> list = null;
			
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
					CommunityDto tmp = new CommunityDto();
					tmp.setCommunity_id(rs.getInt(2));
					tmp.setMember_no(rs.getInt(3));
					tmp.setCommunity_title(rs.getString(4));
					tmp.setCommunity_content(rs.getString(5));
					tmp.setCommunity_regdate(rs.getDate(6));
					tmp.setCommunity_hit(rs.getInt(7));
					tmp.setCommunity_oringin_filename(rs.getString(8));
				
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
			
			String sql = " SELECT COUNT(*) FROM COMMUNITY ";
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
		
		
		//페이징
		// 리스트 페이지에 보여줄 로직(페이징 처리)
			public List<CommunityDto> getMyList(int startRow, int endRow, int m_no) {
				 Connection con = getConnection();
				 PreparedStatement pstm = null;
				 ResultSet rs = null;
				
				// 페이징 처리를 위한 sql / 인라인뷰, rownum 사용
				String sql = " SELECT * FROM (SELECT ROW_NUMBER() OVER(order by COMMUNITY_ID DESC) AS NUM, C.* FROM COMMUNITY C WHERE MEMBER_NO = ?) WHERE NUM BETWEEN ? AND ? ";
				List<CommunityDto> list = null;
				
				try {
					pstm = con.prepareStatement(sql); // sql 정의
					pstm.setInt(1, m_no);
					pstm.setInt(2, startRow);
					pstm.setInt(3, endRow);
					System.out.println("03. 쿼리 준비: " +sql);
					
					rs = pstm.executeQuery();
					System.out.println("04. 쿼리 실행 및 리턴: " );
					
					list = new ArrayList<>(); // list 객체 생성
					while (rs.next()) {
						// 반복할 때마다 ExboardDTO 객체를 생성 및 데이터 저장
						CommunityDto tmp = new CommunityDto();
						tmp.setCommunity_id(rs.getInt(2));
						tmp.setMember_no(rs.getInt(3));
						tmp.setCommunity_title(rs.getString(4));
						tmp.setCommunity_content(rs.getString(5));
						tmp.setCommunity_regdate(rs.getDate(6));
						tmp.setCommunity_hit(rs.getInt(7));
						tmp.setCommunity_oringin_filename(rs.getString(8));
					
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
			public int getMyListCount(int m_no){
				Connection con = getConnection();
				PreparedStatement pstm = null;
				int count = 0;
				ResultSet rs = null;
				
				String sql = " SELECT COUNT(*) FROM COMMUNITY WHERE MEMBER_NO = ? ";
				try {
					pstm = con.prepareStatement(sql);
					pstm.setInt(1, m_no);
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
