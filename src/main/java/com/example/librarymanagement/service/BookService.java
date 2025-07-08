package com.example.librarymanagement.service;

import com.example.librarymanagement.dto.BookDTO;

import java.util.List;
import java.util.Optional;

public interface BookService {
    BookDTO createBook(BookDTO bookDTO);
    List<BookDTO> getAllBooks();
    BookDTO getBookById(Long id);
    BookDTO updateBook(Long id, BookDTO bookDTO);
    void deleteBook(Long id);
    List<BookDTO> getBookByAuthor(String Author);
	List<BookDTO> getBookByAvailability(boolean available);
	Optional<BookDTO> getBookByIsbn(String isbn);
	List<BookDTO> getBookByTitle(String title);
    
}
