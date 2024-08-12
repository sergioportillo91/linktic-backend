package com.reservation.api.repositories;

import com.reservation.api.entities.ServiceEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ServiceRepository extends JpaRepository<ServiceEntity,Long> {

    @Query("SELECT s FROM ServiceEntity s WHERE (:id IS NULL OR s.id = :id) " +
            "AND (:name IS NULL OR UPPER(s.name) LIKE UPPER(CONCAT('%', :name, '%'))) ")
    Page<ServiceEntity> filter(@Param("id") Long id,
                               @Param("name") String name,
                               Pageable pageable);
}
