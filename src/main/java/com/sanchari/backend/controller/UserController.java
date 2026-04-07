package com.sanchari.backend.controller;

import com.sanchari.backend.model.User;
import com.sanchari.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "http://localhost:5173")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    // READ: Get all users for the Admin table
    @GetMapping
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // DELETE: Remove a user by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        return userRepository.findById(id)
                .map(user -> {
                    userRepository.delete(user);
                    return ResponseEntity.ok().body("User deleted successfully");
                })
                .orElse(ResponseEntity.notFound().build());
    }
}