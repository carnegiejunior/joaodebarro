package com.joaodebarro.resident.models;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class ResidentTest {

    private static Validator validator;

    @BeforeAll
    static void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    @DisplayName("Should not allow a resident with invalid fields")
    public void shouldNotAllowResidentWithInvalidFields() {
        Resident resident = Resident.builder()
                .name("") // invalid name
                .cpf("123456789012345") // invalid CPF
                .email("invalid-email") // invalid email
                .birthDate(LocalDate.now().plusDays(1)) // future birth date
                .residentialUnit(null) // null residential unit
                .build();

        var violations = validator.validate(resident);

        assertEquals(7, violations.size());
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("name")));
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("cpf")));
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("email")));
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("birthDate")));
        assertTrue(violations.stream().anyMatch(v -> v.getPropertyPath().toString().equals("residentialUnit")));
    }
}