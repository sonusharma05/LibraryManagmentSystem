package com.example.librarymanagement.service;

import com.example.librarymanagement.dto.BookDTO;
import com.example.librarymanagement.entity.Book;
import com.example.librarymanagement.repository.BookRepository;
import com.example.librarymanagement.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    private BookDTO convertToDTO(Book book) {
        BookDTO dto = new BookDTO();
        dto.setId(book.getId());
        dto.setTitle(book.getTitle());
        dto.setAuthor(book.getAuthor());
        dto.setIsbn(book.getIsbn());
        dto.setAvailable(book.isAvailable());
        return dto;
    }

    private Book convertToEntity(BookDTO dto) {
        Book book = new Book();
        book.setId(dto.getId());
        book.setTitle(dto.getTitle());
        book.setAuthor(dto.getAuthor());
        book.setIsbn(dto.getIsbn());
        book.setAvailable(dto.isAvailable());
        return book;
    }

    @Override
    public BookDTO createBook(BookDTO bookDTO) {
        Book saved = bookRepository.save(convertToEntity(bookDTO));
        return convertToDTO(saved);
    }

    @Override
    public List<BookDTO> getAllBooks() {
        return bookRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public BookDTO getBookById(Long id) {
        return bookRepository.findById(id)
                .map(this::convertToDTO)
                .orElse(null);
    }

    @Override
    public BookDTO updateBook(Long id, BookDTO bookDTO) {
        Book existing = bookRepository.findById(id).orElse(null);
        if (existing == null) return null;

        existing.setTitle(bookDTO.getTitle());
        existing.setAuthor(bookDTO.getAuthor());
        existing.setIsbn(bookDTO.getIsbn());
        existing.setAvailable(bookDTO.isAvailable());

        return convertToDTO(bookRepository.save(existing));
    }

    @Override
    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }

	@Override
	public List<BookDTO> getBookByAuthor(String Author) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BookDTO> getBookByAvaliable(Boolean bool) {
		// TODO Auto-generated method stub
		return null;
	}

}
