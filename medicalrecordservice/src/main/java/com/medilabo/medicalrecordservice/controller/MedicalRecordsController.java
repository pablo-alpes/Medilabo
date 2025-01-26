package com.medilabo.medicalrecordservice.controller;

import com.medilabo.medicalrecordservice.model.MedicalRecord;
import com.medilabo.medicalrecordservice.repository.MedicalRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping(path="/patients/record/update/{id}")
    public void updateMedicalRecord(@PathVariable("id") Integer id, @RequestBody MedicalRecord medicalRecord) {
        medicalRecord.setPatientId(id);
        medicalRecordRepository.save(medicalRecord);
    }
}
