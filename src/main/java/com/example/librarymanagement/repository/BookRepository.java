package com.example.librarymanagement.repository;

import com.example.librarymanagement.dto.BookDTO;
import com.example.librarymanagement.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {

	List<Book> findByAuthor(String author);

	List<Book> findByAvailable(boolean available);

	Optional<Book> findByIsbn(String isbn);

	List<Book> findByTitleContainingIgnoreCase(String title);

}
