package com.blackpoint.accountmanagerweb.controller;

import com.blackpoint.accountmanagerweb.exception.ResourceNotFoundException;
import com.blackpoint.accountmanagerweb.model.User;
import com.blackpoint.accountmanagerweb.model.requests.UserCreationRequest;
import com.blackpoint.accountmanagerweb.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired //TODO Maybe change to some kind of constructor injection?
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/users")
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @PostMapping("/users")
    public User addUser(@Valid @RequestBody UserCreationRequest userCreationRequest) {
        User user = new User();
        user.setUsername(userCreationRequest.getUsername());
        user.setEmail(userCreationRequest.getEmail());
        user.setHashedPassword(passwordEncoder.encode(userCreationRequest.getPassword()));

        return userRepository.save(user);
    }

    @GetMapping("/users/{username}")
    public User getUserByUsername(@PathVariable String username) { return userRepository.findByUsername(username); }

    @PutMapping("/users/{username}")
    public User updateUser(@PathVariable String username,
                           @Valid @RequestBody User userRequest) {
        User user = userRepository.findByUsername(username);
        if (user == null) throw new ResourceNotFoundException("Username not found with username:" + username);

        user.setEmail(userRequest.getEmail());
        user.setUsername(userRequest.getUsername());

        return userRepository.save(user);
    }

    @DeleteMapping("/users/{username")
    public ResponseEntity<?> deleteUser(@RequestParam String username) {
        User user = userRepository.findByUsername(username);
        if (user == null) throw new ResourceNotFoundException("Username not found with username: " + username);

        userRepository.delete(user);
        return ResponseEntity.ok().build();
    }
}
