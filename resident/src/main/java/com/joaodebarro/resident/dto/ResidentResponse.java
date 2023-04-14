package com.joaodebarro.resident.dto;

import com.joaodebarro.resident.models.ResidentialUnit;
import lombok.Builder;

import java.time.LocalDate;

@Builder
public record ResidentResponse(
        String name,
        String cpf,
        String phoneNumber,
        String email,
        LocalDate birthDate,
        ResidentialUnit residentialUnit
) {
}
