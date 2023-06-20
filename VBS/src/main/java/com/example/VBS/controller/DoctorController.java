package com.example.VBS.controller;

import com.example.VBS.DTO.RequestDTO.DoctorRequestDto;
import com.example.VBS.DTO.ResponseDTO.DoctorResponseDto;
import com.example.VBS.exception.CenterNotPresentException;
import com.example.VBS.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/doctor")
public class DoctorController {
    @Autowired
    DoctorService doctorService;

    @PostMapping("/add")
    public ResponseEntity addDoctor(@RequestBody DoctorRequestDto doctorRequestDto){
        try{
            DoctorResponseDto doctorResponseDto=doctorService.addDoctor(doctorRequestDto);
            return new ResponseEntity<>(doctorResponseDto, HttpStatus.CREATED);
        }
        catch (CenterNotPresentException e){
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/doctorsWithMoreThanTenAppointments")
    public ResponseEntity getDoctorsWithMoreThanTenAppointments(){
        List<DoctorResponseDto> doctorResponseDtoList=doctorService.getDoctorsWithMoreThanTenAppointments();
        return new ResponseEntity(doctorResponseDtoList,HttpStatus.OK);
    }
    @GetMapping("/maleDoctorsAboveAgeForty")
    public ResponseEntity getmaleDoctorsAboveAgeForty(){
        List<DoctorResponseDto> doctorResponseDtoList=doctorService.getmaleDoctorsAboveAgeForty();
        return new ResponseEntity(doctorResponseDtoList,HttpStatus.OK);
    }
}
