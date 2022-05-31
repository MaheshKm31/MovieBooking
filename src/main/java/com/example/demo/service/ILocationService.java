package com.example.demo.service;

import java.util.List;

import com.example.demo.model.Location;

public interface ILocationService {

	Location addLocation(Location l);

	Location deleteLocation(String l);

	Location printLocationByCity(String l);

	List<Location> allLocations();
}
