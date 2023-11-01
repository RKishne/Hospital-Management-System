package com.example.HospitalManagementSystem.Entities;

import com.example.HospitalManagementSystem.Enums.City;
import com.example.HospitalManagementSystem.Enums.Speciality;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import jdk.jfr.Name;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "doctors")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int doctorId;

    @NotBlank(message = "Name is required")
    @Size(min = 3, message = "Name should have at least 3 characters")
    private String doctorName;

    @Enumerated(value = EnumType.STRING)
    private City doctorCity;

    @Email(message = "Invalid email address")
    private String doctorEmail;

    @Pattern(regexp = "\\d{10}", message = "Invalid phone number")
    private String doctorPhoneNo;

    @Enumerated(value = EnumType.STRING)
    private Speciality speciality;
}
