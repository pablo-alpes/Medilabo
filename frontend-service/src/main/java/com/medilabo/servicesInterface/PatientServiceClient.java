package com.medilabo.servicesInterface;

import com.medilabo.model.Patient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@FeignClient(name = "patients-service")
public interface PatientServiceClient {
    @RequestMapping("/patients")
    List<Patient> getAllPatients();
}
