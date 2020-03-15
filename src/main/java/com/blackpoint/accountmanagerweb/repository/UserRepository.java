package com.blackpoint.accountmanagerweb.repository;

import com.blackpoint.accountmanagerweb.model.User;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends PagingAndSortingRepository<User, Long> {
    User findByUsername(String username);
}
