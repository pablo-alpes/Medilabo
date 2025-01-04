package com.medilabo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "patients")
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "patient_id", nullable = false)
    private Integer patient_id;

    @Size(max = 50)
    @Column(name = "nom", length = 50)
    private String nom;

    @Size(max = 50)
    @Column(name = "prenom", length = 50)
    private String prenom;

    @Size(max = 10)
    @Column(name = "naissance", length = 10)
    private String naissance;

    @Size(max = 1)
    @Column(name = "genre", length = 1)
    private String genre;

    @Size(max = 50)
    @Column(name = "adresse", length = 50)
    private String adresse;

    @Size(max = 12)
    @Column(name = "telephone", length = 12)
    private String telephone;

}
