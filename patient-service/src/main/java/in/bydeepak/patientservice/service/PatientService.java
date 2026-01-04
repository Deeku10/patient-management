package in.bydeepak.patientservice.service;

import in.bydeepak.patientservice.dto.PatientRequestDto;
import in.bydeepak.patientservice.dto.PatientResponseDto;
import in.bydeepak.patientservice.exception.EmailAlreadyAvailableException;
import in.bydeepak.patientservice.exception.PatientNotFoundException;
import in.bydeepak.patientservice.grpc.BillingServiceGrpcClient;
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
    private final BillingServiceGrpcClient billingServiceGrpcClient;

    public PatientService(PatientRepository patientRepository,BillingServiceGrpcClient billingServiceGrpcClient) {
        this.patientRepository = patientRepository;
        this.billingServiceGrpcClient = billingServiceGrpcClient;
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

        this.billingServiceGrpcClient.createBillingAccount(patient.getId().toString(),patient.getName(),patient.getEmail());

        return PatientMapper.toDto(patient);
   }

   public PatientResponseDto updatePatient(UUID id, PatientRequestDto request){
        Patient patient = patientRepository.findById(id).orElseThrow(()->{
            throw new PatientNotFoundException("Patient not found with this id");
        });
        if(patientRepository.existsByEmailAndIdNot(request.getEmail(),id)){
            throw new EmailAlreadyAvailableException("Email exists");
        }
        patient.setName(request.getName());
        patient.setEmail(request.getEmail());
        patient.setAddress(request.getAddress());
        patient.setDateOfBirth(LocalDate.parse(request.getDateOfBirth()));
        return PatientMapper.toDto(patientRepository.save(patient));
   }

   public void deletePatient(UUID id){
        patientRepository.deleteById(id);
   }
}
