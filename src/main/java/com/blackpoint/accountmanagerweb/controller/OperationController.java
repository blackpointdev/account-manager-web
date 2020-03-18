package com.blackpoint.accountmanagerweb.controller;

import com.blackpoint.accountmanagerweb.exception.ResourceNotFoundException;
import com.blackpoint.accountmanagerweb.model.Operation;
import com.blackpoint.accountmanagerweb.model.User;
import com.blackpoint.accountmanagerweb.repository.OperationRepository;

import com.blackpoint.accountmanagerweb.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class OperationController {

    @Autowired
    private OperationRepository operationRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/operations")
    public Page<Operation> getAllOperations(Pageable pageable) {
        return operationRepository.findAll(pageable);
    }

    @GetMapping("/users/{username}/operations")
    public Page<Operation> getOperationsByUsername(@PathVariable String username, Pageable pageable) {
        return operationRepository.findByUserUsername(username, pageable);
    }

    @PostMapping("/users/{username}/operations")
    public Operation addOperation(@Valid @RequestBody Operation operationRequest,
                                  @PathVariable String username) {
        User user = userRepository.findByUsername(username);
        if (user == null) throw new ResourceNotFoundException("User not found with given username: " + username);

        operationRequest.setUser(user);
        return operationRepository.save(operationRequest);
    }
}
