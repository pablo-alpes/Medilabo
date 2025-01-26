package com.medilabo.frontendservice;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import com.medilabo.frontendservice.interfaces.MedicalServiceClient;
import com.medilabo.frontendservice.interfaces.PatientServiceClient;
import com.medilabo.shareddto.MedicalRecordsDTO;
import com.medilabo.shareddto.PatientDTO;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest //define the scope since the test is not in the same package
@AutoConfigureMockMvc
@EnableFeignClients
public class ControllerTesting {
    @Autowired
    private MockMvc mockMvc; //simulates the http requests
    @Autowired
    ObjectMapper objectMapper; //to convert objects to json and vice versa

    public WireMockServer wireMockServer;

    @Autowired
    public PatientServiceClient patientServiceClient;
    @Autowired
    public MedicalServiceClient medicalServiceClient;

    //private WireMockServer wireMockServer; //https://www.baeldung.com/introduction-to-wiremock

    //Setting up the WireMock for the feign clients
    @BeforeEach
    void setup() {
        int port = 8080;
        wireMockServer = new WireMockServer(WireMockConfiguration.options().port(port));
        wireMockServer.start();
        WireMock.configureFor("localhost", port);


        // Define expected request and response mappings
        WireMock.stubFor(WireMock.get(WireMock.urlEqualTo("/patients/2"))
                .willReturn(WireMock.aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody("{\"patient_id\":2,\"nom\":\"John\",\"prenom\":\"Doe\",\"naissance\":\"01/01/2000\",\"genre\":\"M\",\"adresse\":\"1234 Main St\",\"telephone\":\"1234567890\"}")));

        WireMock.stubFor(WireMock.get(WireMock.urlEqualTo("/medicalrecords/2"))
                .willReturn(WireMock.aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody("{\"patientId\":2,\"notes\":\"Le patient déclare qu'il ressent beaucoup de stress au travail Il se plaint également que son audition est anormale dernièrement\",\"patient\":\"TestBorderline\"}")));
    }

    @AfterEach
    void teardown() {
        if (wireMockServer != null) {
            wireMockServer.stop();
        }
    }

    @Test
    public void saveUpdateFormOnFeignClient() throws Exception {
        //ARRANGE
        Integer patient_id = 2;
        //before update value
        String before = medicalServiceClient.getPatientRecord(String.valueOf(patient_id)).toString();


        //defines a patient and medical record type
        PatientDTO patient = new PatientDTO(patient_id, "John", "Doe", "01/01/2000", "M", "1234 Main St", "1234567890");

        MedicalRecordsDTO medicalRecord = new MedicalRecordsDTO();
        medicalRecord.setPatientId(patient_id);
        medicalRecord.setNotes("Le patient déclare qu'il ressent beaucoup de stress au travail Il se plaint également que son audition est anormale dernièrement");
        medicalRecord.setPatient("TestBorderline");

        //ACT
        // Convert to JSON
        String patientJson = objectMapper.writeValueAsString(patient);
        String medicalRecordJson = objectMapper.writeValueAsString(medicalRecord);

        //patientServiceClient.updatePatient(patient_id, patient);
        //medicalServiceClient.updatePatientRecord(String.valueOf(patient_id), medicalRecord);

        //ASSERT - output & 200 output
        MvcResult result = mockMvc.perform(post("/patients/update/{id}", patient_id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("patient", patient.toString())
                        .param("medicalRecord", medicalRecord.toString()))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("medicalRecord"))
                .andExpect(redirectedUrl("redirect:/patients"))
                .andReturn();

        //logic - changes value after the update
        //AssertNotEquals(medicalServiceClient.getPatientRecord(String.valueOf(patient_id)).toString(), before);

    }
 }
