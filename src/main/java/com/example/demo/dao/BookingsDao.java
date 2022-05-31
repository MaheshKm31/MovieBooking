package com.example.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Bookings;

public interface BookingsDao extends JpaRepository<Bookings, Long> {

}
