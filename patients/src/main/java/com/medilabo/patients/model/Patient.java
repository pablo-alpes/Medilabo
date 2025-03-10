package com.medilabo.patients.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name="patient_identification")
@SecondaryTables(
        {@SecondaryTable(name="patient_contactdetails"),
        @SecondaryTable(name="patient_location")}) // https://www.baeldung.com/jpa-mapping-single-entity-to-multiple-tables
public class Patient {
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

    @Size(max = 50)
    @Column(name = "adresse", table = "patient_location", length = 50)
    @NotNull
    private String adresse;

    @Size(max = 12)
    @Column(name = "telephone", table = "patient_contactdetails", length = 12)
    @NotNull
    private String telephone;

}
