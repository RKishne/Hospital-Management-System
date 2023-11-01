package com.example.HospitalManagementSystem.Services;

import com.example.HospitalManagementSystem.Entities.Doctor;
import com.example.HospitalManagementSystem.Entities.Patient;
import com.example.HospitalManagementSystem.Exceptions.DoctorNotFound;
import com.example.HospitalManagementSystem.Exceptions.DoctorNotPresentAtThatLocation;
import com.example.HospitalManagementSystem.Exceptions.DoctorNotPresentAtThatLocationForYourSymptoms;
import com.example.HospitalManagementSystem.Exceptions.PatientNotFound;
import com.example.HospitalManagementSystem.Repository.DoctorRepository;
import com.example.HospitalManagementSystem.Repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class DoctorService {
    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    private PatientRepository patientRepository;

    public String addDoctor(Doctor doctor) {
        doctorRepository.save(doctor);
        return "Doctor Has been Successfully Added in DB.";
    }

    public String removeDoctor(int doctorId) throws DoctorNotFound {
        Optional<Doctor> optionalDoctor=doctorRepository.findById(doctorId);

        if(!optionalDoctor.isPresent()){
            throw new DoctorNotFound("Doctor ID is not valid or You entered Id is not present in the DB");
        }
        Doctor doctor=optionalDoctor.get();

        doctorRepository.delete(doctor);
        return "Doctor with Id "+doctorId+" has been Removed Successfully from the DB.";
    }

    public List<Doctor> suggestDoctors(int patientId) throws PatientNotFound, DoctorNotPresentAtThatLocation, DoctorNotPresentAtThatLocationForYourSymptoms {
        //Make Combination Of doctor speciality and inside that symptoms present int that;
        //this hashmap is used for checking all logics .
        HashMap<String,List<String>> SpecialityAndSymptoms=new HashMap<>();

        List<String> InsideOrthopedicSymptoms=new ArrayList<>();

        InsideOrthopedicSymptoms.add("ARTHRITIS");
        InsideOrthopedicSymptoms.add("BACK_PAIN");
        InsideOrthopedicSymptoms.add("TISSUE_INJURIES");

        List<String> InsideGynecologySymptoms=new ArrayList<>();

        InsideGynecologySymptoms.add("DYSMENORRHEA");

        List<String> InsideDermatologySymptoms=new ArrayList<>();

        InsideDermatologySymptoms.add("SKIN_INFECTION");
        InsideDermatologySymptoms.add("SKIN_BURN");

        List<String> InsideENTSymptoms=new ArrayList<>();

        InsideENTSymptoms.add("EAR_PAIN");

        SpecialityAndSymptoms.put("ORTHOPEDIC",InsideOrthopedicSymptoms);
        SpecialityAndSymptoms.put("GYNECOLOGY",InsideGynecologySymptoms);
        SpecialityAndSymptoms.put("DERMATOLOGY",InsideDermatologySymptoms);
        SpecialityAndSymptoms.put("ENT",InsideENTSymptoms);

        //check validity of patient
        Optional<Patient> optionalPatient=patientRepository.findById(patientId);

        if(!optionalPatient.isPresent()){
            throw new PatientNotFound("You Entered PatientId is Invalid!! Please confirmed once and reentered");
        }
        Patient patient=optionalPatient.get();

        //getting all doctors present in db
        List<Doctor> doctorList=doctorRepository.findAll();

        String patientCity=patient.getPatientCity();
        String patientSymptom= String.valueOf(patient.getSymptoms());

        boolean flag1=false;//this flag is used for checking doctor location is matched same as patient location or not
        boolean flag2=false;//this flag is used for checking symptoms validity and speciality validity

        List<Doctor> ansList=new ArrayList<>();//doctor list which satify all logic

        for(Doctor doctor:doctorList){
            String doctorCity= String.valueOf(doctor.getDoctorCity());
            String doctorSpeciality= String.valueOf(doctor.getSpeciality());
            if(doctorCity.equals(patientCity)){
                flag1=true;
                for(Map.Entry<String,List<String>> entry: SpecialityAndSymptoms.entrySet()){
                    String speciality=entry.getKey();
                    List<String> listOfSymptoms=entry.getValue();
                    if(doctorSpeciality.equals(speciality)){
                        for(String symptom:listOfSymptoms){
                            if(patientSymptom.equals(symptom)){
                                ansList.add(doctor);
                                flag2=true;
                                break;
                            }
                        }
                    }
                }
            }
        }
        if(flag1==false){
            throw new DoctorNotPresentAtThatLocation("We are still waiting to expand to your location");
        }
        if(flag2==false){
            throw new DoctorNotPresentAtThatLocationForYourSymptoms("There isn’t any doctor present at your location for your symptom");
        }
        return ansList;
    }
}
