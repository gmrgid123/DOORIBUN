package web.dto;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TaxiDto {
	
	
	private int taxi_id;
	private int member_no;
	private String start_addr;
	private String end_addr;
	private Date book_date;
	private Date taxi_regdate;
	private String taxi_state;
	private int driver_no;
	
	public TaxiDto() {
		super();
	}
	
	
	//update
	
	public TaxiDto(int taxi_id, String start_addr, String end_addr, Date book_date) {
		super();
		this.taxi_id = taxi_id;
		this.start_addr = start_addr;
		this.end_addr = end_addr;
		this.book_date = book_date;
	}




	public TaxiDto(int taxi_id, int member_no, String start_addr, String end_addr, Date book_date, Date taxi_regdate,
			String taxi_state, int driver_no) {
		super();
		this.taxi_id = taxi_id;
		this.member_no = member_no;
		this.start_addr = start_addr;
		this.end_addr = end_addr;
		this.book_date = book_date;
		this.taxi_regdate = taxi_regdate;
		this.taxi_state = taxi_state;
		this.driver_no = driver_no;
	}

	//selectAll
	public TaxiDto(int taxi_id, String start_addr, String end_addr, Date book_date, Date taxi_regdate,
			String taxi_state) {
		super();
		this.taxi_id = taxi_id;
		this.start_addr = start_addr;
		this.end_addr = end_addr;
		this.book_date = book_date;
		this.taxi_regdate = taxi_regdate;
		this.taxi_state = taxi_state;
	}
	
	//insert
	public TaxiDto(String start_addr, String end_addr, Date book_date) {
		super();
	
		this.start_addr = start_addr;
		this.end_addr = end_addr;
		this.book_date = book_date;
	}
	
	
	
	
	public TaxiDto(int taxi_id) {
		super();
		this.taxi_id = taxi_id;
	}


	//driverCancel
	public TaxiDto(int taxi_id, String taxi_state) {
		super();
		this.taxi_id = taxi_id;
		this.taxi_state = taxi_state;
	}


	public int gettaxi_id() {
		return taxi_id;
	}




	public void settaxi_id(int taxi_id) {
		this.taxi_id = taxi_id;
	}

	public int getMember_no() {
		return member_no;
	}

	public void setMember_no(int member_no) {
		this.member_no = member_no;
	}

	public String getStart_addr() {
		return start_addr;
	}

	public void setStart_addr(String start_addr) {
		this.start_addr = start_addr;
	}

	public String getEnd_addr() {
		return end_addr;
	}

	public void setEnd_addr(String end_addr) {
		this.end_addr = end_addr;
	}

	public Date getBook_date() {
		return book_date;
	}
	
	public String getBook_dateString() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String date = sdf.format(book_date);
		return date;
	}
	
	public String getBook_dateDate() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String date = sdf.format(book_date);
		return date;
	}
	
	public String getBook_dateTime() {
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
		String time = sdf.format(book_date);
		return time;
	}
	
	public void setBook_date(Date book_date) {
		this.book_date = book_date;
	}

	public Date getTaxi_regdate() {
		return taxi_regdate;
	}

	public void setTaxi_regdate(Date taxi_regdate) {
		this.taxi_regdate = taxi_regdate;
	}

	public String getTaxi_state() {
		return taxi_state;
	}

	public void setTaxi_state(String taxi_state) {
		this.taxi_state = taxi_state;
	}

	public int getDriver_no() {
		return driver_no;
	}

	public void setDriver_no(int driver_no) {
		this.driver_no = driver_no;
	}

	@Override
	public String toString() {
		return "TaxiDto [taxi_id=" + taxi_id + ", member_no=" + member_no + ", start_addr=" + start_addr + ", end_addr="
				+ end_addr + ", book_date=" + book_date + ", taxi_regdate=" + taxi_regdate + ", taxi_state="
				+ taxi_state + ", driver_no=" + driver_no + "]";
	}
	
	
	
	
	
}
	
	
	
	
	
	
