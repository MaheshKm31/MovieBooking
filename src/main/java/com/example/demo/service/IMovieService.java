package com.example.demo.service;

import java.util.List;

import com.example.demo.model.Movie;

public interface IMovieService {
	Movie addMovie(Movie l);

	Movie deleteMovie(long l);

	Movie printByMovieId(long l);

	List<Movie> getAllMovies();

}
