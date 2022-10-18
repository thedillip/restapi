package com.rest.api.service;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rest.api.entity.Book;
import com.rest.api.repository.BookRepository;
import com.rest.api.request.BookRequest;
import com.rest.api.util.ProjectConstant;

@Service
public class BookServiceImpl implements BookService {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private BookRepository bookRepository;

	@Override
	public Book createBook(BookRequest bookDto) {
		Book book = modelMapper.map(bookDto, Book.class);
		return bookRepository.save(book);
	}

	@Override
	public List<Book> getAllBooks() {
		return bookRepository.findAll();
	}

	@Override
	public Book getBookByBookId(long bookId) {
		Optional<Book> optionalBook = bookRepository.findById(bookId);
		return optionalBook.orElse(null);
	}

	@Override
	public Book updateBookByBookId(long bookId, BookRequest bookRequest) {
		Book book = null;
		try {
			book = bookRepository.findByBookId(bookId);

			book.setBookName(bookRequest.getBookName());
			book.setBookDesc(bookRequest.getBookDesc());
			book.setAuthorName(bookRequest.getAuthorName());
			book.setIsbn(bookRequest.getIsbn());
			book.setPrice(bookRequest.getPrice());
			book.setPage(bookRequest.getPage());

			Book updatedBook = bookRepository.save(book);
			return updatedBook;
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public String deleteAllBooks() {
		bookRepository.deleteAll();
		return ProjectConstant.DELETED_MSG;
	}

	@Override
	public String deleteBookByBookId(long bookId) {
		bookRepository.deleteById(bookId);
		return ProjectConstant.DELETED_MSG;
	}

}
