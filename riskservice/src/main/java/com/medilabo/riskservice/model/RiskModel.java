package com.medilabo.riskservice.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "risk")
@Getter
@Setter
@NoArgsConstructor
public class RiskModel {
    @Id
    private String id;
    private String patient_id;
    private String risk;
}
