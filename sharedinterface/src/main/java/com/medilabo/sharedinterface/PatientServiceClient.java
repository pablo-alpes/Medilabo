package com.medilabo.sharedinterface;

import com.medilabo.shareddto.PatientDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@FeignClient(name = "patientservice")
public interface PatientServiceClient {
    @RequestMapping(path="/backend/patients")
    List<PatientDTO> getAllPatients();

    @RequestMapping(path="/backend/patients/get/{id}")
    PatientDTO getPatientById(@PathVariable Integer id);

    @PostMapping(path="/backend/patients/update/{id}")
    void updatePatient(
            @PathVariable("id") Integer id,
            @RequestBody PatientDTO patient);

    @GetMapping(path="/backend/patients/delete/{id}")
    void deletePatient(@PathVariable Integer id);
}
