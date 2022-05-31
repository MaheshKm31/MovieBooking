package com.example.demo.service;

import com.example.demo.model.Admin;

public interface IAdminService {
	public Admin addAdmin(Admin admin);
	public Admin adminLogin(String username, String Password);
	public Admin deleteAdmin(String username);
}
