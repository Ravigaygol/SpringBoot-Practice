package com.BookMain.Models;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "authors")
public class Author extends BaseEntity {

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "age", nullable = false)
    private Integer age;

    @Column(name = "birth_date", nullable = false)
    private LocalDate birthDate;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(
            name = "author_id",
            nullable = false,
            foreignKey = @ForeignKey(
                    name = "fk_books_authors_id"
           )
    )
    private List<Book> books = new ArrayList<>();

	public Author() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Author(String name, Integer age, LocalDate birthDate, List<Book> books) {
		super();
		this.name = name;
		this.age = age;
		this.birthDate = birthDate;
		this.books = books;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public LocalDate getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}

	public List<Book> getBooks() {
		return books;
	}

	public void setBooks(List<Book> books) {
		this.books = books;
	}

	@Override
	public String toString() {
		return "Author [name=" + name + ", age=" + age + ", birthDate=" + birthDate + ", books=" + books + "]";
	}
}