package com.medilabo.shareddto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class PatientDTO {

    private Integer patient_id;

    private String nom;

    private String prenom;

    private String naissance;

    private String genre;

    private String adresse;

    private String telephone;

}
