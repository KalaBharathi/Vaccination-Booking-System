package com.example.VBS.service.Impl;

import com.example.VBS.Enum.VaccineType;
import com.example.VBS.model.Dose2;
import com.example.VBS.model.User;
import com.example.VBS.service.Dose2Service;
import org.springframework.stereotype.Service;

import java.util.UUID;
@Service
public class Dose2ServiceImpl implements Dose2Service {
    @Override
    public Dose2 createDose2(User user, VaccineType vaccineType) {
        Dose2 dose2=Dose2.builder()
                .doseID(String.valueOf(UUID.randomUUID()))
                .user(user)
                .vaccineType(vaccineType)
                .build();
        return dose2;
    }
}
