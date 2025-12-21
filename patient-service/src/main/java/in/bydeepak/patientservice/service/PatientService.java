package in.bydeepak.patientservice.service;

import in.bydeepak.patientservice.dto.PatientRequestDto;
import in.bydeepak.patientservice.dto.PatientResponseDto;
import in.bydeepak.patientservice.exception.EmailAlreadyAvailableException;
import in.bydeepak.patientservice.exception.PatientNotFoundException;
import in.bydeepak.patientservice.mapper.PatientMapper;
import in.bydeepak.patientservice.model.Patient;
import in.bydeepak.patientservice.repository.PatientRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

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

   public PatientResponseDto createPatient(PatientRequestDto request){
        if(patientRepository.existsByEmail(request.getEmail())){
            throw new EmailAlreadyAvailableException("Email exists");
        }
        Patient patient = patientRepository.save(PatientMapper.toModel(request));
        return PatientMapper.toDto(patient);
   }

   public PatientResponseDto updatePatient(UUID id, PatientRequestDto request){
        Patient patient = patientRepository.findById(id).orElseThrow(()->{
            throw new PatientNotFoundException("Patient not found with this id");
        });
        if(patientRepository.existsByEmail(request.getEmail())){
            throw new EmailAlreadyAvailableException("Email exists");
        }
        patient.setName(request.getName());
        patient.setEmail(request.getEmail());
        patient.setAddress(request.getAddress());
        patient.setDateOfBirth(LocalDate.parse(request.getDateOfBirth()));
        return PatientMapper.toDto(patientRepository.save(patient));
   }

}
