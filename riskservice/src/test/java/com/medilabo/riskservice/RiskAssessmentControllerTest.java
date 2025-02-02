/*
package com.medilabo.riskservice;
/

import com.medilabo.riskservice.controller.RiskAssessmentController;
import com.medilabo.riskservice.service.RiskCalculatorService;
import com.medilabo.shareddto.MedicalRecordsDTO;
import com.medilabo.shareddto.PatientDTO;
import com.medilabo.sharedinterface.MedicalServiceClient;
import com.medilabo.sharedinterface.PatientServiceClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(RiskAssessmentController.class)
//https://medium.com/kth-distributed-systems/testing-microservices-in-spring-boot-applications-tools-and-techniques-b9c27d865f88
public class RiskAssessmentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private PatientServiceClient patientServiceClient;

    @Mock
    private MedicalServiceClient medicalServiceClient;

    @Mock
    private RiskCalculatorService riskCalculatorService = new RiskCalculatorService();

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @Disabled
    public void testGetRiskProfile() throws Exception {
        // Mocking the patient and medical records
        PatientDTO patient = new PatientDTO();
        patient.setNaissance("1990-01-01");
        patient.setGenre("M");

        MedicalRecordsDTO medicalRecord = new MedicalRecordsDTO();
        medicalRecord.setNotes("This is a test record with fumeur and anormal microalbumine");

        //Mocking the services replies
        when(patientServiceClient.getPatientById(1)).thenReturn(patient);
        when(medicalServiceClient.getPatientRecord("1")).thenReturn(medicalRecord);
        when(riskCalculatorService.calculateAge("1990-01-01")).thenReturn(33);
        when(riskCalculatorService.triggersCount("This is a test record with fumeur and anormal microalbumine")).thenReturn(3);
        when(riskCalculatorService.assessRisk(33, "M", 3)).thenReturn(RiskCalculatorService.RiskLevel.BORDERLINE);

        // Doing the GET and checking it replies correctly with the assessment
        mockMvc.perform(get("/patient/risk/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("BORDERLINE"));
    }
}
*/