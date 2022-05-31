package com.example.demo.service;

import java.util.List;

import com.example.demo.model.Theatre;

public interface ITheatreService {

	Theatre addTheatre(Theatre l);

	Theatre deleteTheatre(long l);

	Theatre printByTheatreId(long l);

	List<Theatre> allTheatres();

	List<Theatre> getAllTheater();

}
