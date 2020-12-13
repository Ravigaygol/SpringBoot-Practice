package com.BookMain.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.BookMain.Models.Author;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
}