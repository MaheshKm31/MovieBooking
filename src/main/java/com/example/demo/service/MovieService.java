package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.MovieDao;
import com.example.demo.exception.NoSuchItemException;
import com.example.demo.model.Movie;

@Service
public class MovieService implements IMovieService {

	@Autowired
	MovieDao dao;
	
	@Override
	public Movie addMovie(Movie l) {
			return dao.save(l);
	}
	@Override
	public Movie deleteMovie(long l) {
		Movie ll = dao.findById(l).orElse(new Movie());
		dao.delete(ll);
		if (ll != null) {
			return ll;
		} else {
			throw new NoSuchItemException("604", "No Such Movie exists");
		}

	}
	@Override
	public Movie printByMovieId(long l) {
		Movie t = dao.findById(l).get();
		if (t != null) {
			return t;
		} else {
			throw new NoSuchItemException("604", "No Such Movie exists");
		}
	}
	@Override
	public List<Movie> getAllMovies(){
		List<Movie> ll = new ArrayList<Movie>();
		Iterable<Movie> it = dao.findAll();
		for (Movie all : it) {
			ll.add(all);
		}
		return ll;
	}
	
	
	
}
