package com.rest.api.service;

import java.util.List;

import com.rest.api.dto.BookDTO;
import com.rest.api.entity.Book;

public interface BookService {
	Book createBook(BookDTO bookDto);
	List<Book> getAllBooks();
	Book getBookByBookId(long bookId);
	Book updateBookByBookId(long bookId, BookDTO bookDto);
	String deleteAllBooks();
	String deleteBookByBookId(long bookId);
}
