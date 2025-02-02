package com.medilabo.frontendservice;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.medilabo.shareddto.MedicalRecordsDTO;
import com.medilabo.shareddto.PatientDTO;
import com.medilabo.sharedinterface.MedicalServiceClient;
import com.medilabo.sharedinterface.PatientServiceClient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest //define the scope since the test is not in the same package
@AutoConfigureMockMvc
//@EnableFeignClients
public class ControllerTesting {
    @Autowired
    private MockMvc mockMvc; //simulates the http requests
    @Autowired
    ObjectMapper objectMapper; //to convert objects to json and vice versa

    //public WireMockServer wireMockServer;

    @MockitoBean
    public PatientServiceClient patientServiceClient;
    @MockitoBean
    public MedicalServiceClient medicalServiceClient;

    //private WireMockServer wireMockServer; //https://www.baeldung.com/introduction-to-wiremock

    //Setting up the WireMock for the feign clients
/*    @BeforeEach
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
*/

    @Test
    public void getPatientTest() throws Exception {
        // ARRANGE
        Integer patientId = 2;
        PatientDTO patient = new PatientDTO(patientId, "John", "Doe", "01/01/2000", "M", "1234 Main St", "1234567890");
        MedicalRecordsDTO medicalRecord = new MedicalRecordsDTO();
        medicalRecord.setPatientId(patientId);
        medicalRecord.setNotes("Le patient déclare qu'il ressent beaucoup de stress au travail Il se plaint également que son audition est anormale dernièrement");
        medicalRecord.setPatient("TestBorderline");

        // Mock the responses from the Feign clients
        when(patientServiceClient.getPatientById(patientId)).thenReturn(patient);
        when(medicalServiceClient.getPatientRecord(String.valueOf(patientId))).thenReturn(medicalRecord);

        // ACT & ASSERT
        MvcResult result = mockMvc.perform(get("/patients/get/{id}", patientId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("patient"))
                .andExpect(model().attributeExists("medicalRecord"))
                .andExpect(view().name("patient/update"))
                .andReturn();

        // Verify the model attributes
        PatientDTO returnedPatient = (PatientDTO) result.getModelAndView().getModel().get("patient");
        MedicalRecordsDTO returnedMedicalRecord = (MedicalRecordsDTO) result.getModelAndView().getModel().get("medicalRecord");

        assertEquals(patient, returnedPatient);
        assertEquals(medicalRecord, returnedMedicalRecord);
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
