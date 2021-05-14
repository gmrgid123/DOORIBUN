package web.dto;

import java.util.Date;


public class NoticeDto {
	private int notice_id;
	private int member_no;
	private String notice_title;
	private String notice_content;
	private Date notice_regdate;
	private int notice_hit;
	private String oringin_filename;

	public NoticeDto() {
		super();
	}


	public NoticeDto(int notice_id, int member_no, String notice_title, String notice_content, Date notice_regdate,
			int notice_hit, String oringin_filename) {
		super();
		this.notice_id = notice_id;
		this.member_no = member_no;
		this.notice_title = notice_title;
		this.notice_content = notice_content;
		this.notice_regdate = notice_regdate;
		this.notice_hit = notice_hit;
		this.oringin_filename = oringin_filename;
	}

	//update
	public NoticeDto(int notice_id, String notice_title, String notice_content) {
		super();
		this.notice_id = notice_id;
		this.notice_title = notice_title;
		this.notice_content = notice_content;
	}

	public int getNotice_id() {
		return notice_id;
	}

	public void setNotice_id(int notice_id) {
		this.notice_id = notice_id;
	}

	public int getMember_no() {
		return member_no;
	}

	public void setMember_no(int member_no) {
		this.member_no = member_no;
	}

	public String getNotice_title() {
		return notice_title;
	}

	public void setNotice_title(String notice_title) {
		this.notice_title = notice_title;
	}

	public String getNotice_content() {
		return notice_content;
	}

	public void setNotice_content(String notice_content) {
		this.notice_content = notice_content;
	}

	public Date getNotice_regdate() {
		return notice_regdate;
	}

	public void setNotice_regdate(Date notice_regdate) {
		this.notice_regdate = notice_regdate;
	}

	public int getNotice_hit() {
		return notice_hit;
	}

	public void setNotice_hit(int notice_hit) {
		this.notice_hit = notice_hit;
	}


	public String getOringin_filename() {
		return oringin_filename;
	}


	public void setOringin_filename(String oringin_filename) {
		this.oringin_filename = oringin_filename;
	}


	@Override
	public String toString() {
		return "NoticeDto [notice_id=" + notice_id + ", member_no=" + member_no + ", notice_title=" + notice_title
				+ ", notice_content=" + notice_content + ", notice_regdate=" + notice_regdate + ", notice_hit="
				+ notice_hit + ", oringin_filename=" + oringin_filename + "]";
	}
	
	
	
}
