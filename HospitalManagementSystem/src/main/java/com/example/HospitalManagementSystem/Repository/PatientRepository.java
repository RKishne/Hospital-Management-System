package com.example.HospitalManagementSystem.Repository;

import com.example.HospitalManagementSystem.Entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient,Integer> {
}
