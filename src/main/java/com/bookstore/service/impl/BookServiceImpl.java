package com.bookstore.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookstore.model.Book;
import com.bookstore.repository.BookRepository;
import com.bookstore.service.BookService;

@Service
public class BookServiceImpl implements BookService{
	@Autowired
	private BookRepository bookRepository;
	
	@Override
	public void saveBook(Book b) {
		bookRepository.save(b);
	}

	@Override
	public List<Book> getAllBooks() {
		return (List<Book>) bookRepository.findAll();
	}
	
	@Override
	public Optional<Book> getBook(Long isbn) {
		return bookRepository.findById(isbn);
	}

	@Override
	public void deleteBook(Long isbn) {
		bookRepository.deleteById(isbn);
	}

}
