package com.joaodebarro.resident.repositories;

import com.joaodebarro.resident.entities.ResidentialUnitEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResidentialUnitRepository extends JpaRepository<ResidentialUnitEntity,Long> {
}
