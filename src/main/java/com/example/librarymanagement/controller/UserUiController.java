package com.example.librarymanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.librarymanagement.dto.UserDTO;
import com.example.librarymanagement.service.UserService;

@Controller
@RequestMapping("/api/users")

public class UserUiController {
	
	@Autowired
	 private UserService userService;
	
	 @GetMapping("/view")
    public String userView() {
    
        return "user-view"; // Maps to templates/user-list.html
    }
	 @GetMapping("/allview")
	 public String viewAll() {
		 return "view-user.html";
		 
	 }
	 @GetMapping("/create")
	 public String addUser() {
		 return "createuser.html";
		 
	 } @GetMapping("/delete")
	 public String DeleteUser() {
		 return "delete-user.html";
		 
	 }
	 @GetMapping("/update")
	 public String updateUser() {
		 return "userUpdate.html";
	 }
	 

}
