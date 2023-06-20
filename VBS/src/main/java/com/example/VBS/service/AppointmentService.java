package com.example.VBS.service;

import com.example.VBS.DTO.RequestDTO.AppointmentRequestDto;
import com.example.VBS.DTO.ResponseDTO.AppointmentResponseDto;
import com.example.VBS.exception.DoctorNotFoundException;
import com.example.VBS.exception.Dose1NotTakenException;
import com.example.VBS.exception.UserNotFoundException;

public interface AppointmentService {
    public AppointmentResponseDto bookAppointment(AppointmentRequestDto appointmentRequestDto) throws UserNotFoundException, DoctorNotFoundException, Dose1NotTakenException;
}
