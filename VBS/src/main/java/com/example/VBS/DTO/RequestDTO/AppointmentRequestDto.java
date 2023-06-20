package com.example.VBS.DTO.RequestDTO;

import com.example.VBS.Enum.DoseNo;
import com.example.VBS.Enum.VaccineType;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AppointmentRequestDto {
    DoseNo doseNo;
    int userId;
    int doctorId;
    VaccineType vaccineType;
}
