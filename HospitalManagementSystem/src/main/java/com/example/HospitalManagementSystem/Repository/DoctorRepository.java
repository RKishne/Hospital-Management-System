package com.example.HospitalManagementSystem.Repository;

import com.example.HospitalManagementSystem.Entities.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorRepository extends JpaRepository<Doctor,Integer> {
}
