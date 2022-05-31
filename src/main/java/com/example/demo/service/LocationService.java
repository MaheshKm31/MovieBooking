package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.LocationDao;
import com.example.demo.exception.EmptyInputException;
import com.example.demo.exception.NoSuchItemException;
import com.example.demo.model.Location;

@Service
public class LocationService implements ILocationService{

	@Autowired
	LocationDao dao;
	
	@Override
	public Location addLocation(Location l) {
			if (l.getCity().isEmpty()) {
				throw new EmptyInputException("515", "Input Fields Can't be Empty");
			}
			return dao.save(l);
	}
	@Override
	public Location deleteLocation(String l) {
		Location ll = dao.findByCity(l);
		dao.delete(ll);
		if (ll != null) {
			return ll;
		} else {
			throw new NoSuchItemException("604", "No Such Location exists");
		}

	}
	@Override
	public Location printLocationByCity(String l) {
		Location f = dao.findByCity(l);
		if (f != null) {
			return f;
		} else {
			throw new NoSuchItemException("604", "No Such Location exists");
		}
	}
	@Override
	public List<Location> allLocations(){
		Iterable<Location> it = dao.findAll();
		List<Location> ll = new ArrayList<Location>();
		for (Location all : it) {
			ll.add(all);
		}
		return ll;
	}

	
}
