package com.example.VBS.service;

import com.example.VBS.Enum.VaccineType;
import com.example.VBS.model.Dose1;
import com.example.VBS.model.User;

public interface Dose1Service {
    public Dose1 createDose1(User user, VaccineType vaccineType);

}
