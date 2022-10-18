package com.rest.api.graphqlquery;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.rest.api.entity.Book;
import com.rest.api.request.BookRequest;
import com.rest.api.service.BookService;

@Controller
@CrossOrigin
public class BookQuery {

	@Autowired
	private BookService bookService;

	@MutationMapping(value = "createBook")
	public Book createBook(@Argument(name = "bookRequest") BookRequest bookRequest) {
		return bookService.createBook(bookRequest);
	}

	@QueryMapping(value = "books")
	public List<Book> getAllBooks() {
		return bookService.getAllBooks();
	}

	@QueryMapping(value = "book")
	public Book getBookByBookId(@Argument(name = "bookId") long bookId) {
		return this.bookService.getBookByBookId(bookId);
	}

	@MutationMapping(value = "updateBookByBookId")
	public Book updateBookByBookId(@Argument(name = "bookId") long bookId,
			@Argument(name = "bookRequest") BookRequest bookRequest) {
		return this.bookService.updateBookByBookId(bookId, bookRequest);
	}

	@MutationMapping(value = "deleteAllBooks")
	public String deleteAllBooks() {
		return this.bookService.deleteAllBooks();
	}

	@MutationMapping(value = "deleteBookByBookId")
	public String deleteBookByBookId(@Argument(name = "bookId") long bookId) {
		return this.bookService.deleteBookByBookId(bookId);
	}
}
