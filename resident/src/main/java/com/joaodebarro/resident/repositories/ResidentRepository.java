package com.joaodebarro.resident.repositories;


import com.joaodebarro.resident.models.Resident;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.stream.DoubleStream;

@Repository
public interface ResidentRepository extends JpaRepository<Resident,Long> {

    @Modifying
    @Query(value = "UPDATE Resident r SET r.email = :newEmail WHERE r.id = :residentId")
    int updateEmail(@Param("residentId") Long userId, @Param("newEmail") String newEmail);

    @Query("SELECT r FROM Resident r JOIN FETCH r.residentialUnit WHERE r.id = :residentId")
    Optional<Resident> findByIdWithResidentialUnit(@Param("residentId") Long residentId);
}
