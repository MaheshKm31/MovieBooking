package com.example.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.model.Theatre;

public interface TheatreDao extends JpaRepository<Theatre, Long> {

	Theatre findByTheatreId(long l);

}
