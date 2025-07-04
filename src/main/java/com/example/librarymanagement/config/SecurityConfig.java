package com.example.librarymanagement.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.Customizer;


@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
	    http
	        .csrf(csrf -> csrf.disable())
	        .authorizeHttpRequests(auth -> auth
	            .requestMatchers("/", "/login", "/register", "/register/**").permitAll() // public pages
	            .requestMatchers("/api/auth/**","/api/loan/issue").permitAll()
	            .requestMatchers("/api/admin/**", "/admin/**").hasRole("ADMIN")   // Admin pages and APIs
	            .requestMatchers("/api/member/**", "/member/**").hasRole("MEMBER") // Member pages and APIs
	            .requestMatchers("/api/common/**").hasAnyRole("ADMIN", "MEMBER")  // Shared endpoints
	            .anyRequest().authenticated()
	        )
	        .formLogin(form -> form
	            .loginPage("/login") // Custom login page
	            .defaultSuccessUrl("/dashboard", true) // Redirect after login
	            .permitAll()
	        )
	        .logout(logout -> logout
	            .logoutSuccessUrl("/login?logout")
	            .permitAll()
	        );

	    return http.build();
	}

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
