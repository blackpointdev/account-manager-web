package com.blackpoint.accountmanagerweb.repository;

import com.blackpoint.accountmanagerweb.model.Operation;
import com.blackpoint.accountmanagerweb.model.dtos.OperationDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface OperationRepository extends PagingAndSortingRepository<Operation, Long> {
    Page<Operation> findByUserUsername(String username, Pageable pageable);


    /**
     * Method returns Page of OperationDtos, which contains all of Operation data
     * with additional name of user who made the operation.
     * @param pageable
     * @return Page<OperationDto> containing all of operations id database.
     */
    @Query(
            "SELECT " +
                "new com.blackpoint.accountmanagerweb.model.dtos.OperationDto(o.id, o.balance, o.name, o.user.id, o.user.username) " +
            "FROM " +
                "Operation o ORDER BY o.id"
    )
    Page<OperationDto> findAllDto(Pageable pageable);
}
