package web.dto;

import java.text.SimpleDateFormat;
import java.util.Date;

public class InquiryReplyDto {
	private int inquiry_reply_id;
	private int inquiry_id;
	private int member_no;
	private String reply_title;
	private String reply_content;
	private Date reply_regdate;
	
	public InquiryReplyDto() {
		super();
	}

	public InquiryReplyDto(int inquiry_reply_id, int inquiry_id, int member_no, String reply_title,
			String reply_content, Date reply_regdate) {
		super();
		this.inquiry_reply_id = inquiry_reply_id;
		this.inquiry_id = inquiry_id;
		this.member_no = member_no;
		this.reply_title = reply_title;
		this.reply_content = reply_content;
		this.reply_regdate = reply_regdate;
	}

	public int getInquiry_reply_id() {
		return inquiry_reply_id;
	}

	public void setInquiry_reply_id(int inquiry_reply_id) {
		this.inquiry_reply_id = inquiry_reply_id;
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

	public String getReply_title() {
		return reply_title;
	}

	public void setReply_title(String reply_title) {
		this.reply_title = reply_title;
	}

	public String getReply_content() {
		return reply_content;
	}

	public void setReply_content(String reply_content) {
		this.reply_content = reply_content;
	}

	public Date getReply_regdate() {
		return reply_regdate;
	}

	public void setReply_regdate(Date reply_regdate) {
		this.reply_regdate = reply_regdate;
	}
	
	public String getReply_regdateDate() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String date = sdf.format(getReply_regdate());
		return date;
	}
	
	public String getReply_regdateTime() {
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		String time = sdf.format(getReply_regdate());
		return time;
	}
	

	@Override
	public String toString() {
		return "InquiryReplyDto [inquiry_reply_id=" + inquiry_reply_id + ", inquiry_id=" + inquiry_id + ", member_no="
				+ member_no + ", reply_title=" + reply_title + ", reply_content=" + reply_content + ", reply_regdate="
				+ reply_regdate + "]";
	}
	
	
	
	
	
	
}
