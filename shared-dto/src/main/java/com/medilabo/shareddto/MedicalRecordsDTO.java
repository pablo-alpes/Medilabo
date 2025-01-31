package com.medilabo.shareddto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MedicalRecordsDTO {
   private String id;
   private Integer patientId;
   private String patient;
   private String notes;
}
