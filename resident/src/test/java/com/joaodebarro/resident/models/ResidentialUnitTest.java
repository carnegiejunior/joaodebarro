package com.joaodebarro.resident.models;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class ResidentialUnitTest {

    private static Validator validator;

    @BeforeAll
    static void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }


    @Test
    @DisplayName("Should not allow a residential unit with a designation that is too short")
    public void shouldNotAllowResidentialUnitWithShortDesignation() {
        ResidentialUnit residentialUnit = ResidentialUnit.builder()
                .designation("12") // short designation
                .build();

        var violations = validator.validate(residentialUnit);

        assertEquals(1, violations.size());
        assertEquals("size must be between 3 and 10", violations.iterator().next().getMessage());
    }

    @Test
    @DisplayName("Should not allow a residential unit with a designation that is too long")
    public void shouldNotAllowResidentialUnitWithLongDesignation() {
        ResidentialUnit residentialUnit = ResidentialUnit.builder()
                .designation("12345678901") // long designation
                .build();

        var violations = validator.validate(residentialUnit);

        assertEquals(1, violations.size());
        assertEquals("size must be between 3 and 10", violations.iterator().next().getMessage());
    }
}