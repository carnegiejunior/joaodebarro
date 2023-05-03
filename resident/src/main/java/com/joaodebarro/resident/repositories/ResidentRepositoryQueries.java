package com.joaodebarro.resident.repositories;

import com.joaodebarro.resident.entities.ResidentEntity;
import com.joaodebarro.resident.dtos.ResidentRequestQueryDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ResidentRepositoryQueries {
    Optional<List<ResidentEntity>> findAllResidents(ResidentRequestQueryDTO residentRequestQueryDTO , Pageable pageable);
}
