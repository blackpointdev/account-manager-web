package com.blackpoint.accountmanagerweb.controller;

import com.blackpoint.accountmanagerweb.exception.ResourceNotFoundException;
import com.blackpoint.accountmanagerweb.model.Operation;
import com.blackpoint.accountmanagerweb.model.User;
import com.blackpoint.accountmanagerweb.repository.OperationRepository;

import com.blackpoint.accountmanagerweb.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

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

    @GetMapping("/operations/{operationId}")
    public Operation getOperationById(@PathVariable Long operationId) {
        return operationRepository.findById(operationId)
                .orElseThrow(() -> new ResourceNotFoundException("Operation not found with given id: " + operationId));
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

    @PutMapping("/operations/{operationId}")
    public Operation updateOperations(@PathVariable Long operationId,
                                      @Valid @RequestBody Operation operationRequest) {
        Optional<Operation> optionalOperation = operationRepository.findById(operationId);

        return optionalOperation.map(operation -> {
           operation.setName(operationRequest.getName());
           operation.setBalance(operationRequest.getBalance());
           if (operationRequest.getUser() != null) {
               operation.setUser(operationRequest.getUser());
           }
           return operationRepository.save(operation);
        }).orElseThrow(() -> new ResourceNotFoundException("Operation not found with id: " + operationId));
    }

    @DeleteMapping("/operations/{operationId}")
    public ResponseEntity<?> deleteOperation(@PathVariable Long operationId) {
        Optional<Operation> optionalOperation = operationRepository.findById(operationId);

        return optionalOperation.map(operation -> {
            operationRepository.delete(operation);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("Operation not found with id: " + operationId));
    }
}
