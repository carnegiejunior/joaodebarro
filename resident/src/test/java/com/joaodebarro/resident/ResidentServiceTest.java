package com.joaodebarro.resident;

import com.joaodebarro.resident.dto.ResidentRegistrationRequest;
import com.joaodebarro.resident.dto.ResidentResponse;
import com.joaodebarro.resident.models.Resident;
import com.joaodebarro.resident.models.ResidentialUnit;
import com.joaodebarro.resident.repositories.ResidentRepository;
import com.joaodebarro.resident.repositories.ResidentialUnitRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@SpringBootTest
public class ResidentServiceTest {


    @Mock
    private ResidentRepository residentRepository;

    @Mock
    private ResidentialUnitRepository residentialUnitRepository;

    @InjectMocks
    private ResidentService residentService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveResident() {

        ResidentialUnit residentialUnit = ResidentialUnit.builder()
                .id(1L)
                .designation("Apt 101")
                .build();

        when(residentialUnitRepository.findById(anyLong())).thenReturn(Optional.of(residentialUnit));

        ResidentRegistrationRequest request = ResidentRegistrationRequest.builder()
                .name("John Doe")
                .cpf("70609039334")
                .phoneNumber("1234567890")
                .email("johndoe@example.com")
                .birthDate("1990-01-01")
                .residentialUnitId(1L)
                .build();

        Resident resident = Resident.builder()
                .name(request.name())
                .cpf(request.cpf())
                .phoneNumber(request.phoneNumber())
                .email(request.email())
                .birthDate(LocalDate.parse(request.birthDate()))
                .residentialUnit(residentialUnit)
                .build();

        when(residentRepository.save(resident)).thenReturn(resident);

//        ResidentResponse response = residentService.save(request);

//        assertEquals(request.name(), response.name());
//        assertEquals(request.cpf(), response.cpf());
//        assertEquals(request.phoneNumber(), response.phoneNumber());
//        assertEquals(request.email(), response.email());
//        assertEquals(LocalDate.parse(request.birthDate()), response.birthDate());
//        assertEquals(residentialUnit, response.residentialUnit());
    }

    @Test
    void testSaveResidentResidentialUnitNotFound() {

        when(residentialUnitRepository.findById(anyLong())).thenReturn(Optional.empty());

        ResidentRegistrationRequest request = ResidentRegistrationRequest.builder()
                .name("John Doe")
                .cpf("12345678900")
                .phoneNumber("70609039334")
                .email("johndoe@example.com")
                .birthDate("1990-01-01")
                .residentialUnitId(1L)
                .build();

//        assertThrows(EntityNotFoundException.class, () -> residentService.save(request));
    }

//    @Autowired
//    ResidentService residentService;
//
//    @Test
//    @DisplayName("Should register a new resident")
//    void shouldRegisterNewResident(){
//        // Given
//
//        Resident resident = Resident.builder()
//                .name("Fulano")
//                .cpf("70609039334")
//                .email("fulano@domamin.com")
//                .birthDate(LocalDate.of(1976,8,3))
//                .phoneNumber("991404789")
//                .residentialUnit(ResidentialUnit.builder().id(1L).designation("101").build())
//                .build();
//
////        ResidentResponse registrationResponse = residentService.save(resident);
//
////        assertThat(registrationResponse)
////                .extracting(
////                        ResidentResponse::name,
////                        ResidentResponse::cpf,
////                        ResidentResponse::phoneNumber,
////                        ResidentResponse::email,
////                        ResidentResponse::birthDate,
////                        ResidentResponse::residentialUnit
////                        )
////                .contains(
////                        resident.getName(),
////                        resident.getCpf(),
////                        resident.getPhoneNumber(),
////                        resident.getEmail(),
////                        resident.getBirthDate(),
////                        resident.getResidentialUnit()
////                );
//    }



}
