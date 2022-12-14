package com.htecgroup.uber.repository;

import com.htecgroup.uber.model.entity.TransportRequestEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TransportRequestRepository extends JpaRepository<TransportRequestEntity, UUID> {
}
