package com.medilabo.riskservice.controller;

import com.medilabo.riskservice.service.RiskCalculatorService;
import com.medilabo.shareddto.MedicalRecordsDTO;
import com.medilabo.shareddto.PatientDTO;
import com.medilabo.sharedinterface.MedicalServiceClient;
import com.medilabo.sharedinterface.PatientServiceClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableFeignClients
public class RiskAssessmentController {
    @Autowired
    private PatientServiceClient patientServiceClient;
    @Autowired
    private MedicalServiceClient medicalServiceClient;
    @Autowired
    private RiskCalculatorService riskCalculatorService;

    @GetMapping(path="/patient/risk/{id}")
    public String getRiskProfile (@PathVariable("id") Integer id) {
        //Retrieving patient and medical records
        PatientDTO patient = patientServiceClient.getPatientById(id); //injection of the feign client to avoid recall to the jpa repository and separating concerns
        MedicalRecordsDTO medicalRecord = medicalServiceClient.getPatientRecord(String.valueOf(id));

        //Extracting relevant risk factors
        String age = patient.getNaissance();
        age = String.valueOf(RiskCalculatorService.calculateAge(age));
        String genre = patient.getGenre();
        String record = medicalRecord.getNotes();

        //Identifying number of diabetes triggers in the record
        Integer riskFactors = RiskCalculatorService.triggersCount(record);

        return String.valueOf(RiskCalculatorService.assessRisk(Integer.valueOf(age), genre, riskFactors));
    }
}

