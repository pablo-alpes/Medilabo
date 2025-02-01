package com.medilabo.riskservice.serviceinterface;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.medilabo.shareddto.PatientDTO;

@FeignClient(name = "patientservice")
public interface PatientServiceClient {

    @RequestMapping("/patients/get/{id}")
    PatientDTO getPatientById(@PathVariable Integer id);

}
