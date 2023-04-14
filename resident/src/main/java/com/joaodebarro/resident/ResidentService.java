package com.joaodebarro.resident;

import com.carnegieworks.exceptionHandler.defaultExceptions.ResourceNotFoundWithIdException;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
public class ResidentService {

    private final ResidentRepository residentRepository;
    private final ResidentialUnitRepository residentialUnitRepository;

    public ResidentResponseDTO registerNewResident(ResidentRequestDTO residentRequestDTO) {

        ResidentialUnitModel residentialUnit = residentialUnitRepository.findById(residentRequestDTO.residentialUnitId())
                .orElseThrow(() -> new EntityNotFoundException("Residential unit with id: %s not found".formatted(residentRequestDTO.residentialUnitId())));

        ResidentModel resident = ResidentModel.builder()
                .name(residentRequestDTO.name())
                .cpf(residentRequestDTO.cpf())
                .phoneNumber(residentRequestDTO.phoneNumber())
                .email(residentRequestDTO.email())
                .birthDate(residentRequestDTO.birthDate())
                .residentialUnit(residentialUnit)
                .build();

        ResidentModel residentModelSaved = residentRepository.save(resident);

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
                ).orElseThrow(() -> new ResourceNotFoundWithIdException(residentId));
        return Optional.of(residentResponseDTO);
    }
}
