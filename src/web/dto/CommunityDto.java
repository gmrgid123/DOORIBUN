package web.dto;

import java.util.Date;

public class CommunityDto {
	private int community_id;
	private int member_no;
	private String community_title;
	private String community_content;
	private Date community_regdate;
	private int community_hit;
	private int community_good;
	private String community_oringin_filename;
	
	public CommunityDto() {
		super();
	}

	public CommunityDto(int community_id, int member_no, String community_title, String community_content,
			Date community_regdate, int community_hit, int community_good, String community_oringin_filename) {
		super();
		this.community_id = community_id;
		this.member_no = member_no;
		this.community_title = community_title;
		this.community_content = community_content;
		this.community_regdate = community_regdate;
		this.community_hit = community_hit;
		this.community_good = community_good;
		this.community_oringin_filename = community_oringin_filename;
	}

	public int getCommunity_id() {
		return community_id;
	}

	public void setCommunity_id(int community_id) {
		this.community_id = community_id;
	}

	public int getMember_no() {
		return member_no;
	}

	public void setMember_no(int member_no) {
		this.member_no = member_no;
	}

	public String getCommunity_title() {
		return community_title;
	}

	public void setCommunity_title(String community_title) {
		this.community_title = community_title;
	}

	public String getCommunity_content() {
		return community_content;
	}

	public void setCommunity_content(String community_content) {
		this.community_content = community_content;
	}

	public Date getCommunity_regdate() {
		return community_regdate;
	}

	public void setCommunity_regdate(Date community_regdate) {
		this.community_regdate = community_regdate;
	}

	public int getCommunity_hit() {
		return community_hit;
	}

	public void setCommunity_hit(int community_hit) {
		this.community_hit = community_hit;
	}

	public int getCommunity_good() {
		return community_good;
	}

	public void setCommunity_good(int community_good) {
		this.community_good = community_good;
	}

	public String getCommunity_oringin_filename() {
		return community_oringin_filename;
	}

	public void setCommunity_oringin_filename(String community_oringin_filename) {
		this.community_oringin_filename = community_oringin_filename;
	}

	@Override
	public String toString() {
		return "CommunityDto [community_id=" + community_id + ", member_no=" + member_no + ", community_title="
				+ community_title + ", community_content=" + community_content + ", community_regdate="
				+ community_regdate + ", community_hit=" + community_hit + ", community_good=" + community_good
				+ ", community_oringin_filename=" + community_oringin_filename + "]";
	}

	public CommunityDto(int community_id, String community_title, String community_content) {
		super();
		this.community_id = community_id;
		this.community_title = community_title;
		this.community_content = community_content;
	}
	
}
