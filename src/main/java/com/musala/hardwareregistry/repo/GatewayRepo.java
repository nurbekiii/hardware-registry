package com.musala.hardwareregistry.repo;

import com.musala.hardwareregistry.entity.Gateway;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * @author NIsaev on 23.01.2020
 */

@Repository
public interface GatewayRepo extends JpaRepository<Gateway, UUID> {
    @Override
    List<Gateway> findAll();

    @Override
    Optional<Gateway> findById(UUID uuid);

    List<Gateway> findAllByNameContainsOrderByName(String name);
}
