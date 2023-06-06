package com.example.app.services;

import com.example.app.models.LoginRequest;
import com.example.app.models.User;
import com.example.app.repositories.MovieRepository;
import com.example.app.repositories.UserRepository;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.crypto.SecretKey;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean validateLogin(LoginRequest loginRequest) {
        // Retrieve the user from the database based on the provided email
        User user = userRepository.findByEmail(loginRequest.getEmail());

        // Check if the user exists and if the password matches
        if (user != null && user.getPassword().equals(loginRequest.getPassword())) {
            return true; // Login is valid
        }

        return false; // Login is invalid
    }

    public String generateToken(LoginRequest loginRequest) {
        // Retrieve the user from the database based on the provided email
        User user = userRepository.findByEmail(loginRequest.getEmail());

        // Generate a secure key for HS256 algorithm
        SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

        // Generate a token using JWT
        String token = Jwts.builder()
                .setSubject(user.getEmail())
                .signWith(key)
                .compact();

        return token;
    }

    // ...
}
