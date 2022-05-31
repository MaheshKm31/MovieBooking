package com.example.demo.service;

import java.util.List;
import com.example.demo.model.Bookings;

public interface IBookingsService {
	Bookings addBookings(Bookings l);

	Bookings deleteBookings(long l);

	Bookings printByBookingsId(long l);

	List<Bookings> getAllBookingss();


}
