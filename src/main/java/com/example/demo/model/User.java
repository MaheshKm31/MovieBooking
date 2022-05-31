package com.example.demo.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="user")
public class User {
	@Id
	private String username;
	@Size(min=8,message="password should be greater than 8")
	private String password;

	@JsonIgnore
	@OneToMany(mappedBy="user",orphanRemoval = true)
	private List<Bookings> b= new ArrayList<Bookings>();
	
	public List<Bookings> getB() {
		return b;
	}
	public void setB(List<Bookings> b) {
		this.b = b;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	

}