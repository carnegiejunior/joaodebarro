package com.joaodebarro.resident.domain.repositories;


import com.joaodebarro.resident.domain.entities.ResidentEntity;
import com.joaodebarro.resident.domain.interfaces.ResidentRepositoryQueries;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ResidentRepository extends JpaRepository<ResidentEntity,Long>, ResidentRepositoryQueries {

    @Modifying
    @Query(value = "UPDATE ResidentEntity r SET r.email = :newEmail WHERE r.id = :residentId")
    int updateEmail(@Param("residentId") Long userId, @Param("newEmail") String newEmail);

    @Query("SELECT r FROM ResidentEntity r JOIN FETCH r.residentialUnit WHERE r.id = :residentId")
    Optional<ResidentEntity> findResidentById(@Param("residentId") Long residentId);

    @Query("SELECT r FROM ResidentEntity r LEFT JOIN FETCH r.residentialUnit WHERE LOWER(TRIM(r.name)) LIKE CONCAT('%',LOWER(TRIM(:residentName)),'%')")
    Optional<List<ResidentEntity>> findAllByPartialName(@Param("residentName") String residentName , Pageable pageable);


}
