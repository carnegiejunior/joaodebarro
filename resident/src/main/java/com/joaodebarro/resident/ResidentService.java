package com.joaodebarro.resident;

import com.carnegieworks.exceptionHandler.defaultExceptions.EntityNotFoundException;
import com.carnegieworks.exceptionHandler.defaultExceptions.ResourceNotFoundException;
import com.joaodebarro.resident.dtos.ResidentRequestDTO;
import com.joaodebarro.resident.dtos.ResidentRequestQueryDTO;
import com.joaodebarro.resident.dtos.ResidentResponseDTO;
import com.joaodebarro.resident.entities.ResidentEntity;
import com.joaodebarro.resident.entities.ResidentialUnitEntity;
import com.joaodebarro.resident.lib.pageable.PageableTranslator;
import com.joaodebarro.resident.repositories.ResidentRepository;
import com.joaodebarro.resident.repositories.ResidentialUnitRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
public class ResidentService {

    private final ResidentRepository residentRepository;
    private final ResidentialUnitRepository residentialUnitRepository;

    //    @Transactional
//    Deve ser usado em todos os métodos públicos que envolvam transações com o banco de dados
    public Long createResident(ResidentRequestDTO residentRequestDTO) {

        ResidentialUnitEntity residentialUnit = residentialUnitRepository.findById(residentRequestDTO.residentialUnitId())
                .orElseThrow(() -> new EntityNotFoundException("Residential unit with id: %s not found".formatted(residentRequestDTO.residentialUnitId())));

        ResidentEntity resident = ResidentEntity.builder()
                .name(residentRequestDTO.name())
                .cpf(residentRequestDTO.cpf())
                .phoneNumber(residentRequestDTO.phoneNumber())
                .email(residentRequestDTO.email())
                .birthDate(residentRequestDTO.birthDate())
//                .residentialUnit(residentialUnit)
                .build();

        ResidentEntity residentSaved = residentRepository.save(resident);

        return residentSaved.getId();
    }

    public Optional<ResidentResponseDTO> fetchResidentById(Long residentId) {
        ResidentResponseDTO residentResponseDTO = this.residentRepository.findById(residentId)
                .map(resident ->
                        ResidentResponseDTO.builder()
                                .id(resident.getId())
                                .name(resident.getName())
                                .cpf(resident.getCpf())
                                .phoneNumber(resident.getPhoneNumber())
                                .email(resident.getEmail())
                                .birthDate(resident.getBirthDate())
//                                .residentialUnit(resident.getResidentialUnit())
                                .build()
                ).orElseThrow(() -> new ResourceNotFoundException(residentId));
        return Optional.of(residentResponseDTO);
    }

    public ResponseEntity<Page<ResidentResponseDTO>> findAllResidents(ResidentRequestQueryDTO residentRequestQueryDTO, Pageable pageable) {
        List<ResidentEntity> foundResidents =
                residentRepository.findAllResidents(residentRequestQueryDTO, translatePageable(pageable))
                        .orElse(List.of());

        if (foundResidents.isEmpty()) return ResponseEntity.notFound().build();
        List<ResidentResponseDTO> foundResidentsMappedToResponseDTOList = foundResidents
                .stream()
                .map(this::mapToResidentResponseDTO)
                .toList();
        Page<ResidentResponseDTO> pageableResidentResponseDTO = new PageImpl<>(foundResidentsMappedToResponseDTOList, pageable, foundResidentsMappedToResponseDTOList.size());
        return ResponseEntity.status(HttpStatus.OK).body(pageableResidentResponseDTO);
    }

    @Transactional
    public void deleteResidentById(Long residentId) {
        residentRepository.findById(residentId).orElseThrow(()->new EntityNotFoundException("Morador não encontrado com o código %s".formatted(residentId)));
        residentRepository.deleteById(residentId);
        residentRepository.flush();
    }


    @Transactional
    public ResidentResponseDTO updateResident(ResidentRequestDTO residentRequestDTO) {

        ResidentEntity residentEntity = this.residentRepository.findById(residentRequestDTO.id()).orElseThrow(() -> new ResourceNotFoundException(residentRequestDTO.id()));

        return mapToResidentResponseDTO(residentRepository.save(
                ResidentEntity.builder()
                        .id(residentEntity.getId())
                        .name(residentRequestDTO.name())
                        .cpf(residentRequestDTO.cpf())
                        .email(residentRequestDTO.email())
                        .birthDate(residentRequestDTO.birthDate())
                        .phoneNumber(residentRequestDTO.phoneNumber())
                        .build()

        ));
    }



    private ResidentResponseDTO mapToResidentResponseDTO(ResidentEntity resident) {
        return ResidentResponseDTO.builder()
                .id(resident.getId())
                .name(resident.getName())
                .cpf(resident.getCpf())
                .email(resident.getEmail())
                .birthDate(resident.getBirthDate())
                .phoneNumber(resident.getPhoneNumber())
//                .residentialUnit(resident.getResidentialUnit())
                .build();
    }

    private Pageable translatePageable(Pageable pageable) {

        var mappedFields = Map.of(
                "id", "id",
                "name", "name",
                "cpf", "cpf",
                "email", "email",
                "birthDate", "birthDate",
                "phoneNumber", "phoneNumber",
                "residentialUnit", "residentialUnit.designation"
        );

        return PageableTranslator.translate(pageable, mappedFields);
    }
}