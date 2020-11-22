package com.bookstore.service;

import java.util.List;
import java.util.Optional;

import com.bookstore.model.Book;

public interface BookService {

	void saveBook(Book b);

	List<Book> getAllBooks();
	
	Optional<Book> getBook(Long isbn);
	
	void deleteBook(Long isbn);

}
