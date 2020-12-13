package com.BookMain.Resource;

import java.util.Optional;

import org.apache.catalina.realm.AuthenticatedUserRealm;
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
import com.BookMain.Repository.BookRepository;

@RestController
public class BookResource {
	@Autowired
	private final AuthorRepository authorRepository;
	@Autowired
	private final BookRepository bookRepository;

	public BookResource(AuthorRepository authorRepository, BookRepository bookRepository) {
		super();
		this.authorRepository = authorRepository;
		this.bookRepository = bookRepository;
	}

	@GetMapping("/authors/{authorId}/books")
	public Page<Book> getAllBooksByAuthorId(@PathVariable(value = "authorId") Long authorId, Pageable pageable) {
		return bookRepository.findByAuthorId(authorId, pageable);
	}

	@PostMapping("/authors/{authorId}/books")
	public ResponseEntity<Book> createBook(@PathVariable(value = "authorId") Long authorId,
			 @RequestBody Book bookRequest) {
		try
		{
			Book b=bookRepository.save(bookRequest);
			return new ResponseEntity<>(b,HttpStatus.CREATED);
		}
		catch(Exception e)
		{
			return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/authors/{authorId}/books/{bookId}")
	public ResponseEntity<Book> updateBook(@PathVariable(value = "authorId") Long authorId,
			@PathVariable(value = "bookId") Long bookId, @RequestBody Book bookRequest) {

		Author author = authorRepository.findById(authorId)
				.orElseThrow(() -> new ResourceNotFoundException("authorId " + authorId + " not found"));

		Optional op=bookRepository.findByIdAndAuthorId(bookId, authorId);
		if(op.isPresent())
		{
			Book b=(Book) op.get();
			b.setIsbn(bookRequest.getIsbn());
			b.setPageCount(bookRequest.getPageCount());
			b.setPublishedDate(bookRequest.getPublishedDate());
			b.setTitle(bookRequest.getTitle());
			b.setAuthor(author);
			bookRepository.save(bookRequest);
			return new ResponseEntity<>(b,HttpStatus.OK);
		}
		else
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@DeleteMapping("/authors/{authorId}/books/{bookId}")
	public ResponseEntity<?> deleteBook(@PathVariable(value = "authorId") Long authorId,
			@PathVariable(value = "bookId") Long bookId) {
		return bookRepository.findByIdAndAuthorId(bookId, authorId).map(book -> {
			bookRepository.delete(book);
			return ResponseEntity.ok().build();
		}).orElseThrow(
				() -> new ResourceNotFoundException("Book not found with id " + bookId + " and authorId " + authorId));
	}
}