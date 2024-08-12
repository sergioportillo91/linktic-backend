package com.reservation.api.repositories;

import java.util.Optional;

import com.reservation.api.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;



public interface UserRepository extends JpaRepository<UserEntity,Long> {
    Optional<UserEntity> findByUsername(String username); 
}
