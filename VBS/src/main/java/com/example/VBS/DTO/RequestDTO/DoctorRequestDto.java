package com.example.VBS.DTO.RequestDTO;

import com.example.VBS.Enum.Gender;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;


@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DoctorRequestDto {
    int centerId;

    String name;

    int age;

    String emailId;

    String mobNo;

    Gender gender;

}
