package com.htecgroup.uber.repository;

import com.htecgroup.uber.model.entity.PassengerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface PassengerRepository extends JpaRepository<PassengerEntity, UUID> {

    Optional<PassengerEntity> findByUserEntityId(UUID userId);
}
