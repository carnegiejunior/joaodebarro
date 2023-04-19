package com.joaodebarro.resident.domain.interfaces;

import com.joaodebarro.resident.domain.dtos.ResidentRequestQueryDTO;
import com.joaodebarro.resident.domain.entities.ResidentEntity;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ResidentRepositoryQueries {
    Optional<List<ResidentEntity>> findAllResidents(ResidentRequestQueryDTO residentRequestQueryDTO , Pageable pageable);
}
