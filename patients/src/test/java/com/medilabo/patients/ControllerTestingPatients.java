// This class is used to test the controller of the frontend-service
package com.medilabo.patients;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.medilabo.medicalrecordservice.model.MedicalRecord;
import com.medilabo.patients.model.Patient;
import com.medilabo.patients.repository.PatientRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.autoconfigure.webservices.client.AutoConfigureWebServiceClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest
public class ControllerTestingPatients {
    @Autowired
    private MockMvc mockMvc; //simulates the http requests

    @MockBean
    private PatientRepository patientRepository;

    @Autowired
    private ObjectMapper objectMapper; //to convert objects to json and vice versa

    @Test
    public void saveUpdateFormDirectOnController() throws Exception {
        //ARRANGE
        Integer patient_id = 2;

        //defines a patient and medical record type
        Patient patient = new Patient(patient_id, "John", "Doe", "01/01/2000", "M", "1234 Main St", "1234567890");

        MedicalRecord medicalRecord = new MedicalRecord();
        medicalRecord.setPatientId(patient_id);
        medicalRecord.setNotes("Le patient déclare qu'il ressent beaucoup de stress au travail Il se plaint également que son audition est anormale dernièrement");
        medicalRecord.setPatient("TestBorderline");

        // Convert to JSON
        String patientJson = objectMapper.writeValueAsString(patient);
        String medicalRecordJson = objectMapper.writeValueAsString(medicalRecord);

        //ACT
        //patientServiceClient.updatePatient(patient_id, patient);
        //medicalServiceClient.updatePatientRecord(String.valueOf(patient_id), medicalRecord);

        //ASSERT - output & 200 output
        // Perform test
        mockMvc.perform(post("/patients/update/{id}", patient_id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(patientJson))
                .andExpect(status().isOk());
/*
         MvcResult result = mockMvc.perform(post("/patients/update/{id}", patient_id)
                            .contentType(MediaType.APPLICATION_JSON)
                            .param("patient", patientJson)
                            .param("medicalRecord", medicalRecordJson)
                            .content(patientJson)
                            .content(medicalRecordJson))
                            .andExpect(status().isOk())
                            .andExpect(view().name("patient"))
                            .andExpect(view().name("medicalRecord"))
                            .andReturn();*/

    }
}
