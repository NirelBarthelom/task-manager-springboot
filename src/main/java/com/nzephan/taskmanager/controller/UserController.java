package com.nzephan.taskmanager.controller;

import com.nzephan.taskmanager.entity.User;
import com.nzephan.taskmanager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestController  // Marks this class as a REST API controller
@RequestMapping("/api/users")  // Base URL for all user-related endpoints
public class UserController {

    @Autowired
    private UserService userService;

    // API endpoint: POST /api/users/login
    @PostMapping("/loginForm")
    public ResponseEntity<?> login(@RequestBody Map<String, String> credentials) {
        String usernameOrEmail = credentials.get("usernameOrEmail");
        String password = credentials.get("password");

        System.out.println("Login attempt: " + usernameOrEmail);

        // Ask your service to validate and fetch the user
        User user = userService.validateLogin(usernameOrEmail, password);

        if (user != null) {
            Map<String, String> response = new HashMap<>();
            response.put("username", user.getUsername());
            response.put("role", user.getRole()); // e.g. "ADMIN" or "USER"

            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(401).body("Invalid credentials");
        }
    }


    // API endpoint: POST /api/users/register
    @PostMapping("/registerForm")
    public ResponseEntity<User> register(@RequestBody User user) {
        User savedUser = userService.saveUser(user);
        return ResponseEntity.ok(savedUser);
    }
}
