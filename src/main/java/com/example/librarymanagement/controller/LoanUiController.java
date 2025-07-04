package com.example.librarymanagement.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/loans")
public class LoanUiController {
	
	@GetMapping("/issue")
    public String showIssuePage() {
        return "issue"; // loads templates/issue.html
    }
	
	@GetMapping("/issue-records")
    public String showIssuedRecordsPage() {
        return "issue-records"; // Resolves to templates/issue-records.html
    }
	@GetMapping("/return")
	public String returnbook() {
		return "returnbook.html";
		
	}

}
