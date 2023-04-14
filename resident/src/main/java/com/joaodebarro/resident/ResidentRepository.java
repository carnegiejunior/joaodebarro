package com.joaodebarro.resident;


import com.joaodebarro.resident.ResidentModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ResidentRepository extends JpaRepository<ResidentModel,Long> {

    @Modifying
    @Query(value = "UPDATE ResidentModel r SET r.email = :newEmail WHERE r.id = :residentId")
    int updateEmail(@Param("residentId") Long userId, @Param("newEmail") String newEmail);

    @Query("SELECT r FROM ResidentModel r JOIN FETCH r.residentialUnit WHERE r.id = :residentId")
    Optional<ResidentModel> findResidentById(@Param("residentId") Long residentId);
}
