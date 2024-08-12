package com.reservation.api.repositories;

import com.reservation.api.entities.CustomerEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CustomerRepository extends JpaRepository<CustomerEntity,Long> {

    @Query("SELECT c FROM CustomerEntity c WHERE (:id IS NULL OR c.id = :id) " +
            "AND (:firstName IS NULL OR UPPER(c.firstName) LIKE UPPER(CONCAT('%', :firstName, '%'))) " +
            "AND (:lastName IS NULL OR UPPER(c.lastName) LIKE UPPER(CONCAT('%', :lastName, '%'))) " +
            "AND (:identification IS NULL OR c.identification = :identification)")
    Page<CustomerEntity> filter(@Param("id") Long id,
                                @Param("firstName") String firstName,
                                @Param("lastName") String lastName,
                                @Param("identification") String identification,
                                Pageable pageable);
}
