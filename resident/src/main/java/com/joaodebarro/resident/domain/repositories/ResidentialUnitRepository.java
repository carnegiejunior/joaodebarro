package com.joaodebarro.resident.domain.repositories;

import com.joaodebarro.resident.domain.entities.ResidentialUnitEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResidentialUnitRepository extends JpaRepository<ResidentialUnitEntity,Long> {
}
