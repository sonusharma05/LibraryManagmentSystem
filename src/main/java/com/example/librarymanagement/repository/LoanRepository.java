package com.example.librarymanagement.repository;

import com.example.librarymanagement.entity.Loan;
import com.example.librarymanagement.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface LoanRepository extends JpaRepository<Loan, Long> {
    List<Loan> findByUserId(Long userId);
    List<Loan> findByBookId(Long bookId);
    List<Loan> findByUser(User user);
}
