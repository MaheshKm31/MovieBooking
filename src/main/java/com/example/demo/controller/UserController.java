package com.example.demo.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dao.BookingsDao;
import com.example.demo.dao.UserDao;
import com.example.demo.exception.WrongPasswordException;
import com.example.demo.model.Bookings;
import com.example.demo.model.Location;
import com.example.demo.model.Movie;
import com.example.demo.model.Theatre;
import com.example.demo.model.User;
import com.example.demo.service.BookingsService;
import com.example.demo.service.LocationService;
import com.example.demo.service.MovieService;
import com.example.demo.service.TheatreService;
import com.example.demo.service.UserService;

@RestController
public class UserController {
	@Autowired
	UserService userService;
	@Autowired
	UserDao userDao;
	@Autowired
	MovieService movieService;
	@Autowired
	BookingsDao bookingsDao;
	@Autowired
	BookingsService bookingsService;
	@Autowired
	TheatreService theatreService;
	@Autowired
	LocationService locationService;
	
	Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@PostMapping("/addUser")
	public ResponseEntity<User> addUser(@Valid @RequestBody User user){
		logger.info("User Signedup Successfully");
		return new ResponseEntity<User>(userService.addUser(user),HttpStatus.ACCEPTED);
	}
	@GetMapping("/userLogin/{username}/{password}")
	public ResponseEntity<User> UserLogin(@Valid @PathVariable("username") String username,@PathVariable("password") String password,HttpServletRequest req){
		User User = userService.UserLogin(username, password);
		if (User.getUsername().equals(username) && User.getPassword().equals(password)) {
			req.getSession().setAttribute("User", User);
			logger.info("User login successful with the username " + username);
			return new ResponseEntity<User>(User, HttpStatus.ACCEPTED);
		}
		logger.warn("User login Failed with the username " + username);
		return new ResponseEntity<User>(User, HttpStatus.NOT_FOUND);
	}
	@DeleteMapping("/deleteUser/{username}")
	public ResponseEntity<User> deleteUser(@PathVariable("username") String username) {
		User ad = userService.deleteUser(username);
		if (ad.getUsername().equals(username)) {
			logger.info("User Details deleted " + username);
			return new ResponseEntity<User>(ad, HttpStatus.GONE);
		} else {
			logger.warn("User Details not deleted" + username);
			return new ResponseEntity<User>(ad, HttpStatus.NOT_FOUND);
		}
	}
	@GetMapping("/userLogout/{username}/{password}")
	public String UserLogout(@PathVariable("username")String username,@PathVariable("password")String password ,HttpSession ses)
	{
		User User=(User)userDao.findByUsername(username);
		if(User.getPassword().equals(password))
		{
			ses.removeAttribute("User");
			logger.info("logged out sucessfully");
			return "Logout Successful";
		}
		else {
			logger.info("log out failed");
			throw new WrongPasswordException("515", "Wrong password");
		}
	}
	@GetMapping("/printLocation/{city}")
	public ResponseEntity<Location> printLocation(@Valid @PathVariable("city") String city) {
		Location ll=locationService.printLocationByCity(city);
		logger.info("location displayed");
		return new ResponseEntity<Location>(ll, HttpStatus.OK);
	}
	@GetMapping("/printAllLocation")
	public ResponseEntity<List<Location>> printAllLocation(){
		return new ResponseEntity<List<Location>>(locationService.allLocations(),HttpStatus.ACCEPTED);
	}

	@ GetMapping("printTheatre/{theaterId}") 
	public ResponseEntity<Theatre> printTheatre( @PathVariable("theaterId")long l) { 
		logger.info("location printed");
		return new ResponseEntity<Theatre>(theatreService.printByTheatreId(l),HttpStatus.OK); }


	@GetMapping("/printAllTheatre") 
	public ResponseEntity<List<Theatre>> printAllTheatre(){ 
		return new ResponseEntity<List<Theatre>>(theatreService.allTheatres(),HttpStatus.ACCEPTED); }
 
	/*
	 * @GetMapping("/printAllUsers") public ResponseEntity<List<User>>
	 * printAllUsers(){ return new
	 * ResponseEntity<List<User>>(userService.printUser(),HttpStatus.ACCEPTED); }
	 */
	@PostMapping("/bookings/{movieId}/{username}")
	public ResponseEntity<Bookings> addBookings(@Valid @PathVariable("movieId") long id,@Valid @RequestBody Bookings t,@PathVariable("username") String username,HttpSession ses) {
		User user=(User)ses.getAttribute("User");
		if(user.getUsername().equals(username)) {
			Movie movie1 = movieService.printByMovieId(id);
			movie1.getBookings().add(t);
			t.setMovie(movie1);
			bookingsDao.save(t);
			User user1 = userService.printByUsername(username);
			user1.getB().add(t);
			t.setUser(user1);
			bookingsDao.save(t);
			logger.info("Booking added sucessfully");
			return new ResponseEntity<Bookings>(t,HttpStatus.CREATED);
		}
		logger.info("Booking adding failed");
		return new ResponseEntity<Bookings>(t, HttpStatus.NOT_ACCEPTABLE);
	}
	@DeleteMapping("/deleteBookings/{showId}")
	public ResponseEntity<Bookings> deleteBookings(@PathVariable("showId") long showId,HttpSession ses) {
		Bookings book=bookingsService.deleteBookings(showId);
		return new ResponseEntity<Bookings>(book,HttpStatus.ACCEPTED);
	}
	
	@ GetMapping("/{movieId}/printMovie") 
	public ResponseEntity<Movie> printMovie( @PathVariable("movieId")long l) { 
		logger.info("Movie printed");
		return new ResponseEntity<Movie>(movieService.printByMovieId(l),HttpStatus.OK); }


	@GetMapping("/printAllMovies") 
	public ResponseEntity<List<Movie>> printAllMovie(){ 
		logger.info("Movies printed");
		return new ResponseEntity<List<Movie>>(movieService.getAllMovies(),HttpStatus.ACCEPTED); 
	}
	@GetMapping("/{movieId}/printAllBookings") 
	public ResponseEntity<Movie> printAll(@PathVariable("movieId") long l) { 
		logger.info("Movie printed");
		return new ResponseEntity<Movie>(movieService.printByMovieId(l),HttpStatus.OK);
	}
	}
