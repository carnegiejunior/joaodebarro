package com.joaodebarro.resident.entities;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.br.CPF;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.time.LocalDate;
import java.util.Optional;
import java.util.Set;

import static jakarta.validation.Validation.buildDefaultValidatorFactory;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ResidentEntityTest {
    @Mock
    private Validator validator;

    @InjectMocks
    private ResidentEntity resident;

    @BeforeEach
    public void setUp() {
        validator = buildDefaultValidatorFactory().getValidator();
    }

    @Test
    @DisplayName("The 'Name' field cannot be null")
    public void nameShouldNotBeNull(){
        resident = ResidentEntity.builder()
                .id(1L)
                .name(null)
                .cpf("37214523566")
                .email("fullname@test.com")
                .birthDate(LocalDate.of(1900, 1, 1))
                .phoneNumber("555-1234")
                .build();

        // Act
        Set<ConstraintViolation<ResidentEntity>> violations = validator.validate(resident);

        // Assert
        assertThat(violations).allMatch(violation ->
                violation.getMessage().equals("size must be between 3 and 50") ||
                        violation.getMessage().equals("must not be blank"));

    }

    @Test
    void givenResidentEntityWithInvalidName_whenValidating_thenShouldThrowValidationException() {
        // Given
        ResidentEntity residentEntity = ResidentEntity.builder()
                .name("")
                .cpf("70609039334")
                .email("joao@test.com")
                .birthDate(LocalDate.now().minusYears(30))
                .phoneNumber("12345678901")
                .build();

        Validator validator = buildDefaultValidatorFactory().getValidator();
        // Act
        Set<ConstraintViolation<ResidentEntity>> violations = validator.validate(residentEntity);
        // Assert
        assertThat(violations).allMatch(violation ->
                violation.getMessage().equals("size must be between 3 and 50") ||
                violation.getMessage().equals("must not be blank"));
    }


    @Test
    void givenResidentEntityWithInvalidCpf_whenValidating_thenShouldThrowValidationException() {
        // Given
        ResidentEntity residentEntity = ResidentEntity.builder()
                .name("Full name")
                .cpf("12345678901")
                .email("joao@test.com")
                .birthDate(LocalDate.now().minusYears(30))
                .phoneNumber("12345678901")
                .build();

        Validator validator = buildDefaultValidatorFactory().getValidator();
        // Act
        Set<ConstraintViolation<ResidentEntity>> violations = validator.validate(residentEntity);
        // Assert
        assertThat(violations).allMatch(violation ->
                violation.getMessage().equals("invalid Brazilian individual taxpayer registry number (CPF)") ||
                        violation.getMessage().equals("size must be between 11 and 11"));
    }




}