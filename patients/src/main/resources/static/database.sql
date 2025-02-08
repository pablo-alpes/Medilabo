/**
    DATABASE TEST
 */

CREATE DATABASE medilabo;
USE medilabo;

CREATE TABLE patient_identification (
    patient_id INT PRIMARY KEY Auto_Increment,
    nom VARCHAR(50),
    prenom VARCHAR(50),
    naissance VARCHAR(10),
    genre VARCHAR(1)
);

CREATE TABLE patient_contactdetails (
    patient_id INT PRIMARY KEY,
    telephone VARCHAR(12)
);

CREATE TABLE patient_location (
    patient_id INT PRIMARY KEY,
    adresse VARCHAR(50)
);

INSERT INTO patient_identification (patient_id, nom, prenom, naissance, genre)
VALUES
    (1, 'TestNone', 'Test', '1966-12-31', 'F'),
    (2, 'TestBorderline', 'Test', '1945-06-24', 'M'),
    (3, 'TestInDanger', 'Test', '2004-06-18', 'M'),
    (4, 'TestEarlyOnset', 'Test', '2002-06-28', 'F');

INSERT INTO patient_contactdetails (patient_id, telephone)
VALUES
    (1, '100-222-3333'),
    (2, '200-333-4444'),
    (3, '300-444-5555'),
    (4, '400-555-6666');

INSERT INTO patient_location (patient_id, adresse)
VALUES
    (1, '1 Brookside St'),
    (2, '2 High St'),
    (3, '3 Club Road'),
    (4, '4 Valley Dr');



