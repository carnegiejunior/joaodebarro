package com.joaodebarro.resident;

import com.carnegieworks.exceptionHandler.defaultExceptions.ResourceNotFoundWithIdException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/resident")
@CrossOrigin(origins = "*", maxAge = 3600)
@AllArgsConstructor
public class ResidentController {

    private final ResidentService residentService;
    @PostMapping
    public ResponseEntity<ResidentResponseDTO> registerResident(@RequestBody @Valid ResidentRequestDTO residentRegistrationRequest){
        var residentRegistrationResponse = this.residentService.registerNewResident(residentRegistrationRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(residentRegistrationResponse);
    }

    @GetMapping("{residentId}")
    public ResponseEntity<ResidentResponseDTO> fetchResidentById(@NotNull @PathVariable("residentId") Long residentId){
            var residentResponse = this.residentService.fetchResidentById(residentId).orElseThrow(()-> new ResourceNotFoundWithIdException(residentId));
            return ResponseEntity.status(HttpStatus.OK).body(residentResponse);
    }

    @GetMapping
    public ResponseEntity<List<ResidentResponseDTO>> fetchResidentByName( @Size(max = 50) @RequestParam("residentName") String residentName){
        //todo: must return a pagiable list of Residents
        throw new UnsupportedOperationException("not implemented yet");
    }

    @DeleteMapping("{residentId}")
    public ResponseEntity deleteResidentById( @NotNull @PathVariable("residentId") Long residentId){
        throw new UnsupportedOperationException("not implemented yet");
//        return ResponseEntity.noContent().build();
    }

    @PutMapping
    public ResponseEntity<ResidentResponseDTO> updateResident( @RequestBody @Valid ResidentRequestDTO residentRegistrationRequest){
        throw new UnsupportedOperationException("not implemented yet");
//        return ResponseEntity.noContent().build();
    }


}
