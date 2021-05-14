package web.dto;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CourseDto {
	private int course_id;
	private int member_no;
	private String course_title;
	private int tour1;
	private int tour2;
	private int tour3;
	private int tour4;
	private int tour5;
	private String transport;
	private String course_memo;
	private Date course_regdate;
	private int course_good;
	private int course_hit;
	
	public CourseDto() {
		super();
	}

	public CourseDto(int course_id, int member_no, String course_title, int tour1, int tour2, int tour3, int tour4,
			int tour5, String transport, String course_memo, Date course_regdate, int course_good, int course_hit) {
		super();
		this.course_id = course_id;
		this.member_no = member_no;
		this.course_title = course_title;
		this.tour1 = tour1;
		this.tour2 = tour2;
		this.tour3 = tour3;
		this.tour4 = tour4;
		this.tour5 = tour5;
		this.transport = transport;
		this.course_memo = course_memo;
		this.course_regdate = course_regdate;
		this.course_good = course_good;
		this.course_hit = course_hit;
	}

	public int getCourse_id() {
		return course_id;
	}

	public void setCourse_id(int course_id) {
		this.course_id = course_id;
	}

	public int getMember_no() {
		return member_no;
	}

	public void setMember_no(int member_no) {
		this.member_no = member_no;
	}

	public String getCourse_title() {
		return course_title;
	}

	public void setCourse_title(String course_title) {
		this.course_title = course_title;
	}

	public int getTour1() {
		return tour1;
	}

	public void setTour1(int tour1) {
		this.tour1 = tour1;
	}

	public int getTour2() {
		return tour2;
	}

	public void setTour2(int tour2) {
		this.tour2 = tour2;
	}

	public int getTour3() {
		return tour3;
	}

	public void setTour3(int tour3) {
		this.tour3 = tour3;
	}

	public int getTour4() {
		return tour4;
	}

	public void setTour4(int tour4) {
		this.tour4 = tour4;
	}

	public int getTour5() {
		return tour5;
	}

	public void setTour5(int tour5) {
		this.tour5 = tour5;
	}

	public String getTransport() {
		return transport;
	}

	public void setTransport(String transport) {
		this.transport = transport;
	}

	public String getCourse_memo() {
		return course_memo;
	}

	public void setCourse_memo(String course_memo) {
		this.course_memo = course_memo;
	}

	public Date getCourse_regdate() {
		return course_regdate;
	}
	
	public String getCourse_regdateDate() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String date = sdf.format(getCourse_regdate());
		return date;
	}
	
	
	public String getCourse_regdateTime() {
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		String time = sdf.format(getCourse_regdate());
		return time;
	}

	public void setCourse_regdate(Date course_regdate) {
		this.course_regdate = course_regdate;
	}

	public int getCourse_good() {
		return course_good;
	}

	public void setCourse_good(int course_good) {
		this.course_good = course_good;
	}

	public int getCourse_hit() {
		return course_hit;
	}

	public void setCourse_hit(int course_hit) {
		this.course_hit = course_hit;
	}

	@Override
	public String toString() {
		return "CourseDto [course_id=" + course_id + ", member_no=" + member_no + ", course_title=" + course_title
				+ ", tour1=" + tour1 + ", tour2=" + tour2 + ", tour3=" + tour3 + ", tour4=" + tour4 + ", tour5=" + tour5
				+ ", transport=" + transport + ", course_memo=" + course_memo + ", course_regdate=" + course_regdate
				+ ", course_good=" + course_good + ", course_hit=" + course_hit + "]";
	}
	
	
	
	
	
	
	
	
}
