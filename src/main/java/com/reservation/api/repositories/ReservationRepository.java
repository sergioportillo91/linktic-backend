package com.reservation.api.repositories;

import com.reservation.api.entities.ReservationEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;

public interface ReservationRepository extends JpaRepository<ReservationEntity,Long> {

    @Query("SELECT r FROM ReservationEntity r WHERE (:customerId IS NULL OR r.customer.id = :customerId) AND (:serviceId IS NULL OR r.service.id = :serviceId) AND (:reservationDate IS NULL OR r.reservationDate >= :reservationDate)")
    Page<ReservationEntity> filter(@Param("serviceId") Long serviceId,
                                   @Param("customerId") Long customerId,
                                   @Param("reservationDate") Date reservationDate,
                                   Pageable pageable);

    @Modifying
    @Query("UPDATE ReservationEntity r SET r.state = 'CANCELADA' WHERE r.id = :id")
    void cancelReservation(@Param("id") Long id);
}
