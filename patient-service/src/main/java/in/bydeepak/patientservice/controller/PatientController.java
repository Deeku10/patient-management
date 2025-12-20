package in.bydeepak.patientservice.controller;

import in.bydeepak.patientservice.dto.PatientResponseDto;
import in.bydeepak.patientservice.repository.PatientRepository;
import in.bydeepak.patientservice.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
}
