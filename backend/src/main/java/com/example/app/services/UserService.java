package com.example.app.services;

import com.example.app.models.LoginRequest;
import com.example.app.models.User;
import com.example.app.repositories.UserRepository;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.crypto.SecretKey;
import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public boolean validateLogin(LoginRequest loginRequest) {
        User user = userRepository.findByEmail(loginRequest.getEmail());

        if (user != null && user.getPassword().equals(loginRequest.getPassword())) {
            return true;
        }
        return false;
    }

    public String generateToken(LoginRequest loginRequest) {
        User user = userRepository.findByEmail(loginRequest.getEmail());

        SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

        String token = Jwts.builder()
                .setSubject(user.getEmail())
                .signWith(key)
                .compact();

        return token;
    }
}
