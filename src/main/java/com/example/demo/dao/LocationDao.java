package com.example.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Location;

public interface LocationDao extends JpaRepository<Location, String> {

	Location findByCity(String city);
	
}
