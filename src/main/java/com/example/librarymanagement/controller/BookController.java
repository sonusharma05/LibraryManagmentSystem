package com.example.librarymanagement.controller;

import com.example.librarymanagement.dto.BookDTO;
import com.example.librarymanagement.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/books")
@CrossOrigin("*") // Enables frontend access (for future integration)
public class BookController {

    @Autowired
    private BookService bookService;

    @PostMapping
    public BookDTO createBook(@RequestBody BookDTO bookDTO) {
        return bookService.createBook(bookDTO);
    }

    @GetMapping
    public List<BookDTO> getAllBooks() {
        return bookService.getAllBooks();
    }

    @GetMapping("/{id}")
    public BookDTO getBookById(@PathVariable Long id) {
        return bookService.getBookById(id);
    }

    @PutMapping("/{id}")
    public BookDTO updateBook(@PathVariable Long id, @RequestBody BookDTO bookDTO) {
        return bookService.updateBook(id, bookDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
    }
    
    @GetMapping("/author/{author}")
    public List<BookDTO> getBookByAuthor(@PathVariable("author") String author) {
        return bookService.getBookByAuthor(author);
    }
   

    // Get books by availability
    @GetMapping("/available/{available}")
    public List<BookDTO> getByAvailability(@PathVariable boolean available) {
        return bookService.getBookByAvailability(available);
    }

    // Get book by ISBN (returns one or 404)
    @GetMapping("/isbn/{isbn}")
    public ResponseEntity<BookDTO> getByIsbn(@PathVariable String isbn) {
        return bookService.getBookByIsbn(isbn)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Get books by title (partial match, case-insensitive)
    @GetMapping("/title/{title}")
    public List<BookDTO> getByTitle(@PathVariable String title) {
        return bookService.getBookByTitle(title);
    }
}
