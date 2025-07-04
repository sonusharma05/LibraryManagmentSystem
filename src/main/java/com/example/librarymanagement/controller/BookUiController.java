package com.example.librarymanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.librarymanagement.service.BookService;

@Controller
@RequestMapping("/api/books")
public class BookUiController {
	
	@Autowired
    private BookService bookService;
	
	@GetMapping("/view")
	public String books() {
		return "books.html";
	}
	
	@GetMapping("/create")
	public String createBook() {
		return "CreateBook.html";
		
	}
	@GetMapping("/allview")
	public String viewAllBooks() {
		return "view-book.html";
		
	}
	@GetMapping("/delete")
	public String deleteBook() {
		return"delete-book.html";
	}
	

}
