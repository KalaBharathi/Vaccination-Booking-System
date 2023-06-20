package com.example.VBS.DTO.ResponseDTO;

import com.example.VBS.Enum.DoseNo;
import com.example.VBS.Enum.VaccineType;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class AppointmentResponseDto {
    String userName;
    String doctorName;
    String appointmentNo;
    Date dateOfAppointment;
    DoseNo doseNo;
    CenterResponseDto centerResponseDto;
    VaccineType vaccineType;
}
