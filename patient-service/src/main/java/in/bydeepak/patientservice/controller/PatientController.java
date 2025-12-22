package in.bydeepak.patientservice.controller;

import in.bydeepak.patientservice.dto.PatientRequestDto;
import in.bydeepak.patientservice.dto.PatientResponseDto;
import in.bydeepak.patientservice.dto.validator.CreatePatientValidationGroup;
import in.bydeepak.patientservice.repository.PatientRepository;
import in.bydeepak.patientservice.service.PatientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.groups.Default;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/patients")
@Tag(name = "Patient",description = "Api docs for patient service")
public class PatientController {

    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping("/hello")
    @Operation(summary = "Test the accessibility")
    public ResponseEntity<String> hello() {
        return ResponseEntity.ok().body("Hello world");
    }

    @GetMapping
    @Operation(summary = "Get patients")
    public ResponseEntity<List<PatientResponseDto>> getPatients() {
        return  ResponseEntity.ok().body(patientService.getPatients());
    }

    @PostMapping
    @Operation(summary = "Create a patient")
    public ResponseEntity<PatientResponseDto> createPatient(@Validated({Default.class, CreatePatientValidationGroup.class}) @RequestBody PatientRequestDto patientRequestDto) {
        return   ResponseEntity.ok().body(patientService.createPatient(patientRequestDto));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a patient details")
    public ResponseEntity<PatientResponseDto> updatePatient(@PathVariable UUID id, @Validated({Default.class}) @RequestBody PatientRequestDto patientRequestDto) {
        return ResponseEntity.ok().body(patientService.updatePatient(id,patientRequestDto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Remove a patient")
    public ResponseEntity<Void> deletePatient(@PathVariable UUID id) {
        patientService.deletePatient(id);
        return ResponseEntity.noContent().build();
    }
}

