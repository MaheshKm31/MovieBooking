package com.example.demo.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "bookings")
public class Bookings  {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private long showid;
	
	private String row1seat1;
	private String row1seat2;
	private String row1seat3;

	
	private String row2seat1;
	private String row2seat2;
	private String row2seat3;

	
	private String row3seat1;
	private String row3seat2;
	private String row3seat3;

	@JsonIgnore
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	@ManyToOne(fetch = FetchType.LAZY)
	private Movie movie;
	
	@JsonIgnore
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	@ManyToOne(fetch = FetchType.LAZY)
	private User user;
	
	
	public void setShowid(long showid) {
		this.showid = showid;
	}
	public long getShowid() {
		return showid;
	}
	public void setShow_id(long showid) {
		this.showid = showid;
	}
	public String getRow1seat1() {
		return row1seat1;
	}
	public void setRow1seat1(String row1seat1) {
		this.row1seat1 = row1seat1;
	}
	public String getRow1seat2() {
		return row1seat2;
	}
	public void setRow1seat2(String row1seat2) {
		this.row1seat2 = row1seat2;
	}
	public String getRow1seat3() {
		return row1seat3;
	}
	public void setRow1seat3(String row1seat3) {
		this.row1seat3 = row1seat3;
	}
	public String getRow2seat1() {
		return row2seat1;
	}
	public void setRow2seat1(String row2seat1) {
		this.row2seat1 = row2seat1;
	}
	public String getRow2seat2() {
		return row2seat2;
	}
	public void setRow2seat2(String row2seat2) {
		this.row2seat2 = row2seat2;
	}
	public String getRow2seat3() {
		return row2seat3;
	}
	public void setRow2seat3(String row2seat3) {
		this.row2seat3 = row2seat3;
	}
	public String getRow3seat1() {
		return row3seat1;
	}
	public void setRow3seat1(String row3seat1) {
		this.row3seat1 = row3seat1;
	}
	public String getRow3seat2() {
		return row3seat2;
	}
	public void setRow3seat2(String row3seat2) {
		this.row3seat2 = row3seat2;
	}
	public String getRow3seat3() {
		return row3seat3;
	}
	public void setRow3seat3(String row3seat3) {
		this.row3seat3 = row3seat3;
	}
	public Movie getMovie() {
		return movie;
	}
	public void setMovie(Movie movie) {
		this.movie = movie;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	
	
}