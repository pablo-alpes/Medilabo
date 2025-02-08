package com.medilabo.riskservice.controller;

import com.medilabo.riskservice.model.RiskProfile;
import com.medilabo.riskservice.service.RiskCalculatorService;
import com.medilabo.shareddto.MedicalRecordsDTO;
import com.medilabo.shareddto.PatientDTO;
import com.medilabo.shareddto.RiskProfileDTO;
import com.medilabo.sharedinterface.MedicalServiceClient;
import com.medilabo.sharedinterface.PatientServiceClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RiskAssessmentController {
    private PatientServiceClient patientServiceClient;
    private MedicalServiceClient medicalServiceClient;
    private RiskCalculatorService riskCalculatorService;
    private final RiskProfile riskProfile = new RiskProfile();

    @Autowired
    public RiskAssessmentController(PatientServiceClient patientServiceClient, MedicalServiceClient medicalServiceClient, RiskCalculatorService riskCalculatorService) {
        this.patientServiceClient = patientServiceClient;
        this.medicalServiceClient = medicalServiceClient;
        this.riskCalculatorService = riskCalculatorService;
    }

    @GetMapping(path="/patients/risk/{id}", produces = "application/json")
    public RiskProfile getRiskProfile(@PathVariable("id") Integer id) {
        //Retrieving patient and medical records

        PatientDTO patient = patientServiceClient.getPatientById(id); //injection of the feign client to avoid recall to the jpa repository and separating concerns
        System.out.println(patient);
        MedicalRecordsDTO medicalRecord = medicalServiceClient.getPatientRecord(String.valueOf(id));
        System.out.println(medicalRecord);

        //Extracting relevant risk factors
        String age = patient.getNaissance();
        age = String.valueOf(riskCalculatorService.calculateAge(age));
        String genre = patient.getGenre();
        String record = medicalRecord.getNotes();

        //Identifying number of diabetes triggers in the record
        Integer riskFactors = riskCalculatorService.triggersCount(record);

        riskProfile.setId(String.valueOf(id));
        riskProfile.setRisk(String.valueOf(riskCalculatorService.assessRisk(Integer.valueOf(age), genre, riskFactors)));

        return riskProfile;
    }
}

