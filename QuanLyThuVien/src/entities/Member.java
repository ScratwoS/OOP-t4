package entities;

import java.util.Date;

public class Member {
	private int id,type;
	private String fullname,description;
	private Date expired_date;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getFullname() {
		return fullname;
	}
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getExpired_date() {
		return expired_date;
	}
	public void setExpired_date(Date expired_date) {
		this.expired_date = expired_date;
	}
	public Member(int id, int type, String fullname, String description, Date expired_date) {
		super();
		this.id = id;
		this.type = type;
		this.fullname = fullname;
		this.description = description;
		this.expired_date = expired_date;
	}
	@Override
	public String toString() {
		return "Member [id=" + id + ", type=" + type + ", fullname=" + fullname + ", description=" + description
				+ ", expired_date=" + expired_date + "]";
	}
	
}
