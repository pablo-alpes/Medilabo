package com.medilabo.controller;

import com.medilabo.model.MedicalRecord;
import com.medilabo.repository.MedicalRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Service
public class Controller {
    //Controller retrieves the Records "document" from the mongodb database
    @Autowired
    private MedicalRecordRepository medicalRecordRepository;

    @GetMapping(path="/patients/record/get/{id}")
    public MedicalRecord getMedicalRecords(@PathVariable("id") String id) {
        return medicalRecordRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("invalid patient id:" + id));
    }
}
