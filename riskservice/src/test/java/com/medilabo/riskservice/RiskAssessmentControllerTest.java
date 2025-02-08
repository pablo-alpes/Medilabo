package com.medilabo.riskservice;

import com.medilabo.riskservice.controller.RiskAssessmentController;
import com.medilabo.riskservice.service.RiskCalculatorService;
import com.medilabo.shareddto.MedicalRecordsDTO;
import com.medilabo.shareddto.PatientDTO;
import com.medilabo.sharedinterface.MedicalServiceClient;
import com.medilabo.sharedinterface.PatientServiceClient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;



//@ExtendWith(SpringExtension.class)
//@AutoConfigureMockMvc
//@AutoConfigureWebServiceClient
@WebMvcTest(RiskAssessmentController.class)
//@SpringBootTest
//https://medium.com/Rkth-distributed-systems/testing-microservices-in-spring-boot-applications-tools-and-techniques-b9c27d865f88
class RiskAssessmentControllerTest {

    @Autowired
    private MockMvc mockMvc;
    //TODO -- https://www.baeldung.com/spring-unsatisfied-dependency
    //TODO -- https://www.baeldung.com/spring-beancreationexception

    @MockBean
    private MedicalServiceClient medicalServiceClient;

    @MockBean
    private PatientServiceClient patientServiceClient;

    @MockBean
    private RiskCalculatorService riskCalculatorService;

    @Test
    public void contextLoads() {
        // This test will fail if the application context cannot be loaded
        assert(mockMvc != null);
    }

    @Test
    public void testGetRiskProfile() throws Exception {
        // Mocking the patient and medical records
        PatientDTO patient = new PatientDTO();
        patient.setNaissance("1990-01-01");
        patient.setGenre("M");

        MedicalRecordsDTO medicalRecord = new MedicalRecordsDTO();
        medicalRecord.setNotes("This is a test record with fumeur and anormal microalbumine");

        // Mocking service replies
        when(patientServiceClient.getPatientById(anyInt())).thenReturn(patient);
        when(medicalServiceClient.getPatientRecord(anyString())).thenReturn(medicalRecord);
        when(riskCalculatorService.calculateAge(anyString())).thenReturn(34);
        when(riskCalculatorService.triggersCount(anyString())).thenReturn(3);
        when(riskCalculatorService.assessRisk(34, "M", 3))
                .thenReturn(RiskCalculatorService.RiskLevel.BORDERLINE);

        // Doing the GET and checking it replies correctly with the assessment
        mockMvc.perform(get("/patients/risk/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("BORDERLINE"));
    }
}
