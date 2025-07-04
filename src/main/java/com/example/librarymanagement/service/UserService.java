	package com.example.librarymanagement.service;
	
	import com.example.librarymanagement.dto.UserDTO;
	import java.util.List;
	
	public interface UserService {
	    UserDTO createUser(UserDTO userDTO);
	    List<UserDTO> getAllUsers();
	    UserDTO getUserById(Long id);        
	    UserDTO getUserByEmail(String email);
	    UserDTO updateUser(Long id, UserDTO userDTO);  
	    void deleteUser(Long id);  
	    UserDTO registerUser(UserDTO userDTO);
	}
