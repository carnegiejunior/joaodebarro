package com.joaodebarro.resident.domain.interfaces;

import com.joaodebarro.resident.domain.dtos.ResidentRequestDTO;
import com.joaodebarro.resident.domain.entities.ResidentEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ResidentRepositoryQueries {
//    Optional<List<ResidentEntity>> findAllResidents(@Param("residentName") String residentName , Pageable pageable);
    Optional<List<ResidentEntity>> findAllResidents(ResidentRequestDTO residentRequestDTO , Pageable pageable);
}
