package com.example.VBS.service;

import com.example.VBS.DTO.RequestDTO.CenterRequestDto;
import com.example.VBS.DTO.ResponseDTO.CenterResponseDto;
import com.example.VBS.DTO.ResponseDTO.DoctorResponseDto;
import com.example.VBS.Enum.CenterType;
import com.example.VBS.exception.CenterNotPresentException;

import java.util.List;

public interface VaccinationCenterService {
    public CenterResponseDto addCenter(CenterRequestDto centerRequestDto);

   public List<DoctorResponseDto> getListOfAllDoctors(int centerId) throws CenterNotPresentException;

   public List<DoctorResponseDto> getListOfAllMaleDoctors(int centerId) throws CenterNotPresentException;
    public List<DoctorResponseDto> getListOfAllFemaleDoctors(int centerId) throws CenterNotPresentException;

   public  List<CenterResponseDto> centersWithCenterType(CenterType centerType);
}
