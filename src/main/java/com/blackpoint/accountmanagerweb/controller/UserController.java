package com.blackpoint.accountmanagerweb.controller;

import com.blackpoint.accountmanagerweb.model.User;
import com.blackpoint.accountmanagerweb.model.requests.UserCreationRequest;
import com.blackpoint.accountmanagerweb.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
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
}
