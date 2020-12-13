package com.MovieMain.MovieModelData;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="Actor_data")
public class Actor {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private  int aid;
	@Column
	private String aname;
	@Column
	private String agender;
	@OneToMany(mappedBy="actors",cascade = CascadeType.ALL)
	private Set<Movie> movies;
	
	public Actor() {}
	public Actor(int aid, String aname, String agender) {
		super();
		this.aid = aid;
		this.aname = aname;
		this.agender = agender;
	}
	public int getAid() {
		return aid;
	}
	public void setAid(int aid) {
		this.aid = aid;
	}
	public String getAname() {
		return aname;
	}
	public void setAname(String aname) {
		this.aname = aname;
	}
	public String getAgender() {
		return agender;
	}
	public void setAgender(String agender) {
		this.agender = agender;
	}
	public Set<Movie> getMovies() {
		return movies;
	}
	public void setMovies(Set<Movie> movies) {
		this.movies = movies;
	}
	@Override
	public String toString() {
		return "Actor [aid=" + aid + ", aname=" + aname + ", agender=" + agender + ", movies=" + movies + "]";
	}
	
	
}
