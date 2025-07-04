package com.example.librarymanagement.controller;

import com.example.librarymanagement.dto.LoanDTO;
import com.example.librarymanagement.service.LoanService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController 
@RequestMapping("/api/loans")
@CrossOrigin(origins = "*") // Enable CORS for frontend
public class LoanController {

    @Autowired
    private LoanService loanService;

    

    // POST /api/loans/issue
    @PostMapping("/issue")
    public LoanDTO issueBook(@RequestBody LoanDTO loanDTO) {
        return loanService.issueBook(loanDTO);
    }

    // POST /api/loans/return/{loanId}
    @PostMapping("/return/{loanId}")
    public LoanDTO returnBook(@PathVariable Long loanId) {
        return loanService.returnBook(loanId);
    }

    // GET /api/loans
   
    @GetMapping
    public List<LoanDTO> getAllLoans() {
        return loanService.getAllLoans();
    }
    


    // GET /api/loans/user/{userId}
    @GetMapping("/user/{userId}")
    public List<LoanDTO> getLoansByUser(@PathVariable Long userId) {
        return loanService.getLoansByUser(userId);
    }

    // GET /api/loans/book/{bookId}
    @GetMapping("/book/{bookId}")
    public List<LoanDTO> getLoansByBook(@PathVariable Long bookId) {
        return loanService.getLoansByBook(bookId);
    }
    
    @GetMapping("/fine/{loanId}")
    public ResponseEntity<LoanDTO> getFine(@PathVariable Long loanId) {
        LoanDTO loanWithFine = loanService.calculateFine(loanId);
        return ResponseEntity.ok(loanWithFine);
    }
    @GetMapping("/returned")
    public List<LoanDTO> getAllReturnedLoans() {
        return loanService.getReturnedLoans();
    }

}
