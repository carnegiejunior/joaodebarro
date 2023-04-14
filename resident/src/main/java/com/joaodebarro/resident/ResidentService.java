package com.joaodebarro.resident;

import com.joaodebarro.resident.dto.ResidentRegistrationRequest;
import com.joaodebarro.resident.dto.ResidentResponse;
import com.joaodebarro.resident.exceptionHandler.defaultExceptions.EntityNotFoundException;
import com.joaodebarro.resident.exceptionHandler.defaultExceptions.ResourceNotFoundWithIdException;
import com.joaodebarro.resident.models.Resident;
import com.joaodebarro.resident.models.ResidentialUnit;
import com.joaodebarro.resident.repositories.ResidentRepository;
import com.joaodebarro.resident.repositories.ResidentialUnitRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
public class ResidentService {

    private final ResidentRepository residentRepository;
    private final ResidentialUnitRepository residentialUnitRepository;

    public ResidentResponse registerResident(ResidentRegistrationRequest residentRegistrationRequest) {
        ResidentialUnit residentialUnit = residentialUnitRepository.findById(residentRegistrationRequest.residentialUnitId())
                .orElseThrow(() -> new EntityNotFoundException("Residential unit with id: %s not found".formatted(residentRegistrationRequest.residentialUnitId())));

        LocalDate birthDate = LocalDate.parse(residentRegistrationRequest.birthDate());

        Resident resident = Resident.builder()
                .name(residentRegistrationRequest.name())
                .cpf(residentRegistrationRequest.cpf())
                .phoneNumber(residentRegistrationRequest.phoneNumber())
                .email(residentRegistrationRequest.email())
                .birthDate(birthDate)
                .residentialUnit(residentialUnit)
                .build();

        Resident residentSaved = residentRepository.save(resident);

        ResidentResponse response = ResidentResponse.builder()
                .name(residentSaved.getName())
                .email(residentSaved.getEmail())
                .cpf(residentSaved.getEmail())
                .birthDate(residentSaved.getBirthDate())
                .phoneNumber(residentSaved.getPhoneNumber())
                .residentialUnit(residentSaved.getResidentialUnit())
                .build();
        return response;
    }

    public Optional<ResidentResponse> fetchResidentById(Long residentId) {
        ResidentResponse residentResponse = this.residentRepository.findByIdWithResidentialUnit(residentId)
                .map(resident ->
                        ResidentResponse.builder()
                                .name(resident.getName())
                                .cpf(resident.getCpf())
                                .phoneNumber(resident.getPhoneNumber())
                                .email(resident.getEmail())
                                .birthDate(resident.getBirthDate())
                                .residentialUnit(resident.getResidentialUnit())
                                .build()
                ).orElseThrow(() -> new ResourceNotFoundWithIdException(residentId));
        return Optional.of(residentResponse);
    }
}
