package com.medilabo.controller;

import com.medilabo.model.MedicalRecord;
import com.medilabo.model.Patient;
import com.medilabo.repository.MedicalRecordRepository;
import com.medilabo.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class Controller {
    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private MedicalRecordRepository medicalRecordRepository;

    //https://spring.io/guides/gs/accessing-data-mysql
    @RequestMapping(path="/patients")
    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    @GetMapping(path="/patients/get/{id}")
    public Patient getPatientById(@PathVariable("id") Integer id, Model model) {
        Patient patient = patientRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("invalid patient id:" + id));
        model.addAttribute("patient", patient);
        return patient;
    }

    @PostMapping(path="/patients/update/{id}")
    public void updatePatient(@PathVariable("id") Integer id, @ModelAttribute("patient") Patient patient) {
        patient.setPatient_id(id);
        patientRepository.save(patient);
    }

    //https://stackoverflow.com/questions/43719828/update-or-saveorupdate-in-crudrepository

    @PostMapping(path="/patients/delete/{id}")
    public void deletePatient(@PathVariable("id") Integer id) {
        patientRepository.deleteById(id);
    }
}
