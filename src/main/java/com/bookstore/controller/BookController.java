package com.bookstore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bookstore.model.Book;
import com.bookstore.service.BookService;


@Controller
@RequestMapping("/book")
public class BookController {

	@Autowired
	private BookService bookService;
	
	@GetMapping("/addBook")
	public String addBook(Model model) {
		Book book = new Book();
		model.addAttribute("book", book);
		return "addBook";
	}
	
	@PostMapping("/addBook")
	public String addBookPost(@ModelAttribute("book") Book book, Model model, 
								@ModelAttribute("isbn") long isbn) {
		
		if(bookService.getBook(isbn).orElse(null)!=null) {
			model.addAttribute("isbnExists", true);
			return "addBook";
		}
		bookService.saveBook(book);
		return "redirect:bookList";
}
	
	
	@GetMapping("/bookList")
	public String bookList(Model model) 
	{
		List<Book> bookList = bookService.getAllBooks();
		model.addAttribute("bookList", bookList);		
		return "bookList";		
	}
	
	@GetMapping("/bookInfo")
	public String bookInfo(@RequestParam("isbn") Long isbn, Model model) {
		model.addAttribute("book", bookService.getBook(isbn).orElse(null));		
		return "bookInfo";
	}
	
	@GetMapping("/updateBook")
	public String updateBook(@RequestParam("isbn") Long isbn, Model model) {
		model.addAttribute("book", bookService.getBook(isbn).orElse(null));
		return "updateBook";
	}
	
	@PostMapping("/updateBook")
	public String updateBookPost(@ModelAttribute("book") Book book) {
		bookService.saveBook(book);
		return "redirect:bookList";
	}
	
	@GetMapping("/deleteBook")
	public String deleteBook(@RequestParam("isbn") Long isbn) {
		bookService.deleteBook(isbn);
		return "redirect:bookList";
	}
}
