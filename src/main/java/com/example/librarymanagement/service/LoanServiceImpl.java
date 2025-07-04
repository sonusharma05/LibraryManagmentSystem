package com.example.librarymanagement.service;

import com.example.librarymanagement.dto.LoanDTO;
import com.example.librarymanagement.entity.Book;
import com.example.librarymanagement.entity.Loan;
import com.example.librarymanagement.entity.User;
import com.example.librarymanagement.repository.BookRepository;
import com.example.librarymanagement.repository.LoanRepository;
import com.example.librarymanagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LoanServiceImpl implements LoanService {

    @Autowired
    private LoanRepository loanRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookRepository bookRepository;

    
    @Override
    public LoanDTO issueBook(LoanDTO loanDTO) {
        Loan loan = convertToEntity(loanDTO);
        LocalDate today = LocalDate.now();
        loan.setIssueDate(today);
        loan.setExpectedReturnDate(today.plusDays(14));  // Set due date

        Loan savedLoan = loanRepository.save(loan);
        return convertToDTO(savedLoan);
    }


    @Override
    
    public LoanDTO returnBook(Long loanId) {
        Loan loan = loanRepository.findById(loanId)
                .orElseThrow(() -> new RuntimeException("Loan with ID " + loanId + " not found"));

        LocalDate returnDate = LocalDate.now();
        loan.setReturnDate(returnDate);

        LocalDate dueDate = loan.getExpectedReturnDate();
        long daysLate = ChronoUnit.DAYS.between(dueDate, returnDate);
        int fineAmount = (daysLate > 0) ? (int) daysLate * 10 : 0;

        loan.setFine(fineAmount);

        Loan updatedLoan = loanRepository.save(loan);
        return convertToDTO(updatedLoan);
    }



    @Override
    public List<LoanDTO> getAllLoans() {
        return loanRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<LoanDTO> getLoansByUser(Long userId) {
        return loanRepository.findByUserId(userId)
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<LoanDTO> getLoansByBook(Long bookId) {
        return loanRepository.findByBookId(bookId)
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // Helper: Convert Entity to DTO
    private LoanDTO convertToDTO(Loan loan) {
        LoanDTO dto = new LoanDTO();
        dto.setId(loan.getId());

        if (loan.getUser() != null) {
            dto.setUserId(loan.getUser().getId());
        }

        if (loan.getBook() != null) {
            dto.setBookId(loan.getBook().getId());
        }

        dto.setIssueDate(loan.getIssueDate());
        dto.setExpectedReturnDate(loan.getExpectedReturnDate());
        dto.setReturnDate(loan.getReturnDate());
        dto.setFine(loan.getFine());

        return dto;
    }


    // Helper: Convert DTO to Entity
    private Loan convertToEntity(LoanDTO dto) {
        Loan loan = new Loan();

        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new RuntimeException("User with ID " + dto.getUserId() + " not found"));

        Book book = bookRepository.findById(dto.getBookId())
                .orElseThrow(() -> new RuntimeException("Book with ID " + dto.getBookId() + " not found"));

        loan.setUser(user);
        loan.setBook(book);

        // Issue and expected return dates are set during issueBook()
        loan.setIssueDate(dto.getIssueDate());
        loan.setExpectedReturnDate(dto.getExpectedReturnDate());
        loan.setReturnDate(dto.getReturnDate());
        loan.setFine(dto.getFine());

        return loan;
    }

    
    //calulating fine
    @Override
    public LoanDTO calculateFine(Long loanId) {
        Loan loan = loanRepository.findById(loanId)
                .orElseThrow(() -> new RuntimeException("Loan with ID " + loanId + " not found"));

        java.time.LocalDate returnDate = loan.getReturnDate();
        java.time.LocalDate dueDate = loan.getIssueDate().plusDays(14);
        java.time.LocalDate effectiveReturnDate = (returnDate != null) ? returnDate : java.time.LocalDate.now();

        long daysLate = java.time.temporal.ChronoUnit.DAYS.between(dueDate, effectiveReturnDate);
        int fine = (daysLate > 0) ? (int) daysLate * 10 : 0;

        LoanDTO dto = convertToDTO(loan);
        dto.setFine(fine);  // Add this field in LoanDTO
        return dto;
    }
    //
    @Override
    public List<LoanDTO> getReturnedLoans() {
        return loanRepository.findAll().stream()
                .filter(loan -> loan.getReturnDate() != null)
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

   


}
