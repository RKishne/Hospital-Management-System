package com.example.HospitalManagementSystem.Exceptions;

public class DoctorNotPresentAtThatLocation extends Exception{
    public DoctorNotPresentAtThatLocation(String message) {
        super(message);
    }
}
