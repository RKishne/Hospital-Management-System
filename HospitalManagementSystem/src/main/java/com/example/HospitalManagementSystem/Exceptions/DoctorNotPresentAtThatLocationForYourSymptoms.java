package com.example.HospitalManagementSystem.Exceptions;

public class DoctorNotPresentAtThatLocationForYourSymptoms extends Exception{
    public DoctorNotPresentAtThatLocationForYourSymptoms(String message) {
        super(message);
    }
}
