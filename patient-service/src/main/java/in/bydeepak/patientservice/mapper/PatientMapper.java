package in.bydeepak.patientservice.mapper;

import in.bydeepak.patientservice.dto.PatientRequestDto;
import in.bydeepak.patientservice.dto.PatientResponseDto;
import in.bydeepak.patientservice.model.Patient;

import java.time.LocalDate;

public class PatientMapper {
    public static PatientResponseDto toDto(Patient patient) {
        PatientResponseDto patientResponseDto = new PatientResponseDto();
        patientResponseDto.setId(String.valueOf(patient.getId()));
        patientResponseDto.setName(patient.getName());
        patientResponseDto.setEmail(patient.getEmail());
        patientResponseDto.setAddress(patient.getAddress());
        patientResponseDto.setDateOfBirth(patient.getDateOfBirth().toString());
        return patientResponseDto;
    }
    public  static Patient toModel(PatientRequestDto requestDto){
        Patient patient = new Patient();
        patient.setName(requestDto.getName());
        patient.setEmail(requestDto.getEmail());
        patient.setAddress(requestDto.getAddress());
        patient.setDateOfBirth(LocalDate.parse(requestDto.getDateOfBirth()));
        patient.setRegisteredDate(LocalDate.parse(requestDto.getRegisteredDate()));
        return patient;
    }
}
