package com.MovieMain.MovieModelData;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mcnt")
public class Controller {

	@Autowired
	MovieRepository movieRepo;

	/*
	 * Passing Data through QueryString
	 * http://localhost:8080/api/tutorials?titile=xyz
	 * 
	 * http://localhost:8080/api/tutorials
	 */
	@GetMapping("/movies")
	public ResponseEntity<List<Movie>> getMovieByName(@RequestParam(required = false) String mname) {
		List<Movie> mlist = new ArrayList<>();
		try {
			if (mname == null) {
				movieRepo.findAll().forEach(mlist::add);
			} else {
				movieRepo.findByMname(mname).forEach(mlist::add);
			}
			if (mlist.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<List<Movie>>(mlist, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/movies/{mid}")
	public ResponseEntity<Movie> getMovieById(@PathVariable("mid") long mid)
	{
		Optional<Movie> movieop=movieRepo.findById(mid);
		if(movieop.isPresent())
		{
			return new ResponseEntity<>(movieop.get(),HttpStatus.OK);
		}
		else
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	@PostMapping("/movies")
	public ResponseEntity<Movie> addMovie(@RequestBody Movie movie)
	{
		try
		{
			Movie m=movieRepo.save(new Movie(movie.getMid(), movie.getMname(), movie.isMrelease(),new Actor(movie.getActors().getAid(), movie.getActors().getAname(), movie.getActors().getAgender())));
			return new ResponseEntity<>(m,HttpStatus.CREATED);
		}
		catch(Exception e)
		{
			return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping("/movies/{mid}")
	public ResponseEntity<Movie> updateMovie(@PathVariable("mid") long mid,@RequestBody Movie movie)
	{
		Optional<Movie> op=movieRepo.findById(mid);
		if(op.isPresent())
		{
			Movie m=op.get();
			m.setMid(movie.getMid());
			m.setMname(movie.getMname());
			m.setMrelease(movie.isMrelease());
			return new ResponseEntity<>(m,HttpStatus.OK);
		}
		else
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@DeleteMapping("/movies/{mid}")
	public ResponseEntity<HttpStatus>deleteMovie(@PathVariable("mid") long mid)
	{
		try
		{
			movieRepo.deleteById(mid);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		catch(Exception e)
		{
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@DeleteMapping("/movies")
	public ResponseEntity<HttpStatus>deleteAllMovies()
	{
		try
		{
			movieRepo.deleteAll();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		catch(Exception e)
		{
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@GetMapping("/movies/{mrelease}")
	public ResponseEntity<List<Movie>>findByRelease()
	{
		try
		{
	List<Movie> mlist=movieRepo.findByMrelease(true);
	if(mlist.isEmpty())
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	return new ResponseEntity<>(HttpStatus.OK); 
		}
		catch(Exception e)
		{
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
}
