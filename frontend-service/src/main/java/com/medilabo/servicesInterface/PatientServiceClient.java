package com.medilabo.servicesInterface;

import com.medilabo.model.Patient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@FeignClient(name = "patients-service")
public interface PatientServiceClient {
    @RequestMapping("/patients")
    List<Patient> getAllPatients();

    @RequestMapping("/patients/get/{id}")
    Patient getPatientById(@PathVariable Integer id);

    @PostMapping("/patients/update/{id}")
    void updatePatient(@PathVariable Integer id);

    @PostMapping("/patients/delete/{id}")
    void deletePatient(@PathVariable Integer id);
}
