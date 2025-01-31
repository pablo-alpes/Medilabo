package com.medilabo.medicalrecordservice.controller;

import com.medilabo.medicalrecordservice.model.MedicalRecord;
import com.medilabo.medicalrecordservice.repository.MedicalRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

import static org.apache.logging.log4j.ThreadContext.isEmpty;

@RestController
public class MedicalRecordsController {
    //Controller retrieves the Records "document" from the mongodb database
    @Autowired
    private MedicalRecordRepository medicalRecordRepository;

    @GetMapping(path="/patients/record/get/{id}")
    public MedicalRecord getMedicalRecord(@PathVariable("id") Integer id, Model model) {
        MedicalRecord medicalRecord = medicalRecordRepository.findByPatientId(id);
        model.addAttribute("notes", medicalRecord);
        return medicalRecord;
    }

    @PutMapping(path="/patients/record/update/{id}", consumes="application/json")
    public ResponseEntity<?> updateMedicalRecord(@PathVariable("id") String id, @RequestBody MedicalRecord medicalRecord) {
        // https://nuwanharshakumarapiyarathna.medium.com/spring-boot-put-request-with-mongodb-21a2a91cd089
            MedicalRecord patient = medicalRecordRepository.findByPatientId(Integer.valueOf(id));
            if(patient.get_id().isBlank()) {
                return new ResponseEntity<>(
                        "Patient id found",
                        HttpStatus.BAD_REQUEST
                );
            }
            else {
                patient.setPatient(medicalRecord.getPatient());
                patient.setNotes(medicalRecord.getNotes());
                medicalRecordRepository.save(patient);
            }
            return new ResponseEntity<>(
                    "Patient Successfully Updated",
                    HttpStatus.OK
            );
    }
}
