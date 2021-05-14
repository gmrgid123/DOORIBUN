package web.dao;

import static common.JDBCTemplate.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import common.JDBCTemplate;
import web.dto.CourseDto;
import web.dto.MemberDto;

public class MemberDao extends JDBCTemplate implements MemberDaoInterface {
	//dao는 어디서든 쓸수 있음. mvc구조이기 때문에 컨토롤러에서 쓰는게 좋다. 굳이 컨트롤러를 쓴느것은 mvc모델형태에 맞춰서 데이터를 넘겨주고 받고 하기위해서
	// 그래서 컨트롤러만 모델부분을 신경쓰면 되기 때문에 뷰는 그냥 컨트롤러에서 주는 데이터만 체크하고 보여주면 된다.
	
	// 로그인 기능 구현
	public int login(String m_id, String m_pw) {
		Connection con = getConnection();
		PreparedStatement pstm = null;
		ResultSet rs = null;

		String sql = "SELECT M_PW FROM DURI_MEMBER WHERE M_ID=? ";

		try {
			pstm = con.prepareStatement(sql);
			pstm.setString(1, m_id);
			rs = pstm.executeQuery();

			System.out.println(rs.getRow());
			System.out.println(m_id);

			if (rs.next()) {
				if (rs.getString(1).equals(m_pw)) {
					close(con);
					close(rs);
					close(pstm);
					return 1; // 가지고 온 패스워드가 일치하면 로그인 성공
				} else {
					close(con);
					close(rs);
					close(pstm);
					return 0; // 비밀번호 불일치
				}
			}
			return -1; // ?값을 넣었을때 아이디가 없으면 실제 존재하지 않는 회원이라 결과값이 나오지 않아 이게 실행됨

		} catch (SQLException e) {

			e.printStackTrace();
		}

		return -2; // 데이터베이스 오류
	}

	// 회원가입 입력
	public int register(String m_id, String m_pw, String m_name, String m_email, String m_phone, String m_type) { // F3누르기
		Connection con = getConnection();
		PreparedStatement pstm = null;
		ResultSet rs = null;

		String sql = "INSERT INTO DURI_MEMBER VALUES (DURI_MEMBER_SEQ.NEXTVAL,?,?,?,?,?,?, SYSDATE, 'Y' , DEFAULT)";

		try {
			pstm = con.prepareStatement(sql);
			pstm.setString(1, m_id);
			pstm.setString(2, m_pw);
			pstm.setString(3, m_name);
			pstm.setString(4, m_email);
			pstm.setString(5, m_phone);
			pstm.setString(6, m_type);

			System.out.println("id : " + m_id + ", pw : " + m_pw + ",name : " + m_name + ",email : " + m_email
					+ ",phone : " + m_phone + ", type : " + m_type);

			int res = pstm.executeUpdate();
			commit(con);
			return res; // 커밋을 해야되니깐 res 리절트를 받아서 커밋이 된 다음에 리턴해줘야 작동.

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				close(con);
				if (rs != null)
					close(rs);
				if (pstm != null)
					close(pstm);

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return -3;
	}

	// 회원가입 아이디 체크
	public int registercheck(String m_id) { // ctrl+g 사용하는 얘가 있는지
		Connection con = getConnection();
		PreparedStatement pstm = null;
		ResultSet rs = null;

		String sql = "SELECT * FROM DURI_MEMBER WHERE M_ID=?";

		try {
			pstm = con.prepareStatement(sql);
			pstm.setString(1, m_id);
			rs = pstm.executeQuery();
			System.out.println(sql);
			System.out.println(rs.getRow());

			if (rs.next() || m_id.equals("")) {
				return 0; // 이미 존재하는 회원
			} else {
				return 1; // 가입 가능한 회원 아이디
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {

				if (rs != null)
					close(rs);
				if (pstm != null)
					close(pstm);
				close(con);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return -1; // 데이터베이스 오류
	}

	// 회원정보 셀렉트 ... 이렇게 가져온 정보를 세션에 담아야한다. 회원정보가 필요하기 때문에
	// String sql ="SELECT * FROM DURI_MEMBER WHERE M_ID=? ";

	// 회원의 정보를 dto를 통해 넘기는 함수
	public MemberDto getMember(String m_id) {
		Connection con = getConnection();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM DURI_MEMBER WHERE M_ID=? ";
		MemberDto dto = null;

		try {

			pstm = con.prepareStatement(sql);
			pstm.setString(1, m_id);
			rs = pstm.executeQuery();

			if (rs.next()) {
				dto = new MemberDto();
				dto.setM_no(rs.getInt("m_no"));
				dto.setM_id(rs.getString("m_id"));
				dto.setM_pw(rs.getString("m_pw"));
				dto.setM_name(rs.getString("m_name"));
				dto.setM_email(rs.getString("m_email"));
				dto.setM_phone(rs.getString("m_phone"));
				dto.setM_type(rs.getString("m_type"));
				dto.setM_regdate(rs.getDate("m_regdate"));
				dto.setM_enabled(rs.getString("m_enabled"));
				dto.setM_deldate(rs.getDate("m_deldate"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				close(rs);
				close(pstm);
				close(con);
			} catch (Exception e2) {
			}
		}
		return dto;
	}

	public MemberDto getMember(int m_no) {
		Connection con = getConnection();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM DURI_MEMBER WHERE M_NO=? ";
		MemberDto dto = null;

		try {

			pstm = con.prepareStatement(sql);
			pstm.setInt(1,  m_no);
			rs = pstm.executeQuery();

			if (rs.next()) {
				dto = new MemberDto();
				dto.setM_no(rs.getInt("m_no"));
				dto.setM_id(rs.getString("m_id"));
				dto.setM_pw(rs.getString("m_pw"));
				dto.setM_name(rs.getString("m_name"));
				dto.setM_email(rs.getString("m_email"));
				dto.setM_phone(rs.getString("m_phone"));
				dto.setM_type(rs.getString("m_type"));
				dto.setM_regdate(rs.getDate("m_regdate"));
				dto.setM_enabled(rs.getString("m_enabled"));
				dto.setM_deldate(rs.getDate("m_deldate"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				close(rs);
				close(pstm);
				close(con);
			} catch (Exception e2) {
			}
		}
		return dto;
	}

	// 회원의 정보를 수정하는 함수
	public int updateMember(String m_id, String m_pw, String m_email, String m_phone) {

		Connection con = getConnection();
		PreparedStatement pstm = null;
		int result = -4;

		String sql = "UPDATE DURI_MEMBER SET M_PW=?, M_EMAIL=?, M_PHONE=? WHERE M_ID=?";

		try {

			pstm = con.prepareStatement(sql);
			pstm.setString(1, m_pw);
			pstm.setString(2, m_email);
			pstm.setString(3, m_phone);
			pstm.setString(4, m_id);
			result = pstm.executeUpdate();
			
			if(result != 1) {
				result = -4;
			} //에러페이지에서는 제대로 안될때 -4만 넘어감
			
		} catch (Exception e) {
			e.printStackTrace();
			
		} finally {
			try {
				close(pstm);
				close(con);
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return result;
		
	}
	
	public String getMemberId(int m_no) {
		Connection con = getConnection();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		String res = "";
		String sql = " SELECT M_ID FROM DURI_MEMBER WHERE M_NO = ? ";
		
		try {
			pstm = con.prepareStatement(sql);
			pstm.setInt(1, m_no);
			System.out.println("03. query 준비 : " + sql);
			
			rs = pstm.executeQuery();
			System.out.println("04. query 실행 및 리턴");
			
			while(rs.next()) {
				res = rs.getString(1);
			}
			
		} catch (SQLException e) {
			System.out.println("3/4단계 에러");
			e.printStackTrace();
		} finally {
			close(con);
			close(rs);
			close(pstm);
			System.out.println("05. db 종료\n");
		}
		
		return res;
	}
	
	public int phoneCheck(String m_phone) {
		Connection con = getConnection();
		PreparedStatement pstm = null;
		ResultSet rs = null;

		String sql = "SELECT * FROM DURI_MEMBER WHERE M_PHONE=?";

		try {
			pstm = con.prepareStatement(sql);
			pstm.setString(1, m_phone);
			rs = pstm.executeQuery();
			System.out.println(sql);
			System.out.println(rs.getRow());

			if (rs.next() ) {
				return 0; // 이미 존재하는 번호
			} else {
				return 1; // 가입 가능한 회원 번호
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {

				if (rs != null)
					close(rs);
				if (pstm != null)
					close(pstm);
				close(con);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return -1; // 데이터베이스 오류
		
	}
	
	
	public int phoneCheck(String m_phone,int m_no) {
		Connection con = getConnection();
		PreparedStatement pstm = null;
		ResultSet rs = null;

		String sql = "SELECT * FROM DURI_MEMBER WHERE M_PHONE=? AND M_NO != ? ";

		try {
			pstm = con.prepareStatement(sql);
			pstm.setString(1, m_phone);
			pstm.setInt(2, m_no);
			rs = pstm.executeQuery();
			System.out.println(sql);
			System.out.println(rs.getRow());

			if (rs.next() ) {
				return 0; // 이미 존재하는 번호
			} else {
				return 1; // 가입 가능한 회원 번호
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {

				if (rs != null)
					close(rs);
				if (pstm != null)
					close(pstm);
				close(con);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return -1; // 데이터베이스 오류
		
	}
	
	public boolean secession(int m_no) {
		Connection con = getConnection();
		PreparedStatement pstm = null;
		int res = 0;
		String sql = " UPDATE DURI_MEMBER SET M_ENABLED = 'N', M_DELDATE = SYSDATE WHERE M_NO = ? ";
		
		try {
			pstm = con.prepareStatement(sql);
			pstm.setInt(1, m_no);
			
			res = pstm.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(con); close(pstm);
		}
		
		return (res>0)?true:false;
		
	}
	

}
