package com.example.HospitalManagementSystem.Controllers;

import com.example.HospitalManagementSystem.Entities.Patient;
import com.example.HospitalManagementSystem.Exceptions.PatientNotFound;
import com.example.HospitalManagementSystem.Services.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/patient")
public class PatientController {
    @Autowired
    private PatientService patientService;

    @PostMapping("/addPatient")
    public String addPatient(@RequestBody Patient patient){
        return patientService.addPatient(patient);
    }
    @DeleteMapping("/removePatient")
    public ResponseEntity removePatient(@RequestParam("patientId")int patientId) throws PatientNotFound {
        try{
            String response=patientService.removePatient(patientId);
            return new ResponseEntity(response, HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
}
