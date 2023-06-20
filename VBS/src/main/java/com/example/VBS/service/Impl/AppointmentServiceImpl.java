package com.example.VBS.service.Impl;

import com.example.VBS.DTO.RequestDTO.AppointmentRequestDto;
import com.example.VBS.DTO.ResponseDTO.AppointmentResponseDto;
import com.example.VBS.DTO.ResponseDTO.CenterResponseDto;
import com.example.VBS.Enum.DoseNo;
import com.example.VBS.exception.DoctorNotFoundException;
import com.example.VBS.exception.Dose1NotTakenException;
import com.example.VBS.exception.UserNotFoundException;
import com.example.VBS.model.*;
import com.example.VBS.repository.DoctorRepository;
import com.example.VBS.repository.UserRepository;
import com.example.VBS.service.AppointmentService;
import com.example.VBS.service.Dose1Service;
import com.example.VBS.service.Dose2Service;
import com.example.VBS.transformer.VaccinationCenterTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class AppointmentServiceImpl implements AppointmentService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    DoctorRepository doctorRepository;
    @Autowired
    Dose1Service dose1Service;
    @Autowired
    Dose2Service dose2Service;
    @Autowired
    private JavaMailSender emailSender;

    @Override
    public AppointmentResponseDto bookAppointment(AppointmentRequestDto appointmentRequestDto) throws UserNotFoundException, DoctorNotFoundException, Dose1NotTakenException {
        Optional<User> optionalUser=userRepository.findById(appointmentRequestDto.getUserId());
        if(!optionalUser.isPresent()){
            throw  new UserNotFoundException("User not found!!");
        }
        Optional<Doctor> optionalDoctor=doctorRepository.findById(appointmentRequestDto.getDoctorId());
        if(optionalDoctor.isEmpty()){
            throw new DoctorNotFoundException("Doctor not found!!");
        }
        User user=optionalUser.get();
        Doctor doctor=optionalDoctor.get();
        if(appointmentRequestDto.getDoseNo()== DoseNo.DOSE_1){
            Dose1 dose1=dose1Service.createDose1(user,appointmentRequestDto.getVaccineType());
            user.setDose1Taken(true);
            user.setDose1(dose1);
        }else{
            if(!user.isDose1Taken()){
                throw new Dose1NotTakenException("Dose1 is not taken!");
            }
            Dose2 dose2=dose2Service.createDose2(user,appointmentRequestDto.getVaccineType());
            user.setDose2Taken(true);
            user.setDose2(dose2);
        }

        Appointment appointment=Appointment.builder()
                .appointmentNo(String.valueOf(UUID.randomUUID()))
                .doseNo(appointmentRequestDto.getDoseNo())
                .user(user)
                .doctor(doctor).build();
        //doctor.getAppointments().add(appointment);
        user.getAppointments().add(appointment);
        User savedUser=userRepository.save(user);
        int size=savedUser.getAppointments().size();
        Appointment savedAppointment=savedUser.getAppointments().get(size-1);
        doctor.getAppointments().add(savedAppointment);
        doctorRepository.save(doctor);

        // send email
        String text = "Congrats!!" + user.getName() + " Your dose "+ appointmentRequestDto.getDoseNo() + " has been booked!!";
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("backendvbs6@gmail.com");
        message.setTo(user.getEmailId());
        message.setSubject("Appointment Booked !!!");
        message.setText(text);
        emailSender.send(message);


        CenterResponseDto centerResponseDto= VaccinationCenterTransformer.vaccinationCenterToCenterResponseDto(doctor.getVaccinationCenter());
        return AppointmentResponseDto.builder()
                .userName(user.getName())
                .doctorName(doctor.getName())
                .appointmentNo(appointment.getAppointmentNo())
                .dateOfAppointment(savedAppointment.getDateOfAppointment())
                .doseNo(appointment.getDoseNo())
                .vaccineType(appointmentRequestDto.getVaccineType())
                .centerResponseDto(centerResponseDto)
                .build();
    }
}
