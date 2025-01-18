package com.medilabo.servicesInterface;

import com.medilabo.model.MedicalRecord;
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

    @PostMapping(path="/patients/update/{id}")
    void updatePatient(@PathVariable Integer id, @RequestBody Patient patient);

    @PostMapping("/patients/delete/{id}")
    void deletePatient(@PathVariable Integer id);

    @RequestMapping("/patients/records/get/{id}")
    MedicalRecord getPatientRecord(@PathVariable Integer id);

    //@PostMapping(path="/patients/records/update/{id}")
    //void updatePatientRecord(@PathVariable Integer id, @RequestBody Patient patient);
    //TODO* To define how the request of the field will be passed into here
}
