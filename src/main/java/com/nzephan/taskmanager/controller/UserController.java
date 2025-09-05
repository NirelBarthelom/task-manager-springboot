package com.nzephan.taskmanager.controller;

import com.nzephan.taskmanager.entity.User;
import com.nzephan.taskmanager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController  // Marks this class as a REST API controller
@RequestMapping("/api/users")  // Base URL for all user-related endpoints
public class UserController {

    @Autowired
    private UserService userService;

    // API endpoint: POST /api/users/login
    @PostMapping("/loginForm")
    public ResponseEntity<String> login(@RequestBody java.util.Map<String, String> credentials) {
        String usernameOrEmail = credentials.get("usernameOrEmail");
        String password = credentials.get("password");
        System.out.println(usernameOrEmail + "\n" + password);

        //boolean valid = userService.validateLogin(usernameOrEmail, password);
        boolean valid = true;
        if (valid) {
            return ResponseEntity.ok("Login successful");
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
