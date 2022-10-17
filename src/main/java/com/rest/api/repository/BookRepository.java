package com.rest.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rest.api.entity.Book;

public interface BookRepository extends JpaRepository<Book, Long> {
	Book findByBookId(long bookId);
}
