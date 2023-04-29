package com.joaodebarro.resident;

import com.carnegieworks.exceptionHandler.defaultExceptions.ResourceNotFoundException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.ws.rs.Produces;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/v1/resident")
@CrossOrigin(origins = "*", maxAge = 3600)
@AllArgsConstructor
public class ResidentController {

    private final ResidentService residentService;

    @PostMapping
    public ResponseEntity<Long> createResident(@RequestBody @Valid ResidentRequestDTO residentRegistrationRequest) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(
                        this.residentService.createResident(residentRegistrationRequest)
                );
    }

    @Produces("application/json; charset=UTF-8")
    @GetMapping("{residentId}")
    public ResponseEntity<ResidentResponseDTO> fetchResidentById(@NotNull @PathVariable("residentId") Long residentId) {
        var residentResponse = this.residentService.fetchResidentById(residentId).orElseThrow(() -> new ResourceNotFoundException(residentId));
        return ResponseEntity.status(HttpStatus.OK).body(residentResponse);
    }

//    @Produces("application/json; charset=UTF-8")
//    @GetMapping
//    public ResponseEntity<Page<ResidentResponseDTO>> findAllResidents(
//            @Valid @RequestBody ResidentRequestQueryDTO residentRequestQueryDTO,
//            @PageableDefault(
//                    size = 10,
//                    page = 0,
//                    direction = Sort.Direction.ASC,
//                    sort = {}
//            ) Pageable pageable) {
//        return this.residentService.findAllResidents(residentRequestQueryDTO, pageable);
//    }

    @Produces("application/json; charset=UTF-8")
    @GetMapping
    public ResponseEntity<Page<ResidentResponseDTO>> findAllResidents(
            @RequestParam(required = false) List<String> name,
            @RequestParam(required = false) List<String> cpf,
            @RequestParam(required = false) List<String> phoneNumber,
            @RequestParam(required = false) List<String> email,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) List<LocalDate> birthDate,
            @RequestParam(required = false) List<Long> residentialUnitId,
            @PageableDefault(
                    size = 10,
                    page = 0,
                    direction = Sort.Direction.ASC,
                    sort = {}
            ) Pageable pageable) {

        ResidentRequestQueryDTO residentRequestQueryDTO = ResidentRequestQueryDTO.builder()
                .name(name)
                .cpf(cpf)
                .phoneNumber(phoneNumber)
                .email(email)
                .birthDate(birthDate)
                .build();
        return this.residentService.findAllResidents(residentRequestQueryDTO, pageable);
    }


    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteResidentById(@NotNull @PathVariable("id") Long residentId) {
        residentService.deleteResidentById(residentId);
    }

    @PutMapping
    public ResponseEntity<ResidentResponseDTO> updateResident(@RequestBody @Valid ResidentRequestDTO residentRegistrationRequest) {
        throw new UnsupportedOperationException("not implemented yet");
    }

}
