package com.medilabo.controller;

import com.medilabo.model.MedicalRecord;
import com.medilabo.repository.MedicalRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
public class Controller {
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
