package com.example.librarymanagement.service;

import com.example.librarymanagement.dto.LoanDTO;
import java.util.List;

public interface LoanService {
    LoanDTO issueBook(LoanDTO loanDTO);
    LoanDTO returnBook(Long loanId);
    List<LoanDTO> getAllLoans();
    List<LoanDTO> getLoansByUser(Long userId);
    List<LoanDTO> getLoansByBook(Long bookId);
    LoanDTO calculateFine(Long loanId);
    List<LoanDTO> getReturnedLoans();
    
}
