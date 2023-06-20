package com.example.VBS.transformer;

import com.example.VBS.DTO.RequestDTO.DoctorRequestDto;
import com.example.VBS.DTO.ResponseDTO.CenterResponseDto;
import com.example.VBS.DTO.ResponseDTO.DoctorResponseDto;
import com.example.VBS.model.Doctor;

public class DoctorTransformer {
    public static Doctor doctorRequestDtoToDoctor(DoctorRequestDto doctorRequestDto){
        return Doctor.builder()
                .age(doctorRequestDto.getAge())
                .name(doctorRequestDto.getName())
                .mobNo(doctorRequestDto.getMobNo())
                .gender(doctorRequestDto.getGender())
                .emailId(doctorRequestDto.getEmailId())
                .build();
    }
    public static DoctorResponseDto doctorToDoctorResponseDto(Doctor doctor){
        CenterResponseDto centerResponseDto=VaccinationCenterTransformer.vaccinationCenterToCenterResponseDto(doctor.getVaccinationCenter());

        return DoctorResponseDto.builder()
                .name(doctor.getName())
                .emailId(doctor.getEmailId())
                .mobNo(doctor.getMobNo())
                .centerResponseDto(centerResponseDto).build();
    }
}
