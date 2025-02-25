package com.medilabo.riskservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PatientRiskFactors {
    private String patient_id;
    private String age;
    private String genre;
    private String record;
}
