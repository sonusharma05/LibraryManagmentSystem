package com.example.librarymanagement.repository;

import com.example.librarymanagement.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {

    Optional<Book> findByTitle(String title);

    List<Book> findByAuthor(String author);

    List<Book> findByAvailable(boolean available);
    
    List<Book> findByIsbn(String isbn);
    
    
}
