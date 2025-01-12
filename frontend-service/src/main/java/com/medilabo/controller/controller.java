package com.medilabo.controller;

import com.medilabo.model.Patient;
import com.medilabo.servicesInterface.PatientServiceClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller //Returns a view, so spring recognises this
public class controller {
    @Autowired
    private PatientServiceClient patientServiceClient;

    //https://spring.io/guides/gs/accessing-data-mysql
    @GetMapping(path="/patients")
    public String getPatients(Model model) {
        //api reply:
        List<Patient> patients = patientServiceClient.getAllPatients(); //injection of the feign client to avoid recall to the jpa repository and separating concerns
        model.addAttribute("patients", patients);
        return "patients";
    }

    @PostMapping(path="/patients/update/{id}", consumes = {"*/*"})
    public String updatePatient(@PathVariable("id") Integer id, @RequestBody Patient patient, Model model) {
            patientServiceClient.updatePatient(id, patient);
            return "redirect:/patients";
    }

    @GetMapping(path="/patients/get/{id}")
    public String getPatient(@PathVariable("id") Integer id, Model model) {
        Patient patient = patientServiceClient.getPatientById(id); //injection of the feign client to avoid recall to the jpa repository and separating concerns
        patient.setPatient_id(id); //it is needed to position the id in the list it's bind after in the template
        model.addAttribute("patient", patient);
        return "patient/update";
    }

    @GetMapping(path="/patients/delete/{id}")
    public String deletePatient(@PathVariable("id") Integer id) {
        patientServiceClient.deletePatient(id);
        return "redirect:/patients";
    }

    //Home page of the website
    @GetMapping("/home")
    public String home(Model model) {
        return "index";
    }
    //https://medium.com/@tanuchaudhary1900/create-a-rest-api-with-spring-boot-and-thymeleaf-b7367ffc2967

}