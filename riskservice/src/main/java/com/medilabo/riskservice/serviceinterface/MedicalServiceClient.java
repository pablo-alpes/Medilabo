package com.medilabo.riskservice.serviceinterface;

import com.medilabo.shareddto.MedicalRecordsDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "medicalrecordservice")
public interface MedicalServiceClient {
    @RequestMapping("/patients/record/get/{id}")
    MedicalRecordsDTO getPatientRecord(@PathVariable String id);
}