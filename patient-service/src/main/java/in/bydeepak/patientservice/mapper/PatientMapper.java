package in.bydeepak.patientservice.mapper;

import in.bydeepak.patientservice.dto.PatientResponseDto;
import in.bydeepak.patientservice.model.Patient;

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
}
