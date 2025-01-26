package com.medilabo.patients.repository;

import com.medilabo.patients.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Integer> {
    //List<PatientProjection> findAllProjectedBy(); // to avoid creation of the full DTO
}
