package com.example.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Admin;

public interface AdminDao extends JpaRepository<Admin, String> {

	Admin findByUsername(String username);

}
