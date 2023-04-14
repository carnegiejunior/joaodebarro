package com.joaodebarro.resident.dto;

import lombok.Builder;

@Builder
public record ResidentRegistrationRequest(
        String name,
        String cpf,
        String phoneNumber,
        String email,
        String birthDate,
        Long residentialUnitId
) {

}
