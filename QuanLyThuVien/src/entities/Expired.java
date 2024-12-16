package entities;

import java.util.Date;

public class Expired {
	private int id, member,staff,book, expired;
	private Date start_date, end_date;
	private String description;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getMember() {
		return member;
	}
	public void setMember(int member) {
		this.member = member;
	}
	public int getStaff() {
		return staff;
	}
	public void setStaff(int staff) {
		this.staff = staff;
	}
	public int getBook() {
		return book;
	}
	public void setBook(int book) {
		this.book = book;
	}
	public int getExpired() {
		return expired;
	}
	public void setExpired(int expired) {
		this.expired = expired;
	}
	public Date getStart_date() {
		return start_date;
	}
	public void setStart_date(Date start_date) {
		this.start_date = start_date;
	}
	public Date getEnd_date() {
		return end_date;
	}
	public void setEnd_date(Date end_date) {
		this.end_date = end_date;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Expired(int id, int member, int staff, int book, int expired, Date start_date, Date end_date,
			String description) {
		super();
		this.id = id;
		this.member = member;
		this.staff = staff;
		this.book = book;
		this.expired = expired;
		this.start_date = start_date;
		this.end_date = end_date;
		this.description = description;
	}
	public Expired() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
