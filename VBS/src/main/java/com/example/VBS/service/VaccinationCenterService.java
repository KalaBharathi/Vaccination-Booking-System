package com.example.VBS.service;

import com.example.VBS.DTO.RequestDTO.CenterRequestDto;
import com.example.VBS.DTO.ResponseDTO.CenterResponseDto;

public interface VaccinationCenterService {
    public CenterResponseDto addCenter(CenterRequestDto centerRequestDto);
}
