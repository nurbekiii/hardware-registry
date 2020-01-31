package com.musala.hardwareregistry.repo;

import com.musala.hardwareregistry.entity.Equipment;
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
public interface EquipmentRepo  extends JpaRepository<Equipment, Long> {
    @Override
    List<Equipment> findAll();

    @Override
    Optional<Equipment> findById(Long aLong);

    List<Equipment> getEquipmentByGateway(UUID gatewayId);
}
