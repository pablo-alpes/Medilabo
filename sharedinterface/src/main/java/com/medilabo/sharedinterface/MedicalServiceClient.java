package com.medilabo.sharedinterface;

import com.medilabo.shareddto.MedicalRecordsDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "medicalrecordservice")
public interface MedicalServiceClient {
    @RequestMapping(path="/patients/record/get/{id}")
    MedicalRecordsDTO getPatientRecord(@PathVariable String id);

    @PutMapping(path= "/patients/record/update/{id}", consumes = "application/json")
    void updatePatientRecord(@RequestParam("id") String id,
                             @RequestHeader("Content-Type") String contentType,
                             String medicalRecordJson);
}