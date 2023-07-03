package com.example.VBS.controller;

import com.example.VBS.DTO.RequestDTO.CenterRequestDto;
import com.example.VBS.DTO.ResponseDTO.CenterResponseDto;
import com.example.VBS.DTO.ResponseDTO.DoctorResponseDto;
import com.example.VBS.Enum.CenterType;
import com.example.VBS.exception.CenterNotPresentException;
import com.example.VBS.service.VaccinationCenterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/center")
public class VaccinationCenterController {
    @Autowired
    VaccinationCenterService vaccinationCenterService;

    @PostMapping("/add")
    public ResponseEntity addCenter(@RequestBody CenterRequestDto centerRequestDto){
        CenterResponseDto centerResponseDto=vaccinationCenterService.addCenter(centerRequestDto);
        return new ResponseEntity<>(centerResponseDto, HttpStatus.CREATED);
    }
    @GetMapping("/listOfAllDoctorsAtGivenCenter")
    public ResponseEntity getListOfAllDoctors(@RequestParam int centerId){
        try{
            List<DoctorResponseDto> doctorResponseDtoList=vaccinationCenterService.getListOfAllDoctors(centerId);
            return new ResponseEntity<>(doctorResponseDtoList,HttpStatus.OK);
        }catch(CenterNotPresentException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/all-doctors/male/center")
    public ResponseEntity getListOfAllMaleDoctors(@RequestParam int centerId){
        try{
            List<DoctorResponseDto> doctorResponseDtoList=vaccinationCenterService.getListOfAllMaleDoctors(centerId);
            return new ResponseEntity<>(doctorResponseDtoList,HttpStatus.OK);
        }catch(CenterNotPresentException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/all-doctors/Female/center")
    public ResponseEntity getListOfAllFemaleDoctors(@RequestParam int centerId){
        try{
            List<DoctorResponseDto> doctorResponseDtoList=vaccinationCenterService.getListOfAllFemaleDoctors(centerId);
            return new ResponseEntity<>(doctorResponseDtoList,HttpStatus.OK);
        }catch(CenterNotPresentException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/center-type/{centerType}")
    public ResponseEntity centersWithCenterType(@PathVariable CenterType centerType){
        List<CenterResponseDto> centers=vaccinationCenterService.centersWithCenterType(centerType);
        return new ResponseEntity(centers,HttpStatus.OK);
    }
}
