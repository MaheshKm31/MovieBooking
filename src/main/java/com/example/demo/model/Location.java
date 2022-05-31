package com.example.demo.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="location")
public class Location {

	@Id
	private String city;
	private String state;
	
	@JsonIgnore
	@OneToMany(mappedBy="location")
	private List<Theatre> theatre=new ArrayList<Theatre>();
	
	public List<Theatre> getTheatre() {
		return theatre;
	}
	public void setTheatre(List<Theatre> theatre) {
		this.theatre = theatre;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public Location() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Location(String city, String state) {
		super();
		this.city = city;
		this.state = state;
	}
	
}
