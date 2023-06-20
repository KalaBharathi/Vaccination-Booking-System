package com.example.VBS.service.Impl;

import com.example.VBS.DTO.RequestDTO.DoctorRequestDto;
import com.example.VBS.DTO.ResponseDTO.DoctorResponseDto;
import com.example.VBS.Enum.Gender;
import com.example.VBS.exception.CenterNotPresentException;
import com.example.VBS.model.Doctor;
import com.example.VBS.model.VaccinationCenter;
import com.example.VBS.repository.DoctorRepository;
import com.example.VBS.repository.VaccinationCenterRepository;
import com.example.VBS.service.DoctorService;
import com.example.VBS.transformer.DoctorTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DoctorServiceImpl implements DoctorService {
    @Autowired
    DoctorRepository doctorRepository;
    @Autowired
    VaccinationCenterRepository vaccinationCenterRepository;

    @Override
    public DoctorResponseDto addDoctor(DoctorRequestDto doctorRequestDto) throws CenterNotPresentException {
        Optional<VaccinationCenter> optionalVaccinationCenter= vaccinationCenterRepository.findById(doctorRequestDto.getCenterId());
        if(!optionalVaccinationCenter.isPresent()){
            throw new CenterNotPresentException("invalid center id!");
        }
        VaccinationCenter vaccinationCenter=optionalVaccinationCenter.get();

        Doctor doctor= DoctorTransformer.doctorRequestDtoToDoctor(doctorRequestDto);
        doctor.setVaccinationCenter(vaccinationCenter);

        vaccinationCenter.getDoctors().add(doctor);

        VaccinationCenter savedCenter=vaccinationCenterRepository.save(vaccinationCenter);
        DoctorResponseDto doctorResponseDto=DoctorTransformer.doctorToDoctorResponseDto(doctor);
        return doctorResponseDto;
    }

    @Override
    public List<DoctorResponseDto> getDoctorsWithMoreThanTenAppointments() {
        List<Doctor> doctorsList=doctorRepository.findAll();
        List<DoctorResponseDto> doctorResponseDtoList=new ArrayList<>();
        for(Doctor doctor:doctorsList){
            if(doctor.getAppointments().size()>10){
                doctorResponseDtoList.add(DoctorTransformer.doctorToDoctorResponseDto(doctor));
            }
        }
        return doctorResponseDtoList;
    }

    @Override
    public List<DoctorResponseDto> getmaleDoctorsAboveAgeForty() {
        List<Doctor> doctorsList=doctorRepository.findAll();
        List<DoctorResponseDto> doctorResponseDtoList=new ArrayList<>();
        for(Doctor doctor:doctorsList){
            if(doctor.getGender().equals(Gender.MALE) && doctor.getAge()>40){
                doctorResponseDtoList.add(DoctorTransformer.doctorToDoctorResponseDto(doctor));
            }
        }
        return doctorResponseDtoList;
    }
}
