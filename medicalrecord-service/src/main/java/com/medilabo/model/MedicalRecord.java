package com.medilabo.model;

import org.springframework.data.annotation.Id;

public class MedicalRecord {
    @Id
    private String patient_id;

    private String nom;

    private String record;
}
