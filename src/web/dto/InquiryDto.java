package web.dto;

import java.text.SimpleDateFormat;
import java.util.Date;

public class InquiryDto {
	private int inquiry_id;
	private int member_no;
	private String inquiry_title;
	private String inquiry_content;
	private Date inquiry_regdate;
	private String inquiry_state;
	
	
	
	public InquiryDto() {
		super();
	}



	public InquiryDto(int inquiry_id, int member_no, String inquiry_title, String inquiry_content, Date inquiry_regdate,
			String inquiry_state) {
		super();
		this.inquiry_id = inquiry_id;
		this.member_no = member_no;
		this.inquiry_title = inquiry_title;
		this.inquiry_content = inquiry_content;
		this.inquiry_regdate = inquiry_regdate;
		this.inquiry_state = inquiry_state;
	}



	public int getInquiry_id() {
		return inquiry_id;
	}



	public void setInquiry_id(int inquiry_id) {
		this.inquiry_id = inquiry_id;
	}



	public int getMember_no() {
		return member_no;
	}



	public void setMember_no(int member_no) {
		this.member_no = member_no;
	}



	public String getInquiry_title() {
		return inquiry_title;
	}



	public void setInquiry_title(String inquiry_title) {
		this.inquiry_title = inquiry_title;
	}



	public String getInquiry_content() {
		return inquiry_content;
	}



	public void setInquiry_content(String inquiry_content) {
		this.inquiry_content = inquiry_content;
	}



	public Date getInquiry_regdate() {
		return inquiry_regdate;
	}



	public void setInquiry_regdate(Date inquiry_regdate) {
		this.inquiry_regdate = inquiry_regdate;
	}
	
	public String getInquiry_regdateDate() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String date = sdf.format(getInquiry_regdate());
		return date;
	}
	
	
	public String getInquiry_regdateTime() {
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		String time = sdf.format(getInquiry_regdate());
		return time;
	}



	public String getInquiry_state() {
		return inquiry_state;
	}



	public void setInquiry_state(String inquiry_state) {
		this.inquiry_state = inquiry_state;
	}



	@Override
	public String toString() {
		return "InquiryDto [inquiry_id=" + inquiry_id + ", member_no=" + member_no + ", inquiry_title=" + inquiry_title
				+ ", inquiry_content=" + inquiry_content + ", inquiry_regdate=" + inquiry_regdate + ", inquiry_state="
				+ inquiry_state + "]";
	}
	
	
	
	
	
	
	
}
