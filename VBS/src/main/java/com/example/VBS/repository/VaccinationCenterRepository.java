package com.example.VBS.repository;

import com.example.VBS.Enum.CenterType;
import com.example.VBS.model.VaccinationCenter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VaccinationCenterRepository extends JpaRepository<VaccinationCenter,Integer> {
    List<VaccinationCenter> findByCenterType(CenterType centerType);
}
