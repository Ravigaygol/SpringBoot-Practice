package com.MovieMain.MovieModelData;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, Long> {
	
List<Movie>findByMrelease(boolean mrelease);
List<Movie>findByMname(String mname);
}
