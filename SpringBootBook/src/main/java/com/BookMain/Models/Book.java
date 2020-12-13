package com.BookMain.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "books")
public class Book extends BaseEntity {
    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "isbn", nullable = false)
    private String isbn;

    @Column(name = "page_count", nullable = false)
    private Integer pageCount;

    @Column(name = "published_date", nullable = false)
    private LocalDate publishedDate;

    @ManyToOne(
            fetch = FetchType.LAZY,
            optional = false
    )
    @JoinColumn(
            name = "author_id",
            nullable = false,
            foreignKey = @ForeignKey(
                    name = "fk_books_authors_id"
            )
    )
    @JsonIgnore
    private Author author;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public Integer getPageCount() {
		return pageCount;
	}

	public void setPageCount(Integer pageCount) {
		this.pageCount = pageCount;
	}

	public LocalDate getPublishedDate() {
		return publishedDate;
	}

	public void setPublishedDate(LocalDate publishedDate) {
		this.publishedDate = publishedDate;
	}

	public Author getAuthor() {
		return author;
	}

	public void setAuthor(Author author) {
		this.author = author;
	}

	public Book() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Book(String title, String isbn, Integer pageCount, LocalDate publishedDate, Author author) {
		super();
		this.title = title;
		this.isbn = isbn;
		this.pageCount = pageCount;
		this.publishedDate = publishedDate;
		this.author = author;
	}

	@Override
	public String toString() {
		return "Book [title=" + title + ", isbn=" + isbn + ", pageCount=" + pageCount + ", publishedDate="
				+ publishedDate + "]";
	}
    
}