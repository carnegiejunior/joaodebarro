package com.joaodebarro.resident;

import com.joaodebarro.resident.repositories.ResidentRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.junit.jupiter.api.Assertions.assertTrue;


@DataJpaTest
class ResidentRepositoryTest {

    @Autowired
    private ResidentRepository residentRepository;

    @Autowired
    private JdbcTemplate jdbc;

    @BeforeEach
    void setupBeforeEach() {
        jdbc.execute("insert into residential_unit(id,designation) values(1L,'101'); ");
        jdbc.execute("insert into resident(id,name,cpf,phone_number,email,birth_date,residential_unit_id) " +
                "values(1L,'Fulano','991404789','13873908050','test@domain.com','1980-01-01',1L); ");
    }

    @AfterEach
    void setupAfterEach() {
        jdbc.execute("DELETE FROM resident");
        jdbc.execute("DELETE FROM residential_unit");
    }

    @Test
    @DisplayName("Should update a given email")
    void shouldUpdateEmail(){
        // Given
        Long residentId = 1L;
        String newEmail = "test2@domain.com";
        // When
        int totalEffected = residentRepository.updateEmail(residentId, newEmail);
        // Do
        assertTrue(totalEffected > 0);
    }

}