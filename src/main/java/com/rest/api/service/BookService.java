package com.rest.api.service;

import java.util.List;

import com.rest.api.entity.Book;
import com.rest.api.request.BookRequest;

public interface BookService {
	Book createBook(BookRequest bookRequest);
	List<Book> getAllBooks();
	Book getBookByBookId(long bookId);
	Book updateBookByBookId(long bookId, BookRequest bookRequest);
	String deleteAllBooks();
	String deleteBookByBookId(long bookId);
}
