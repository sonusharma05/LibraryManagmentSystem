package com.example.librarymanagement.service;

import com.example.librarymanagement.dto.BookDTO;

import java.util.List;

public interface BookService {
    BookDTO createBook(BookDTO bookDTO);
    List<BookDTO> getAllBooks();
    BookDTO getBookById(Long id);
    BookDTO updateBook(Long id, BookDTO bookDTO);
    void deleteBook(Long id);
    List<BookDTO> getBookByAuthor(String Author);
    List<BookDTO>getBookByAvaliable(Boolean bool);
    
}
