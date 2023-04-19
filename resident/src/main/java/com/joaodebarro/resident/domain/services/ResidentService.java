package com.joaodebarro.resident.domain.services;

import com.carnegieworks.exceptionHandler.defaultExceptions.ResourceNotFoundException;
import com.joaodebarro.resident.core.PageableTranslator;
import com.joaodebarro.resident.domain.dtos.ResidentRequestDTO;
import com.joaodebarro.resident.domain.dtos.ResidentRequestQueryDTO;
import com.joaodebarro.resident.domain.dtos.ResidentResponseDTO;
import com.joaodebarro.resident.domain.entities.ResidentEntity;
import com.joaodebarro.resident.domain.entities.ResidentialUnitEntity;
import com.joaodebarro.resident.domain.repositories.ResidentRepository;
import com.joaodebarro.resident.domain.repositories.ResidentialUnitRepository;
import jakarta.persistence.*;
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
    public ResidentResponseDTO registerNewResident(ResidentRequestDTO residentRequestDTO) {

        ResidentialUnitEntity residentialUnit = residentialUnitRepository.findById(residentRequestDTO.residentialUnitId())
                .orElseThrow(() -> new EntityNotFoundException("Residential unit with id: %s not found".formatted(residentRequestDTO.residentialUnitId())));

        ResidentEntity resident = ResidentEntity.builder()
                .name(residentRequestDTO.name())
                .cpf(residentRequestDTO.cpf())
                .phoneNumber(residentRequestDTO.phoneNumber())
                .email(residentRequestDTO.email())
                .birthDate(residentRequestDTO.birthDate())
                .residentialUnit(residentialUnit)
                .build();

        ResidentEntity residentModelSaved = residentRepository.save(resident);

        return ResidentResponseDTO.builder()
                .name(residentModelSaved.getName())
                .email(residentModelSaved.getEmail())
                .cpf(residentModelSaved.getEmail())
                .birthDate(residentModelSaved.getBirthDate())
                .phoneNumber(residentModelSaved.getPhoneNumber())
                .residentialUnit(residentModelSaved.getResidentialUnit())
                .build();
    }

    public Optional<ResidentResponseDTO> fetchResidentById(Long residentId) {
        ResidentResponseDTO residentResponseDTO = this.residentRepository.findResidentById(residentId)
                .map(residentModel ->
                        ResidentResponseDTO.builder()
                                .name(residentModel.getName())
                                .cpf(residentModel.getCpf())
                                .phoneNumber(residentModel.getPhoneNumber())
                                .email(residentModel.getEmail())
                                .birthDate(residentModel.getBirthDate())
                                .residentialUnit(residentModel.getResidentialUnit())
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
    public ResponseEntity deleteResidentById(Long id) {
        residentRepository.deleteById(id);
        residentRepository.flush();
        return ResponseEntity.noContent().build();
    }


    private ResidentResponseDTO mapToResidentResponseDTO(ResidentEntity residentModel) {
        return ResidentResponseDTO.builder()
//                .id(residentModel.getId())
                .name(residentModel.getName())
                .cpf(residentModel.getCpf())
                .email(residentModel.getEmail())
                .birthDate(residentModel.getBirthDate())
                .phoneNumber(residentModel.getPhoneNumber())
                .residentialUnit(residentModel.getResidentialUnit())
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