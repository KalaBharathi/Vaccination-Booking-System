package com.example.VBS.service.Impl;

import com.example.VBS.Enum.VaccineType;
import com.example.VBS.model.Dose1;
import com.example.VBS.model.User;
import com.example.VBS.service.Dose1Service;
import org.springframework.stereotype.Service;

import java.util.UUID;
@Service
public class Dose1ServiceImpl implements Dose1Service {
    @Override
    public Dose1 createDose1(User user, VaccineType vaccineType) {
        Dose1 dose1=Dose1.builder()
                .doseID(String.valueOf(UUID.randomUUID()))
                .user(user)
                .vaccineType(vaccineType)
                .build();
        return dose1;

    }
}
