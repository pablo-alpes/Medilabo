package com.medilabo.servicesInterface;

import com.medilabo.model.MedicalRecord;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name = "medicalrecords-service")
public interface MedicalServiceClient {
    @RequestMapping("/patients/record/get/{id}")
    MedicalRecord getPatientRecord(@PathVariable String id);

    @PostMapping(path="/patients/record/update/{id}")
    void updatePatientRecord(@PathVariable String id, @RequestBody MedicalRecord medicalRecord);
}
