package com.example.HospitalManagementSystem.Services;

import com.example.HospitalManagementSystem.Entities.Patient;
import com.example.HospitalManagementSystem.Exceptions.PatientNotFound;
import com.example.HospitalManagementSystem.Repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PatientService {
    @Autowired
    private PatientRepository patientRepository;
    public String addPatient(Patient patient) {
        patientRepository.save(patient);
        return "Patient has been successfully added in DB.";
    }

    public String removePatient(int patientId) throws PatientNotFound {

        Optional<Patient> optionalPatient=patientRepository.findById(patientId);

        //validity check
        if(!optionalPatient.isPresent()){
            throw new PatientNotFound("Patient Id is Invalid or patientId is not present in DB. ");
        }
        Patient patient=optionalPatient.get();

        patientRepository.delete(patient);
        return "Patient whose Id is "+patientId+" has been removed successfully from the Db.";
    }
}
