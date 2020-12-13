package com.MovieMain.MovieModelData;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="Movie_Data")
public class Movie {

	@Id
	@GeneratedValue(strategy =GenerationType.AUTO)
	private long mid;
	@Column(name="mname")
	private String mname;
	@Column(name="mrelease")
	private boolean mrelease;
	@ManyToOne
	@JoinColumn(name="aid")
	private Actor actors;
	public Movie() {}
	
	public Movie(long mid, String mname, boolean mrelease, Actor actors) {
		super();
		this.mid = mid;
		this.mname = mname;
		this.mrelease = mrelease;
		this.actors = actors;
	}

	public long getMid() {
		return mid;
	}
	public void setMid(long mid) {
		this.mid = mid;
	}
	public String getMname() {
		return mname;
	}
	public void setMname(String mname) {
		this.mname = mname;
	}
	public boolean isMrelease() {
		return mrelease;
	}
	public void setMrelease(boolean mrelease) {
		this.mrelease = mrelease;
	}
	
	public Actor getActors() {
		return actors;
	}
	public void setActors(Actor actors) {
		this.actors = actors;
	}
	@Override
	public String toString() {
		return "Movie [mid=" + mid + ", mname=" + mname + ", mrelease=" + mrelease + ", actors=" + actors + "]";
	}
	
}
