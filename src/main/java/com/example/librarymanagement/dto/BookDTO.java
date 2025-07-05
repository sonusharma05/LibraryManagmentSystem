package com.example.librarymanagement.dto;

import java.util.List;

public class BookDTO {
    private Long id;
    private String title;
    private String author;
    private String isbn;
    private boolean available;

    private List<Long> loanIds;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public List<Long> getLoanIds() {
		return loanIds;
	}

	public void setLoanIds(List<Long> loanIds) {
		this.loanIds = loanIds;
	}

	public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

   
    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
}
	