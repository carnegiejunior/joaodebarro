package com.joaodebarro.resident;

import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ResidentRepositoryQueries {
    Optional<List<ResidentEntity>> findAllResidents(ResidentRequestQueryDTO residentRequestQueryDTO , Pageable pageable);
}
