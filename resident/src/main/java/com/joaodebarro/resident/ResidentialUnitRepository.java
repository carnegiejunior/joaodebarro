package com.joaodebarro.resident;

import com.joaodebarro.resident.ResidentialUnitModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResidentialUnitRepository extends JpaRepository<ResidentialUnitModel,Long> {
}
