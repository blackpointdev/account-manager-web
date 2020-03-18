package com.blackpoint.accountmanagerweb.repository;

import com.blackpoint.accountmanagerweb.model.Operation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface OperationRepository extends PagingAndSortingRepository<Operation, Long> {
    Page<Operation> findByUserUsername(String username, Pageable pageable);
}
