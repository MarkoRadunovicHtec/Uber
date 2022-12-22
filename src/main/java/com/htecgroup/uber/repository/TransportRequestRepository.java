package com.htecgroup.uber.repository;

import com.htecgroup.uber.model.entity.TransportRequestEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface TransportRequestRepository extends JpaRepository<TransportRequestEntity, UUID> {

    Optional<TransportRequestEntity> findByDriverAndPassengerAndStatus(UUID driverId, UUID passengerId, String status);
}
