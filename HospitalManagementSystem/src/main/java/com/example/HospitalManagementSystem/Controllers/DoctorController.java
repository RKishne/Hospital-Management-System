package com.example.HospitalManagementSystem.Controllers;

import com.example.HospitalManagementSystem.Entities.Doctor;
import com.example.HospitalManagementSystem.Exceptions.DoctorNotFound;
import com.example.HospitalManagementSystem.Exceptions.DoctorNotPresentAtThatLocation;
import com.example.HospitalManagementSystem.Exceptions.DoctorNotPresentAtThatLocationForYourSymptoms;
import com.example.HospitalManagementSystem.Exceptions.PatientNotFound;
import com.example.HospitalManagementSystem.Services.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/doctor")
public class DoctorController {
    @Autowired
    private DoctorService doctorService;

    @PostMapping("/addDoctor")
    public String addDoctor(@RequestBody Doctor doctor){
        return doctorService.addDoctor(doctor);
    }
    @DeleteMapping("/removeDoctor")
    private ResponseEntity removeDoctor(@RequestParam("doctorId")int doctorId) throws DoctorNotFound {
        try{
            String response =doctorService.removeDoctor(doctorId);
            return new ResponseEntity(response, HttpStatus.OK);
        }
        catch(Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/suggestDoctors")
    public ResponseEntity suggestDoctors(@RequestParam("patientId")int patientId){
        try{
            List<Doctor> doctorList=doctorService.suggestDoctors(patientId);
            return new ResponseEntity<>(doctorList,HttpStatus.OK);
        } catch (PatientNotFound e) {
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        } catch (DoctorNotPresentAtThatLocation e) {
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        } catch (DoctorNotPresentAtThatLocationForYourSymptoms e) {
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
}
