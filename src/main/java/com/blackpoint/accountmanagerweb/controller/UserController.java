package com.blackpoint.accountmanagerweb.controller;

import com.blackpoint.accountmanagerweb.model.User;
import com.blackpoint.accountmanagerweb.model.UserCreationRequest;
import com.blackpoint.accountmanagerweb.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class UserController {

    @Autowired //TODO Maybe change to some kind of constructor injection?
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/users")
    public Page<User> getUsers(Pageable pageable) {
        return userRepository.findAll(pageable);
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
