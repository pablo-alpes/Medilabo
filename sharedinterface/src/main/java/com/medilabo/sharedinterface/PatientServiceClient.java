package com.medilabo.sharedinterface;

import com.medilabo.shareddto.PatientDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import java.util.List;



@FeignClient(name = "patientservice", url = "http://localhost:8080")
public interface PatientServiceClient {
    @RequestMapping(path="/patients")
    List<PatientDTO> getAllPatients();

    @RequestMapping(path="/patients/get/{id}")
    PatientDTO getPatientById(@PathVariable Integer id);

    @PostMapping(path="/patients/update/{id}")
    void updatePatient(
            @PathVariable("id") Integer id,
            @RequestBody PatientDTO patient);

    @PostMapping(path="/patients/delete/{id}")
    void deletePatient(@PathVariable Integer id);
}
