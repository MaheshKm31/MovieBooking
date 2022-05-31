package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.AdminDao;
import com.example.demo.exception.EmptyInputException;
import com.example.demo.exception.NoSuchAdminException;
import com.example.demo.exception.WrongPasswordException;
import com.example.demo.model.Admin;

@Service
public class AdminService implements IAdminService{
	@Autowired
	AdminDao dao;
	
	public Admin addAdmin(Admin admin) {
		if (admin.getUsername().isEmpty() || admin.getUsername().length() == 0) {
			throw new EmptyInputException("404", "Input cannot be Empty");
		}
		return dao.save(admin);		
	}
	@Override
	public Admin adminLogin(String username, String Password) {
		Admin admin = dao.findByUsername(username);
		if (admin != null) {
			if (admin.getPassword().equals(Password)) {
				return admin;
			} else {
				throw new WrongPasswordException("405", "Incorrect Password");
			}
		} else {
			throw new NoSuchAdminException("406", "Admin does not exist");
		}
	}
	@Override
	public Admin deleteAdmin(String username) {
		Admin admin = dao.findByUsername(username);
		if (admin != null) {
			dao.delete(admin);
			return admin;
		} else {
			throw new NoSuchAdminException("406", "Admin does not exist");
		}
	}
}
