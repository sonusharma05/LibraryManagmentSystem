package com.example.librarymanagement;

import com.example.librarymanagement.entity.User;
import com.example.librarymanagement.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@SpringBootApplication
public class LibraryManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(LibraryManagementApplication.class, args);
	}

	@Bean
	public CommandLineRunner hashPasswordsAtStartup(UserRepository userRepository, PasswordEncoder passwordEncoder) {
		return args -> {
			List<User> users = userRepository.findAll();

			for (User user : users) {
				String rawPassword = user.getPassword();

				// Skip if already hashed
				if (!rawPassword.startsWith("$2a$") && !rawPassword.startsWith("$2b$")) {
					String hashed = passwordEncoder.encode(rawPassword);
					user.setPassword(hashed);
					userRepository.save(user);
					System.out.println("Hashed password for: " + user.getEmail());
				}
			}
		};
	}
}
