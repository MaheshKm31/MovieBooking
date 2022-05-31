package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.BookingsDao;
import com.example.demo.exception.NoSuchItemException;
import com.example.demo.model.Bookings;

@Service
public class BookingsService implements IBookingsService{
	@Autowired
	BookingsDao dao;
	
	@Override
	public Bookings addBookings(Bookings l) {
			return dao.save(l);
	}
	@Override
	public Bookings deleteBookings(long l) {
		Bookings ll = dao.findById(l).get();
		dao.delete(ll);
		if (ll != null) {
			return ll;
		} else {
			throw new NoSuchItemException("604", "No Such Bookings exists");
		}

	}
	@Override
	public Bookings printByBookingsId(long l) {
		Bookings t = dao.findById(l).get();
		if (t != null) {
			return t;
		} else {
			throw new NoSuchItemException("604", "No Such Bookings exists");
		}
	}
	@Override
	public List<Bookings> getAllBookingss(){
		List<Bookings> ll = new ArrayList<Bookings>();
		Iterable<Bookings> it = dao.findAll();
		for (Bookings all : it) {
			ll.add(all);
		}
		return ll;
	}
	
	
}
