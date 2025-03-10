package com.medilabo.patients.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "patient_identification")
public class PatientIdentification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "patient_id", nullable = false)
    private Integer patient_id;

    @Size(max = 50)
    @Column(name = "nom", length = 50)
    @NotNull
    private String nom;

    @Size(max = 50)
    @Column(name = "prenom", length = 50)
    @NotNull
    private String prenom;

    @Size(max = 10)
    @Column(name = "naissance", length = 10)
    @NotNull
    private String naissance;

    @Size(max = 1)
    @Column(name = "genre", length = 1)
    @NotNull
    private String genre;

}
