package web.dao;

import java.sql.Connection;

import web.dto.MemberDto;

public interface MemberDaoInterface {
	
	/**
	 * 로그인시 사용되는 함수
	 * @param m_id
	 * 로그인하는 사용자의 ID
	 * @param m_pw
	 * 로그인하는 사용자의 PW
	 * @return
	 * 로그인한 결과가 정상인 경우 1
	 */
	public int login(String m_id, String m_pw);
	public int register(String m_id, String m_pw, String m_name, String m_email, String m_phone, String m_type);
	public int registercheck(String m_id);
	public MemberDto getMember(String m_id);
	public int updateMember(String m_id, String m_pw, String m_email, String m_phone);
	public String getMemberId(int m_no);
}
