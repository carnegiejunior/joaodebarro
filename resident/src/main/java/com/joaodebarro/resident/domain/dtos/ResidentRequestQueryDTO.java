package com.joaodebarro.resident.domain.dtos;

import jakarta.validation.constraints.*;
import lombok.Builder;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;
import java.util.List;

@Builder
public record ResidentRequestQueryDTO(
        List<String> name,
        List<String> cpf,
        List<String> phoneNumber,
        List<String> email,
        List<LocalDate> birthDate,
        List<Long> residentialUnitId
) {

}
