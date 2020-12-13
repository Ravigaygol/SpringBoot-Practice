package com.BookMain.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.BookMain.Exception.ResourceNotFoundException;
import com.BookMain.Models.Author;
import com.BookMain.Models.Book;
import com.BookMain.Repository.AuthorRepository;

import java.util.Optional;

@RestController
public class AuthorResource {

	@Autowired
	private final AuthorRepository authorRepository;

	public AuthorResource(AuthorRepository authorRepository) {
		super();
		this.authorRepository = authorRepository;
	}

	@GetMapping("/authors")
	public Page<Author> getAllAuthors(Pageable pageable) {
		return authorRepository.findAll(pageable);
	}

	@PostMapping("/authors")
	public ResponseEntity<Author> saveAuthor( @RequestBody Author request) {
		try
		{
			Author a=authorRepository.save(request);
			return new ResponseEntity<>(a,HttpStatus.CREATED);
		}
		catch(Exception e)
		{
			return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/authors/{authorId}")
	public ResponseEntity<Author> updateAuthor(@PathVariable final Long authorId,  @RequestBody Author request) {
		

		Author author = authorRepository.findById(authorId)
				.orElseThrow(() -> new ResourceNotFoundException("authorId " + authorId + " not found"));

		Optional<Author> op=authorRepository.findById(authorId);   
		if(op.isPresent())
		{
			Author a=op.get();
			a.setAge(request.getAge());
			a.setBirthDate(request.getBirthDate());
			a.setName(request.getName());
			authorRepository.save(request);
			return new ResponseEntity<>(a,HttpStatus.OK);
		}
		else
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@DeleteMapping("/authors/{authorId}")
	public ResponseEntity<?> deleteAuthor(@PathVariable Long authorId) {
		return authorRepository.findById(authorId).map(author -> {
			authorRepository.delete(author);
			return ResponseEntity.ok().build();
		}).orElseThrow(() -> new ResourceNotFoundException("AuthorID " + authorId + " not found"));
	}
}