package com.medilabo.frontendservice.interfaces;

import com.medilabo.shareddto.PatientDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "patientservice")
public interface PatientServiceClient {
    @RequestMapping("/patients")
    List<PatientDTO> getAllPatients();

    @RequestMapping("/patients/get/{id}")
    PatientDTO getPatientById(@PathVariable Integer id);

    @PostMapping(path="/patients/update/{id}")
    void updatePatient(@PathVariable Integer id, @RequestBody String patient);

    @PostMapping("/patients/delete/{id}")
    void deletePatient(@PathVariable Integer id);
}
