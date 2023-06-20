package com.example.VBS.service.Impl;

import com.example.VBS.DTO.RequestDTO.CenterRequestDto;
import com.example.VBS.DTO.ResponseDTO.CenterResponseDto;
import com.example.VBS.model.VaccinationCenter;
import com.example.VBS.repository.VaccinationCenterRepository;
import com.example.VBS.service.VaccinationCenterService;
import com.example.VBS.transformer.VaccinationCenterTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VaccinationCenterServiceImpl implements VaccinationCenterService {
    @Autowired
    VaccinationCenterRepository vaccinationCenterRepository;

    @Override
    public CenterResponseDto addCenter(CenterRequestDto centerRequestDto) {
        VaccinationCenter vaccinationCenter= VaccinationCenterTransformer.centerRequestDtoToVaccinationCenter(centerRequestDto);
        VaccinationCenter savedVaccinationCenter=vaccinationCenterRepository.save(vaccinationCenter);
        return VaccinationCenterTransformer.vaccinationCenterToCenterResponseDto(savedVaccinationCenter);

    }
}
