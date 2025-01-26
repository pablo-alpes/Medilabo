package com.medilabo.medicalrecordservice.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "delete_me")
@Getter
@Setter
@NoArgsConstructor
public class MedicalRecord {
    @Id
    private String _id;

    @Field("patient_id")
    private Integer patientId;

    private String patient;
    private String notes;
}
