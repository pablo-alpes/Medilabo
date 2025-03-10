package com.medilabo.frontendservice.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.medilabo.shareddto.MedicalRecordsDTO;
import com.medilabo.shareddto.PatientDTO;
import com.medilabo.shareddto.RiskProfileDTO;
import com.medilabo.sharedinterface.MedicalServiceClient;
import com.medilabo.sharedinterface.PatientServiceClient;
import com.medilabo.sharedinterface.RiskServiceClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller //Returns a view, so spring recognises this
@EnableFeignClients
public class FrontEndController {

    @Autowired
    private PatientServiceClient patientServiceClient;
    @Autowired
    private MedicalServiceClient medicalServiceClient;
    @Autowired
    private RiskServiceClient riskServiceClient;

    @Autowired
    private ObjectMapper objectMapper = new ObjectMapper();

    //https://spring.io/guides/gs/accessing-data-mysql
    @GetMapping(path="/patients")
    public String getPatients(Model model) {
        //api reply:
        List<PatientDTO> patients = patientServiceClient.getAllPatients(); //injection of the feign client to avoid recall to the jpa repository and separating concerns
        model.addAttribute("patients", patients);
        return "patients";
    }

    @PostMapping(path="/patients/update/{id}", consumes = "application/x-www-form-urlencoded")
    public String updatePatient(@PathVariable("id") Integer id, @ModelAttribute("patient") PatientDTO patient, @ModelAttribute("medicalRecord") MedicalRecordsDTO medicalRecord, Model model) {
        //model attribute medical records binds with the thymeleaf template
        //this method acts as an umbrella to perform the update in both databases and registries separately at level of microservices
        patient.setPatient_id(id);  // Ensure the ID is set
        medicalRecord.setPatientId(id);
        medicalRecord.setPatient(patient.getPrenom());

        // Convert to JSON as per the Request Body needed
        try {
            //String patientJson = objectMapper.writeValueAsString(patient);
            String medicalRecordJson = objectMapper.writeValueAsString(medicalRecord);

            patientServiceClient.updatePatient(id, patient); //injection of the feign client with plain object
            medicalServiceClient.updatePatientRecord(String.valueOf(id), "application/json", medicalRecordJson);
            return "redirect:/patients";
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e.getMessage()); // TODO do logging
        }
    }

    @GetMapping(path="/patients/get/{id}")
    public String getPatient(@PathVariable("id") Integer id, Model model) {
        PatientDTO patient = patientServiceClient.getPatientById(id); //injection of the feign client to avoid recall to the jpa repository and separating concerns
        MedicalRecordsDTO medicalRecord = medicalServiceClient.getPatientRecord(String.valueOf(id));
        RiskProfileDTO riskProfile = riskServiceClient.getRiskProfile(id);

        patient.setPatient_id(id); //it is needed to position the id in the list it's bind after in the template

        model.addAttribute("risk", riskProfile);
        model.addAttribute("patient", patient);
        model.addAttribute("medicalRecord", medicalRecord);
        return "patient/update";
    }

    @GetMapping(path="/patients/delete/{id}")
    public String deletePatient(@PathVariable("id") Integer id) {
        patientServiceClient.deletePatient(id);
        return "redirect:/patients";
    }

    //Home page of the website
    @GetMapping("/")
    public String home(Model model) {
        return "index";
    }
    //https://medium.com/@tanuchaudhary1900/create-a-rest-api-with-spring-boot-and-thymeleaf-b7367ffc2967

}