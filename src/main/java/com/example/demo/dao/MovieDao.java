package com.example.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.model.Movie;

public interface MovieDao extends JpaRepository<Movie, Long> {

	Movie findByMovieId(long l);

}
