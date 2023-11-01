package com.example.HospitalManagementSystem.Entities;

import com.example.HospitalManagementSystem.Enums.City;
import com.example.HospitalManagementSystem.Enums.Symptoms;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "patients")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int patientId;

    @NotBlank(message = "Name is required")
    @Size(min = 3, message = "Name should have at least 3 characters")
    private String patientName;

    @Size(max = 20, message = "City should have at most 20 characters")
    private String patientCity;

    @Email(message = "Invalid email address")
    private String patientEmail;

    @Pattern(regexp = "\\d{10}", message = "Invalid phone number")
    private String patientPhoneNo;

    @Enumerated(value = EnumType.STRING)
    private Symptoms symptoms;

}
