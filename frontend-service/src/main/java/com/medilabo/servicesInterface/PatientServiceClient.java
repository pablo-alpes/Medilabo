package com.medilabo.servicesInterface;

import com.medilabo.model.Patient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "patients-service")
public interface PatientServiceClient {
    @RequestMapping("/patients")
    List<Patient> getAllPatients();

    @RequestMapping("/patients/get/{id}")
    Patient getPatientById(@PathVariable Integer id);

    @PostMapping(path="/patient/update/{id}", consumes = {"*/*"})
    void updatePatient(@PathVariable("id") Integer id, @RequestBody Patient patient);

    @PostMapping("/patients/delete/{id}")
    void deletePatient(@PathVariable Integer id);
}
