package com.medilabo.repository;

import com.medilabo.model.Patient;
import org.springframework.data.repository.ListCrudRepository;

import java.util.List;

public interface PatientRepository extends ListCrudRepository<Patient, Integer> {
    //List<PatientProjection> findAllProjectedBy(); // to avoid creation of the full DTO
}