package com.joaodebarro.resident.controllers;

import com.carnegieworks.exceptionHandler.defaultExceptions.ResourceNotFoundException;
import com.joaodebarro.resident.domain.dtos.ResidentRequestDTO;
import com.joaodebarro.resident.domain.dtos.ResidentResponseDTO;
import com.joaodebarro.resident.domain.services.ResidentService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/resident")
@CrossOrigin(origins = "*", maxAge = 3600)
@AllArgsConstructor
public class ResidentController {

    private final ResidentService residentService;

    @PostMapping
    public ResponseEntity<ResidentResponseDTO> registerResident(@RequestBody @Valid ResidentRequestDTO residentRegistrationRequest) {
        var residentRegistrationResponse = this.residentService.registerNewResident(residentRegistrationRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(residentRegistrationResponse);
    }

    @GetMapping("{residentId}")
    public ResponseEntity<ResidentResponseDTO> fetchResidentById(@NotNull @PathVariable("residentId") Long residentId) {
        var residentResponse = this.residentService.fetchResidentById(residentId).orElseThrow(() -> new ResourceNotFoundException(residentId));
        return ResponseEntity.status(HttpStatus.OK).body(residentResponse);
    }

//            @Size(max = 50) @RequestParam(value = "residentName") String residentName,
//            @Size(max = 14) @RequestParam(value = "cpf") String cpf,
//            @Size(max = 10) @RequestParam(value = "birth-date") String birthDate,
    @GetMapping
    public ResponseEntity<Page<ResidentResponseDTO>> findAllResidentsByName(
            @Valid @RequestBody ResidentRequestDTO residentRequestDTO,
            @PageableDefault(
                    size = 10,
                    page = 0,
                    direction = Sort.Direction.ASC,
                    sort = {}
            ) Pageable pageable) {
        return this.residentService.findAllResidents(residentRequestDTO, pageable);
    }

    @DeleteMapping("{residentId}")
    public ResponseEntity deleteResidentById(@NotNull @PathVariable("residentId") Long residentId) {
        throw new UnsupportedOperationException("not implemented yet");
    }

    @PutMapping
    public ResponseEntity<ResidentResponseDTO> updateResident(@RequestBody @Valid ResidentRequestDTO residentRegistrationRequest) {
        throw new UnsupportedOperationException("not implemented yet");
//        return ResponseEntity.noContent().build();
    }


}
