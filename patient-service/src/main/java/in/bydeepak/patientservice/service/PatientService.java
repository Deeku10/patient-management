package in.bydeepak.patientservice.service;

import in.bydeepak.patientservice.dto.PatientResponseDto;
import in.bydeepak.patientservice.mapper.PatientMapper;
import in.bydeepak.patientservice.model.Patient;
import in.bydeepak.patientservice.repository.PatientRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PatientService {

    private final PatientRepository patientRepository;

    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

   public List<PatientResponseDto> getPatients(){
       List<Patient> patients = patientRepository.findAll();
       return patients.stream().map(PatientMapper::toDto).toList();
   }
}
