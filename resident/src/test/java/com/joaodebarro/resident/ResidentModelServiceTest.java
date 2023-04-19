package com.joaodebarro.resident;

import com.joaodebarro.resident.domain.dtos.ResidentRequestDTO;
import com.joaodebarro.resident.domain.entities.ResidentEntity;
import com.joaodebarro.resident.domain.entities.ResidentialUnitEntity;
import com.joaodebarro.resident.domain.repositories.ResidentRepository;
import com.joaodebarro.resident.domain.repositories.ResidentialUnitRepository;
import com.joaodebarro.resident.domain.services.ResidentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@SpringBootTest
public class ResidentModelServiceTest {


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

        ResidentialUnitEntity residentialUnitModel = ResidentialUnitEntity.builder()
                .id(1L)
                .designation("Apt 101")
                .build();

        when(residentialUnitRepository.findById(anyLong())).thenReturn(Optional.of(residentialUnitModel));

        ResidentRequestDTO request = ResidentRequestDTO.builder()
                .name("John Doe")
                .cpf("70609039334")
                .phoneNumber("1234567890")
                .email("johndoe@example.com")
                .birthDate(LocalDate.of(1990, 1, 1))
                .residentialUnitId(1L)
                .build();

        ResidentEntity residentModel = ResidentEntity.builder()
                .name(request.name())
                .cpf(request.cpf())
                .phoneNumber(request.phoneNumber())
                .email(request.email())
                .birthDate(request.birthDate())
                .residentialUnit(residentialUnitModel)
                .build();

        when(residentRepository.save(residentModel)).thenReturn(residentModel);

//        ResidentResponseDTO response = residentService.save(request);

//        assertEquals(request.name(), response.name());
//        assertEquals(request.cpf(), response.cpf());
//        assertEquals(request.phoneNumber(), response.phoneNumber());
//        assertEquals(request.email(), response.email());
//        assertEquals(LocalDate.parse(request.birthDate()), response.birthDate());
//        assertEquals(residentialUnitModel, response.residentialUnitModel());
    }

    @Test
    void testSaveResidentResidentialUnitNotFound() {

//        when(residentialUnitRepository.findById(anyLong())).thenReturn(Optional.empty());

//        ResidentRequestDTO request = ResidentRequestDTO.builder()
//                .name("John Doe")
//                .cpf("12345678900")
//                .phoneNumber("70609039334")
//                .email("johndoe@example.com")
//                .birthDate(LocalDate.of(1990,1,1))
//                .residentialUnitId(1L)
//                .build();

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
//        ResidentEntity resident = ResidentEntity.builder()
//                .name("Fulano")
//                .cpf("70609039334")
//                .email("fulano@domamin.com")
//                .birthDate(LocalDate.of(1976,8,3))
//                .phoneNumber("991404789")
//                .residentialUnitModel(ResidentialUnitEntity.builder().id(1L).designation("101").build())
//                .build();
//
////        ResidentResponseDTO registrationResponse = residentService.save(resident);
//
////        assertThat(registrationResponse)
////                .extracting(
////                        ResidentResponseDTO::name,
////                        ResidentResponseDTO::cpf,
////                        ResidentResponseDTO::phoneNumber,
////                        ResidentResponseDTO::email,
////                        ResidentResponseDTO::birthDate,
////                        ResidentResponseDTO::residentialUnitModel
////                        )
////                .contains(
////                        resident.getName(),
////                        resident.getCpf(),
////                        resident.getPhoneNumber(),
////                        resident.getEmail(),
////                        resident.getBirthDate(),
////                        resident.getResidentialUnitModel()
////                );
//    }



}
