package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.dao.UserDao;
import com.example.demo.exception.EmptyInputException;
import com.example.demo.exception.NoSuchItemException;
import com.example.demo.exception.NoSuchUserException;
import com.example.demo.exception.WrongPasswordException;
import com.example.demo.model.Location;
import com.example.demo.model.User;
@Service
public class UserService implements IUserService{
	@Autowired
	private UserDao userDao;

	public User addUser(User User) {
		if (User.getUsername().isEmpty() || User.getUsername().length() == 0) {
			throw new EmptyInputException("404", "Input cannot be Empty");
		}
		return userDao.save(User);		
	}
	@Override
	public User UserLogin(String username, String Password) {
		User User = userDao.findByUsername(username);
		if (User != null) {
			if (User.getPassword().equals(Password)) {
				return User;
			} else {
				throw new WrongPasswordException("405", "Incorrect Password");
			}
		} else {
			throw new NoSuchUserException("406", "User does not exist");
		}
	}
	@Override
	public User deleteUser(String username) {
		User User = userDao.findByUsername(username);
		if (User != null) {
			userDao.delete(User);
			return User;
		} else {
			throw new NoSuchUserException("406", "User does not exist");
		}
	}
	@Override
	public List<User> printUser(){
		Iterable<User> it = userDao.findAll();
		List<User> ll = new ArrayList<User>();
		for (User all : it) {
			ll.add(all);
		}
		return ll;
	}
	@Override
	public User printByUsername(String l) {
		User f = userDao.findByUsername(l);
		if (f != null) {
			return f;
		} else {
			throw new NoSuchItemException("604", "No Such user exists");
		}
	}
}
