package com.medilabo.repository;

import com.medilabo.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.repository.ListCrudRepository;

public interface PatientRepository extends JpaRepository<Patient, Integer> {
    //List<PatientProjection> findAllProjectedBy(); // to avoid creation of the full DTO
}
