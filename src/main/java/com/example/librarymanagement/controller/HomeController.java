package com.example.librarymanagement.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class HomeController {
	
	@GetMapping("/")
    public String homePage() {
        return "home.html";
        
    }
	@GetMapping("/dashboard")
	public String dashboard() {
		return "dashboard.html";
		
	}
	@GetMapping("/login")
    public String loginPage() {
        return "login"; 
    }

}
