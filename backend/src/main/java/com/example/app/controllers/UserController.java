package com.example.app.controllers;

import com.example.app.models.LoginRequest;
import com.example.app.models.LoginResponse;
import com.example.app.models.Movie;
import com.example.app.models.User;
import com.example.app.repositories.UserRepository;
import com.example.app.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;

    @PostMapping
    public User createUser(@RequestBody User user) {
        return userRepository.save(user);
    }

    @GetMapping
    public List<User> getAllMovies() {
        List<User> users = userService.getAllUsers();
        return users;
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") Long id) {
        Optional<User> optionalUser = userRepository.findById(Math.toIntExact(id));
        if (optionalUser.isPresent()) {
            return ResponseEntity.ok(optionalUser.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable("id") Long id, @RequestBody User user) {
        Optional<User> optionalUser = userRepository.findById(Math.toIntExact(id));
        if (optionalUser.isPresent()) {
            User existingUser = optionalUser.get();
            existingUser.setName(user.getName());
            existingUser.setEmail(user.getEmail());
            User updatedUser = userRepository.save(existingUser);
            return ResponseEntity.ok(updatedUser);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable("id") Long id) {
        Optional<User> optionalUser = userRepository.findById(Math.toIntExact(id));
        if (optionalUser.isPresent()) {
            userRepository.delete(optionalUser.get());
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        // Validate the user's credentials and generate a token or session

        // Assuming you have a UserService with a method to validate the login
        boolean isValidLogin = userService.validateLogin(loginRequest);

        if (isValidLogin) {
            // Generate a token or session and return it in the response
            String token = userService.generateToken(loginRequest);
            return ResponseEntity.ok().body(new LoginResponse(token));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password");
        }
    }
}
