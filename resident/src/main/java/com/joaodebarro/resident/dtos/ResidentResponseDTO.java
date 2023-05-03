package com.joaodebarro.resident.dtos;

import com.joaodebarro.resident.entities.ResidentialUnitEntity;
import jakarta.validation.constraints.*;
import lombok.Builder;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;

@Builder
public record ResidentResponseDTO(

        Long id,

        @NotBlank
        @Size(min = 3, max = 50)
        String name,

        @CPF
        @Size(min = 11, max = 11)
        String cpf,

        @Size(max = 14)
        String phoneNumber,

        @Email
        @NotBlank
        @Size(min = 5, max = 100)
        String email,

        @Past
        @NotNull
        LocalDate birthDate,

        @NotNull
        ResidentialUnitEntity residentialUnit
) {}
