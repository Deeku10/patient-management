package in.bydeepak.patientservice.controller;

import in.bydeepak.patientservice.dto.PatientRequestDto;
import in.bydeepak.patientservice.dto.PatientResponseDto;
import in.bydeepak.patientservice.dto.validator.CreatePatientValidationGroup;
import in.bydeepak.patientservice.repository.PatientRepository;
import in.bydeepak.patientservice.service.PatientService;
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
public class PatientController {

    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping("/hello")
    public ResponseEntity<String> hello() {
        return ResponseEntity.ok().body("Hello world");
    }

    @GetMapping
    public ResponseEntity<List<PatientResponseDto>> getPatients() {
        return  ResponseEntity.ok().body(patientService.getPatients());
    }

    @PostMapping
    public ResponseEntity<PatientResponseDto> createPatient(@Validated({Default.class, CreatePatientValidationGroup.class}) @RequestBody PatientRequestDto patientRequestDto) {
        return   ResponseEntity.ok().body(patientService.createPatient(patientRequestDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PatientResponseDto> updatePatient(@PathVariable UUID id, @Validated({Default.class}) @RequestBody PatientRequestDto patientRequestDto) {
        return ResponseEntity.ok().body(patientService.updatePatient(id,patientRequestDto));
    }
}
