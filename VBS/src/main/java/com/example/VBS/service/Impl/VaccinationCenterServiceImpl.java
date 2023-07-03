package com.example.VBS.service.Impl;

import com.example.VBS.DTO.RequestDTO.CenterRequestDto;
import com.example.VBS.DTO.ResponseDTO.CenterResponseDto;
import com.example.VBS.DTO.ResponseDTO.DoctorResponseDto;
import com.example.VBS.Enum.CenterType;
import com.example.VBS.Enum.Gender;
import com.example.VBS.exception.CenterNotPresentException;
import com.example.VBS.model.Doctor;
import com.example.VBS.model.VaccinationCenter;
import com.example.VBS.repository.VaccinationCenterRepository;
import com.example.VBS.service.VaccinationCenterService;
import com.example.VBS.transformer.DoctorTransformer;
import com.example.VBS.transformer.VaccinationCenterTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    @Override
    public List<DoctorResponseDto> getListOfAllDoctors(int centerId) throws CenterNotPresentException {
        Optional<VaccinationCenter> vaccinationCenterOptional=vaccinationCenterRepository.findById(centerId);
        if(vaccinationCenterOptional.isEmpty()){
            throw new CenterNotPresentException("Center not found!!");
        }
        VaccinationCenter vaccinationCenter=vaccinationCenterOptional.get();
        List<Doctor> doctorList=vaccinationCenter.getDoctors();
        List<DoctorResponseDto> doctorResponseDtoList=new ArrayList<>();
        for(Doctor doctor:doctorList){
            doctorResponseDtoList.add(DoctorTransformer.doctorToDoctorResponseDto(doctor));
        }
        return doctorResponseDtoList;
    }

    @Override
    public List<DoctorResponseDto> getListOfAllMaleDoctors(int centerId) throws CenterNotPresentException {
        Optional<VaccinationCenter> vaccinationCenterOptional=vaccinationCenterRepository.findById(centerId);
        if(vaccinationCenterOptional.isEmpty()){
            throw new CenterNotPresentException("Center not found!!");
        }
        VaccinationCenter vaccinationCenter=vaccinationCenterOptional.get();
        List<Doctor> doctorList=vaccinationCenter.getDoctors();
        List<DoctorResponseDto> doctorResponseDtoList=new ArrayList<>();
        for(Doctor doctor:doctorList){
            if(doctor.getGender().equals(Gender.MALE))
            doctorResponseDtoList.add(DoctorTransformer.doctorToDoctorResponseDto(doctor));
        }
        return doctorResponseDtoList;
    }
    @Override
    public List<DoctorResponseDto> getListOfAllFemaleDoctors(int centerId) throws CenterNotPresentException {
        Optional<VaccinationCenter> vaccinationCenterOptional=vaccinationCenterRepository.findById(centerId);
        if(vaccinationCenterOptional.isEmpty()){
            throw new CenterNotPresentException("Center not found!!");
        }
        VaccinationCenter vaccinationCenter=vaccinationCenterOptional.get();
        List<Doctor> doctorList=vaccinationCenter.getDoctors();
        List<DoctorResponseDto> doctorResponseDtoList=new ArrayList<>();
        for(Doctor doctor:doctorList){
            if(doctor.getGender().equals(Gender.FEMALE))
                doctorResponseDtoList.add(DoctorTransformer.doctorToDoctorResponseDto(doctor));
        }
        return doctorResponseDtoList;
    }

    @Override
    public List<CenterResponseDto> centersWithCenterType(CenterType centerType) {
        List<VaccinationCenter> vaccinationCenters=vaccinationCenterRepository.findByCenterType(centerType);
        List<CenterResponseDto> centerResponseDtos=new ArrayList<>();
        for(VaccinationCenter center:vaccinationCenters){
            centerResponseDtos.add(VaccinationCenterTransformer.vaccinationCenterToCenterResponseDto(center));
        }
        return centerResponseDtos;
    }
}
