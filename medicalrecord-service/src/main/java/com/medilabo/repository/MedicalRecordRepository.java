package com.medilabo.repository;

import com.medilabo.model.MedicalRecord;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MedicalRecordRepository extends MongoRepository<MedicalRecord, String> {}
//https://spring.io/guides/gs/accessing-data-mongodb