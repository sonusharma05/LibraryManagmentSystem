package com.example.librarymanagement.service;

import com.example.librarymanagement.dto.UserDTO;
import com.example.librarymanagement.entity.User;
import com.example.librarymanagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;

    private UserDTO convertToDTO(User user) {
        return new UserDTO(user.getId(), user.getName(), user.getEmail(), user.getRole());
    }

    private User convertToEntity(UserDTO dto) {
        User user = new User();
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setRole(dto.getRole());
        // Set default password (should be hashed in real apps)
        //user.setPassword("default123"); // or from another DTO
        return user;
    }
    @Override
    public UserDTO createUser(UserDTO userDTO) {
        User user = new User();
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());

        // 🔐 Hash the raw password
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));

        user.setRole(userDTO.getRole().toUpperCase()); // "ADMIN", "MEMBER", etc.

        User savedUser = userRepository.save(user);
        return convertToDTO(savedUser);
    }

    

    @Override
    public UserDTO registerUser(UserDTO userDTO) {
        User user = new User();

        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());

        // 🔐 Hash the password before saving
        String hashedPassword = passwordEncoder.encode(userDTO.getPassword());
        user.setPassword(hashedPassword);

        user.setRole(userDTO.getRole().toUpperCase()); // e.g., "ADMIN", "MEMBER"

        User savedUser = userRepository.save(user);

        return convertToDTO(savedUser);
    }


    @Override
    public List<UserDTO> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public UserDTO getUserByEmail(String email) {
        User user = userRepository.findByEmail(email);
        return (user != null) ? convertToDTO(user) : null;
    }
    
    @Override
    public UserDTO getUserById(Long id) {
        User user = userRepository.findById(id).orElse(null);
        return (user != null) ? convertToDTO(user) : null;
    }

    @Override
    public UserDTO updateUser(Long id, UserDTO userDTO) {
        User existingUser = userRepository.findById(id).orElse(null);
        if (existingUser == null) {
            return null;
        }
        existingUser.setName(userDTO.getName());
        existingUser.setEmail(userDTO.getEmail());
       
        User updatedUser = userRepository.save(existingUser);
        return convertToDTO(updatedUser);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

	

}
