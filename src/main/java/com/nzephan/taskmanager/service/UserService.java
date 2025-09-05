// This class handles the business logic related to Users.
// It talks to the UserRepository to fetch/save users from the database.
package com.nzephan.taskmanager.service;

import com.nzephan.taskmanager.entity.User;               // Import User entity (maps to User table)
import com.nzephan.taskmanager.repository.UserRepository; // Import Repository for database access
import org.springframework.beans.factory.annotation.Autowired; // Allows automatic injection of repository instance
import org.springframework.stereotype.Service;          // Marks this class as a Service (business logic layer)

@Service  // Tells Spring Boot to treat this class as a Service component
public class UserService {

    @Autowired  // Spring injects (provides) the UserRepository automatically
    private UserRepository userRepository;

    // Fetches a User from the database by username
    public User findByUsername(String username) {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new IllegalArgumentException("User not found with username: " + username);
        }
        return user;
    }

    // Saves a User object to the database (used for registering new users, etc.)
    public User saveUser(User requestUser) {

        User newUser = new User(
                requestUser.getUsername(),
                requestUser.getEmail(),
                requestUser.getPassword(),
                requestUser.getRole()
        );

        return userRepository.save(newUser);  // save() is auto-provided by JpaRepository
    }

    // Validates login by checking if username and password match a database record
    public boolean validateLogin(String usernameOrEmail, String password) {
        User user;

        // Check if input contains '@' → treat as email
        if (usernameOrEmail.contains("@")) {
            user = userRepository.findByEmail(usernameOrEmail);
        } else {
            user = userRepository.findByUsername(usernameOrEmail);
        }

        if (user == null) {
            return false;
        }
        return user.getPassword().equals(password);
    }
}
