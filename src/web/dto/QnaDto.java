package web.dto;

import java.util.Date;

public class QnaDto {
	private int qna_id;
	private int member_no;
	private String qna_title;
	private String qna_content;
	private Date qna_regdate;
	private int qna_hit;
	private String qna_oringin_filename;
	
	public QnaDto() {
		super();
	}

	public QnaDto(int qna_id, int member_no, String qna_title, String qna_content, Date qna_regdate, int qna_hit,
			String qna_oringin_filename) {
		super();
		this.qna_id = qna_id;
		this.member_no = member_no;
		this.qna_title = qna_title;
		this.qna_content = qna_content;
		this.qna_regdate = qna_regdate;
		this.qna_hit = qna_hit;
		this.qna_oringin_filename = qna_oringin_filename;
	}

	public int getQna_id() {
		return qna_id;
	}

	public void setQna_id(int qna_id) {
		this.qna_id = qna_id;
	}

	public int getMember_no() {
		return member_no;
	}

	public void setMember_no(int member_no) {
		this.member_no = member_no;
	}

	public String getQna_title() {
		return qna_title;
	}

	public void setQna_title(String qna_title) {
		this.qna_title = qna_title;
	}

	public String getQna_content() {
		return qna_content;
	}

	public void setQna_content(String qna_content) {
		this.qna_content = qna_content;
	}

	public Date getQna_regdate() {
		return qna_regdate;
	}

	public void setQna_regdate(Date qna_regdate) {
		this.qna_regdate = qna_regdate;
	}

	public int getQna_hit() {
		return qna_hit;
	}

	public void setQna_hit(int qna_hit) {
		this.qna_hit = qna_hit;
	}

	public String getQna_oringin_filename() {
		return qna_oringin_filename;
	}

	public void setQna_oringin_filename(String qna_oringin_filename) {
		this.qna_oringin_filename = qna_oringin_filename;
	}

	@Override
	public String toString() {
		return "QnaDto [qna_id=" + qna_id + ", member_no=" + member_no + ", qna_title=" + qna_title + ", qna_content="
				+ qna_content + ", qna_regdate=" + qna_regdate + ", qna_hit=" + qna_hit + ", qna_oringin_filename="
				+ qna_oringin_filename + "]";
	}

	public QnaDto(int qna_id, String qna_title, String qna_content) {
		super();
		this.qna_id = qna_id;
		this.qna_title = qna_title;
		this.qna_content = qna_content;
	}

	
	
	
}
