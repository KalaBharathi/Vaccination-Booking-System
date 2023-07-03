package com.example.VBS.service;


import com.example.VBS.DTO.RequestDTO.DoctorRequestDto;
import com.example.VBS.DTO.ResponseDTO.DoctorResponseDto;
import com.example.VBS.exception.CenterNotPresentException;
import com.example.VBS.exception.DoctorNotFoundException;

import java.util.List;

public interface DoctorService {
    public DoctorResponseDto addDoctor(DoctorRequestDto doctorRequestDto) throws CenterNotPresentException;

   public List<DoctorResponseDto> getDoctorsWithMoreThanTenAppointments();

   public List<DoctorResponseDto> getmaleDoctorsAboveAgeForty();

   public double getRatioOfMaleToFemaleDoctors();

   public DoctorResponseDto updateMobNo(String mailId, String mobNo) throws DoctorNotFoundException;
}
