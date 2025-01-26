package com.medilabo.frontendservice.interfaces;

import com.medilabo.shareddto.MedicalRecordsDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name = "medicalrecordservice")
public interface MedicalServiceClient {
    @RequestMapping("/patients/record/get/{id}")
    MedicalRecordsDTO getPatientRecord(@PathVariable String id);

    @PostMapping(path="/patients/record/update/{id}")
    void updatePatientRecord(@PathVariable String id, @RequestBody String medicalRecord);
}
