package web.dto;

public class TourDto {
	private int tour_id;
	private String tour_name;
	private String tour_addr;
	private String tour_content;
	private double tour_lat;
	private double tour_lon;
	private String tour_agency;
	private String tour_phone;
	private int tour_park;
	
	public TourDto() {
		super();
	}
	
	public TourDto(int tour_id, String tour_name, String tour_addr, String tour_content, double tour_lat,
			double tour_lon, String tour_agency, String tour_phone, int tour_park) {
		super();
		this.tour_id = tour_id;
		this.tour_name = tour_name;
		this.tour_addr = tour_addr;
		this.tour_content = tour_content;
		this.tour_lat = tour_lat;
		this.tour_lon = tour_lon;
		this.tour_agency = tour_agency;
		this.tour_phone = tour_phone;
		this.tour_park = tour_park;
	}

	public int getTour_id() {
		return tour_id;
	}

	public void setTour_id(int tour_id) {
		this.tour_id = tour_id;
	}

	public String getTour_name() {
		return tour_name;
	}

	public void setTour_name(String tour_name) {
		this.tour_name = tour_name;
	}

	public String getTour_addr() {
		return tour_addr;
	}

	public void setTour_addr(String tour_addr) {
		this.tour_addr = tour_addr;
	}

	public String getTour_content() {
		return tour_content;
	}

	public void setTour_content(String tour_content) {
		this.tour_content = tour_content;
	}

	public double getTour_lat() {
		return tour_lat;
	}

	public void setTour_lat(double tour_lat) {
		this.tour_lat = tour_lat;
	}

	public double getTour_lon() {
		return tour_lon;
	}

	public void setTour_lon(double tour_lon) {
		this.tour_lon = tour_lon;
	}

	public String getTour_agency() {
		return tour_agency;
	}

	public void setTour_agency(String tour_agency) {
		this.tour_agency = tour_agency;
	}

	public String getTour_phone() {
		return tour_phone;
	}

	public void setTour_phone(String tour_phone) {
		this.tour_phone = tour_phone;
	}

	public int getTour_park() {
		return tour_park;
	}

	public void setTour_park(int tour_park) {
		this.tour_park = tour_park;
	}


	
	
	
	
	
	
	
}
