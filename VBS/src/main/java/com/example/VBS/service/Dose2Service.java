package com.example.VBS.service;

import com.example.VBS.Enum.VaccineType;
import com.example.VBS.model.Dose2;
import com.example.VBS.model.User;

public interface Dose2Service {
    public Dose2 createDose2(User user, VaccineType vaccineType);
}
