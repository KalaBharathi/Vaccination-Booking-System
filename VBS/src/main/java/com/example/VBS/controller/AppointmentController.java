package com.example.VBS.controller;

import com.example.VBS.DTO.RequestDTO.AppointmentRequestDto;
import com.example.VBS.DTO.ResponseDTO.AppointmentResponseDto;
import com.example.VBS.exception.DoctorNotFoundException;
import com.example.VBS.exception.Dose1NotTakenException;
import com.example.VBS.exception.UserNotFoundException;
import com.example.VBS.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/appointment")
public class AppointmentController {
    @Autowired
    AppointmentService appointmentService;

    @PostMapping("/book")
    public ResponseEntity bookAppointment(@RequestBody AppointmentRequestDto appointmentRequestDto){
        try {
            AppointmentResponseDto appointmentResponseDto = appointmentService.bookAppointment(appointmentRequestDto);
            return new ResponseEntity(appointmentResponseDto, HttpStatus.CREATED);
        }catch(DoctorNotFoundException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }catch(UserNotFoundException e){
            return  new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }catch(Dose1NotTakenException e){
            return  new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
}
