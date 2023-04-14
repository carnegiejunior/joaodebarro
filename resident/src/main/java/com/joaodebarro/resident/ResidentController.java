package com.joaodebarro.resident;

import com.joaodebarro.resident.dto.ResidentRegistrationRequest;
import com.joaodebarro.resident.dto.ResidentResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.function.Consumer;

@RestController
@RequestMapping("api/v1/resident")
@AllArgsConstructor
public class ResidentController {

    private final ResidentService residentService;
    @PostMapping
    public ResponseEntity<ResidentResponse> registerResident(@RequestBody ResidentRegistrationRequest residentRegistrationRequest){
        var residentRegistrationResponse = this.residentService.registerResident(residentRegistrationRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(residentRegistrationResponse);
    }

    @GetMapping("{residentId}")
    public ResponseEntity<ResidentResponse> fetchResidentById(@PathVariable("residentId") Long residentId){
            var residentResponse = this.residentService.fetchResidentById(residentId).orElseThrow(()-> new ResidentResourceNotFoundException(residentId));
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(residentResponse);
    }



}
