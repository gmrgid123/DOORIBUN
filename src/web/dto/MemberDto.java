package web.dto;

import java.util.Date;

public class MemberDto {
	private int m_no;
	private String m_id;
	private String m_pw;
	private String m_name;
	private String m_email;
	private String m_phone;
	private String m_type;
	private Date m_regdate;
	private String m_enabled;
	private Date m_deldate;
	
	
	
	public MemberDto() {
		super();
	}



	public MemberDto(int m_no, String m_id, String m_pw, String m_name, String m_email, String m_phone, String m_type,
			Date m_regdate, String m_enabled, Date m_deldate) {
		super();
		this.m_no = m_no;
		this.m_id = m_id;
		this.m_pw = m_pw;
		this.m_name = m_name;
		this.m_email = m_email;
		this.m_phone = m_phone;
		this.m_type = m_type;
		this.m_regdate = m_regdate;
		this.m_enabled = m_enabled;
		this.m_deldate = m_deldate;
	}



	public int getM_no() {
		return m_no;
	}



	public void setM_no(int m_no) {
		this.m_no = m_no;
	}



	public String getM_id() {
		return m_id;
	}



	public void setM_id(String m_id) {
		this.m_id = m_id;
	}



	public String getM_pw() {
		return m_pw;
	}



	public void setM_pw(String m_pw) {
		this.m_pw = m_pw;
	}



	public String getM_name() {
		return m_name;
	}



	public void setM_name(String m_name) {
		this.m_name = m_name;
	}



	public String getM_email() {
		return m_email;
	}



	public void setM_email(String m_email) {
		this.m_email = m_email;
	}



	public String getM_phone() {
		return m_phone;
	}



	public void setM_phone(String m_phone) {
		this.m_phone = m_phone;
	}



	public String getM_type() {
		return m_type;
	}



	public void setM_type(String m_type) {
		this.m_type = m_type;
	}



	public Date getM_regdate() {
		return m_regdate;
	}



	public void setM_regdate(Date m_regdate) {
		this.m_regdate = m_regdate;
	}



	public String getM_enabled() {
		return m_enabled;
	}



	public void setM_enabled(String m_enabled) {
		this.m_enabled = m_enabled;
	}



	public Date getM_deldate() {
		return m_deldate;
	}



	public void setM_deldate(Date m_deldate) {
		this.m_deldate = m_deldate;
	}

	@Override
	public String toString() {
		return "MemberDto [m_no=" + m_no + ", m_id=" + m_id + ", m_pw=" + m_pw + ", m_name=" + m_name + ", m_email="
				+ m_email + ", m_phone=" + m_phone + ", m_type=" + m_type + ", m_regdate=" + m_regdate + ", m_enabled="
				+ m_enabled + ", m_deldate=" + m_deldate + "]";
	}
	
	
	
	
}
