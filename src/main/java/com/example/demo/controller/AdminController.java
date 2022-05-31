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
import com.example.demo.dao.AdminDao;
import com.example.demo.dao.BookingsDao;
import com.example.demo.dao.LocationDao;
import com.example.demo.dao.MovieDao;
import com.example.demo.dao.TheatreDao;
import com.example.demo.exception.WrongPasswordException;
import com.example.demo.model.Admin;
import com.example.demo.model.Bookings;
import com.example.demo.model.Location;
import com.example.demo.model.Movie;
import com.example.demo.model.Theatre;
import com.example.demo.service.AdminService;
import com.example.demo.service.BookingsService;
import com.example.demo.service.LocationService;
import com.example.demo.service.MovieService;
import com.example.demo.service.TheatreService;

@RestController
public class AdminController {

	@Autowired
	AdminService adminService;
	@Autowired
	AdminDao adminDao;
	@Autowired
	LocationService locationService;
	@Autowired
	TheatreService theatreService;
	@Autowired
	TheatreDao theatreDao;
	@Autowired
	LocationDao locationDao;
	@Autowired
	MovieService movieService;
	@Autowired
	MovieDao movieDao;
	@Autowired
	BookingsService bookingsService;
	@Autowired
	BookingsDao bookingsDao;
	
	Logger logger = LoggerFactory.getLogger(AdminController.class);

	@PostMapping("/addAdmin")
	public ResponseEntity<Admin> addAdmin(@Valid @RequestBody Admin admin){
		logger.info("Admin Signedup Successfully");
		return new ResponseEntity<Admin>(adminService.addAdmin(admin),HttpStatus.ACCEPTED);
	}
	@GetMapping("/adminLogin/{username}/{password}")
	public ResponseEntity<Admin> adminLogin(@Valid @PathVariable("username") String username,@PathVariable("password") String password,HttpServletRequest req){
		Admin admin = adminService.adminLogin(username, password);
		if (admin.getUsername().equals(username) && admin.getPassword().equals(password)) {
			req.getSession().setAttribute("admin", admin);
			logger.info("admin login successful with the username " + username);
			return new ResponseEntity<Admin>(admin, HttpStatus.ACCEPTED);
		}
		logger.warn("admin login Failed with the username " + username);
		return new ResponseEntity<Admin>(admin, HttpStatus.NOT_FOUND);
	}
	@DeleteMapping("/deleteAdmin/{username}")
	public ResponseEntity<Admin> deleteAdmin(@PathVariable("username") String username) {
		Admin ad = adminService.deleteAdmin(username);
		if (ad.getUsername().equals(username)) {
			logger.info("Admin Details deleted " + username);
			return new ResponseEntity<Admin>(ad, HttpStatus.GONE);
		} else {
			logger.warn("Admin Details not deleted" + username);
			return new ResponseEntity<Admin>(ad, HttpStatus.NOT_FOUND);
		}
	}
	@GetMapping("/adminLogout/{username}/{password}")
	public String adminLogout(@PathVariable("username")String username,@PathVariable("password")String password ,HttpSession ses)
	{
		Admin admin=(Admin)adminDao.findByUsername(username);
		if(admin.getPassword().equals(password))
		{
			ses.removeAttribute("admin");
			logger.info("logged out sucessfully");
			return "Logout Successful";
		}
		else {
			logger.info("log out failed");
			throw new WrongPasswordException("515", "Wrong password");
		}
	}
	@PostMapping("addLocation/{username}")
	public ResponseEntity<Location> addLocation(@Valid @RequestBody Location l,@PathVariable("username") String user,HttpSession ses) {
		Admin admin=(Admin)ses.getAttribute("admin");
		if(admin.getUsername().equals(user)) {
			logger.info("Location added sucessfully");
			return new ResponseEntity<Location>( locationService.addLocation(l),HttpStatus.CREATED);
		}
		logger.info("Location adding failed");
		return new ResponseEntity<Location>(l, HttpStatus.NOT_ACCEPTABLE);
	}
	@DeleteMapping("/deleteLocation/{city}")
	public ResponseEntity<Location> deleteLocation(@Valid @PathVariable("city") String city) {
		Location ll=locationService.deleteLocation(city);
		logger.info("location deleted");
		return new ResponseEntity<Location>(ll, HttpStatus.OK);
	}
	
	@PostMapping("{city}/theatre/{username}")
	public ResponseEntity<Theatre> addTheatre(@Valid @PathVariable("city") String city,@Valid @RequestBody Theatre t,@PathVariable("username") String user,HttpSession ses) {
		Admin admin=(Admin)ses.getAttribute("admin");
		if(admin.getUsername().equals(user)) {
			Location city1 = locationService.printLocationByCity(city);
			city1.getTheatre().add(t);
			t.setLocation(city1);
			Theatre theatre=new Theatre();
			theatre.setLocation(city1);
			theatreDao.save(t);
			logger.info("Theatre added sucessfully");
			return new ResponseEntity<Theatre>(HttpStatus.CREATED);
		}
		logger.info("Theatre adding failed");
		return new ResponseEntity<Theatre>(t, HttpStatus.NOT_ACCEPTABLE);
	}


	@DeleteMapping("/deleteTheatre/{theatreId}") 
	public ResponseEntity<Theatre> deleteTheatre(@PathVariable("theatreId") long l) { 
		logger.info("theatre deleted");
		return new ResponseEntity<Theatre>(theatreService.deleteTheatre(l),HttpStatus.OK);
	}



	
	@PostMapping("/movie/{theatreId}/{username}")
	public ResponseEntity<Movie> addMovie(@Valid @PathVariable("theatreId") long id,@Valid @RequestBody Movie t,@PathVariable("username") String user,HttpSession ses) {
		Admin admin=(Admin)ses.getAttribute("admin");
		if(admin.getUsername().equals(user)) {
			Theatre theatre1 = theatreService.printByTheatreId(id);
			theatre1.getTheatre().add(t);
			t.setTheatre(theatre1);
			Movie movie=new Movie();
			movie.setTheatre(theatre1);
			movieDao.save(t);
			logger.info("Movie added sucessfully");
			return new ResponseEntity<Movie>(HttpStatus.CREATED);
		}
		logger.info("Movie adding failed");
		return new ResponseEntity<Movie>(t, HttpStatus.NOT_ACCEPTABLE);
	}


	@DeleteMapping("/{movieId}/deleteMovie") 
	public ResponseEntity<Movie> deleteMovie(@PathVariable("movieId") long l) { 
		logger.info("Movie deleted");
		return new ResponseEntity<Movie>(movieService.deleteMovie(l),HttpStatus.OK);
	}

	
	
	
	
}
