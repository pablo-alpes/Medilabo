package com.medilabo.medicalrecordservice.repository;

import com.medilabo.medicalrecordservice.model.MedicalRecord;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MedicalRecordRepository extends MongoRepository<MedicalRecord, String> {
    MedicalRecord findByPatientId(Integer patientId);
}

//https://spring.io/guides/gs/accessing-data-mongodb