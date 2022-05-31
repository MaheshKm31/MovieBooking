package com.example.demo.service;

import java.util.List;

import com.example.demo.model.Location;
import com.example.demo.model.User;

public interface IUserService {
	public User addUser(User User);
	public User UserLogin(String username, String Password);
	public User deleteUser(String username);
	List<User> printUser();
	public User printByUsername(String l);
}
