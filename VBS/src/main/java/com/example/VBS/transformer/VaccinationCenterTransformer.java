package com.example.VBS.transformer;

import com.example.VBS.DTO.RequestDTO.CenterRequestDto;
import com.example.VBS.DTO.ResponseDTO.CenterResponseDto;
import com.example.VBS.model.VaccinationCenter;
import lombok.experimental.UtilityClass;

@UtilityClass
public class VaccinationCenterTransformer {
    public static VaccinationCenter centerRequestDtoToVaccinationCenter(CenterRequestDto centerRequestDto){
        return VaccinationCenter.builder()
                .name(centerRequestDto.getName())
                .address(centerRequestDto.getAddress())
                .centerType(centerRequestDto.getCenterType())
                .build();
    }
    public static CenterResponseDto vaccinationCenterToCenterResponseDto(VaccinationCenter vaccinationCenter){
        return CenterResponseDto.builder()
                .name(vaccinationCenter.getName())
                .address(vaccinationCenter.getAddress())
                .centerType(vaccinationCenter.getCenterType())
                .build();
    }
}
