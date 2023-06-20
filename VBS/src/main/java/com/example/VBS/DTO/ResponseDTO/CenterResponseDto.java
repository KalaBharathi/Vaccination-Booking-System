package com.example.VBS.DTO.ResponseDTO;

import com.example.VBS.Enum.CenterType;
import lombok.*;
import lombok.experimental.FieldDefaults;


@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class CenterResponseDto {
    String name;

    String address;

    CenterType centerType;
}
