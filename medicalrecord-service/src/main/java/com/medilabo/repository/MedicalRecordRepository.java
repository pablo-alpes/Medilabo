package com.medilabo.repository;

import com.medilabo.model.MedicalRecord;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface MedicalRecordRepository extends MongoRepository<MedicalRecord, String> {
    MedicalRecord findByPatientId(Integer patientId);
}

//https://spring.io/guides/gs/accessing-data-mongodb