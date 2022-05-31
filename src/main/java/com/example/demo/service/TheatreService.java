package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.dao.TheatreDao;
import com.example.demo.exception.NoSuchItemException;
import com.example.demo.model.Theatre;

@Service
public class TheatreService implements ITheatreService{

	@Autowired
	TheatreDao dao;
	
	@Override
	public Theatre addTheatre(Theatre l) {
			return dao.save(l);
	}
	@Override
	public Theatre deleteTheatre(long l) {
		Theatre ll = dao.findById(l).orElse(new Theatre());
		dao.delete(ll);
		if (ll != null) {
			return ll;
		} else {
			throw new NoSuchItemException("604", "No Such Theatre exists");
		}

	}
	@Override
	public Theatre printByTheatreId(long l) {
		Theatre t = dao.findById(l).get();
		if (t != null) {
			return t;
		} else {
			throw new NoSuchItemException("604", "No Such Location exists");
		}
	}
	@Override
	public List<Theatre> allTheatres(){
		List<Theatre> ll = new ArrayList<Theatre>();
		Iterable<Theatre> it = dao.findAll();
		for (Theatre all : it) {
			ll.add(all);
		}
		return ll;
	}
	@Override
	public List<Theatre> getAllTheater() {
		return dao.findAll();
	}
	
	
}
