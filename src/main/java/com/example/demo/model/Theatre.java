package com.example.demo.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "theatre")
public class Theatre {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long theatreId;
    @NotEmpty
    private String theatreName;
    @NotEmpty
    private String theatreCity;
    
    @ManyToOne
    private Location location;
    
    @JsonIgnore
    @OneToMany
	private List<Movie> theatre=new ArrayList<Movie>();

    public List<Movie> getTheatre() {
		return theatre;
	}

	public void setTheatre(List<Movie> theatre) {
		this.theatre = theatre;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public long getTheatreId() {
        return theatreId;
    }

    public void setTheatreId(long theatreId) {
        this.theatreId = theatreId;
    }

    public String getTheatreName() {
        return theatreName;
    }

    public void setTheatreName(String theatreName) {
        this.theatreName = theatreName;
    }

    public String getTheatreCity() {
        return theatreCity;
    }

    public void setTheatreCity(String theatreCity) {
        this.theatreCity = theatreCity;
    }

	public Theatre(long theatreId, String theatreName, String theatreCity) {
		super();
		this.theatreId = theatreId;
		this.theatreName = theatreName;
		this.theatreCity = theatreCity;
	}

	public Theatre() {
		super();
		// TODO Auto-generated constructor stub
	}
    
}
