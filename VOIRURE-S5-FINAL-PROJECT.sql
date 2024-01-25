create database e_voiture;

\c e_voiture;

CREATE SEQUENCE utilisateur_seq START 1;

CREATE TABLE Utilisateur(
   Id_User SERIAL,
   nom VARCHAR(50),
   prenom VARCHAR(50),
   email VARCHAR(50),
   mdp VARCHAR(50),
   telephone VARCHAR(50),
   est_admin BOOLEAN,
   PRIMARY KEY(Id_User)
);

CREATE SEQUENCE compte_commissionaire_seq START 1;

CREATE TABLE Compte_commissionaire(
   Id_Compte_commissionaire SERIAL,
   montant VARCHAR(50),
   date_recu TIMESTAMP,
   PRIMARY KEY(Id_Compte_commissionaire)
);

CREATE SEQUENCE marque_seq START 1;

CREATE TABLE Marque(
   Id_Marque SERIAL,
   nom VARCHAR(50),
   PRIMARY KEY(Id_Marque)
);

CREATE SEQUENCE modeles_seq START 1;

CREATE TABLE Modeles(
   Id_Modeles SERIAL,
   nom VARCHAR(50),
   PRIMARY KEY(Id_Modeles)
);

CREATE SEQUENCE couleur_seq START 1;

CREATE TABLE Couleur(
   Id_Couleur SERIAL,
   nom VARCHAR(50),
   PRIMARY KEY(Id_Couleur)
);

CREATE SEQUENCE categorie_seq START 1;

CREATE TABLE Categorie(
   Id_Categorie SERIAL,
   nom VARCHAR(50),
   PRIMARY KEY(Id_Categorie)
);

CREATE SEQUENCE moteur_seq START 1;

CREATE TABLE Moteur(
   Id_Moteur SERIAL,
   nom VARCHAR(50),
   PRIMARY KEY(Id_Moteur)
);

CREATE SEQUENCE transmission_seq START 1;

CREATE TABLE Transmission(
   Id_Transmission SERIAL,
   nom VARCHAR(50),
   PRIMARY KEY(Id_Transmission)
);

CREATE SEQUENCE carburant_seq START 1;

CREATE TABLE Carburant(
   Id_Carburant SERIAL,
   nom VARCHAR(50),
   PRIMARY KEY(Id_Carburant)
);

CREATE SEQUENCE status_lettre_seq START 1;

CREATE TABLE Status_lettre(
   Id_Status_lettre SERIAL,
   nom VARCHAR(50),
   nombre VARCHAR(50),
   PRIMARY KEY(Id_Status_lettre)
);

CREATE SEQUENCE pct_commission_seq START 1;

CREATE TABLE pct_commission(
   Id_pct_commission SERIAL,
   pourcentage INTEGER,
   date_maj TIMESTAMP,
   PRIMARY KEY(Id_pct_commission)
);

CREATE SEQUENCE annonce_seq START 1;

CREATE TABLE Annonce(
   Id_Annonce SERIAL,
   description TEXT,
   nbr_place INTEGER,
   nbr_porte INTEGER,
   etat INTEGER,
   kilometrage DOUBLE PRECISION,
   conso DOUBLE PRECISION,
   date_annonce DATE,
   annee INTEGER,
   prix DOUBLE PRECISION,
   Id_User INTEGER NOT NULL,
   Id_Carburant INTEGER NOT NULL,
   Id_Transmission INTEGER NOT NULL,
   Id_Moteur INTEGER NOT NULL,
   Id_Categorie INTEGER NOT NULL,
   Id_Couleur INTEGER NOT NULL,
   Id_Modeles INTEGER NOT NULL,
   Id_Marque INTEGER NOT NULL,
   PRIMARY KEY(Id_Annonce),
   FOREIGN KEY(Id_User) REFERENCES Utilisateur(Id_User),
   FOREIGN KEY(Id_Carburant) REFERENCES Carburant(Id_Carburant),
   FOREIGN KEY(Id_Transmission) REFERENCES Transmission(Id_Transmission),
   FOREIGN KEY(Id_Moteur) REFERENCES Moteur(Id_Moteur),
   FOREIGN KEY(Id_Categorie) REFERENCES Categorie(Id_Categorie),
   FOREIGN KEY(Id_Couleur) REFERENCES Couleur(Id_Couleur),
   FOREIGN KEY(Id_Modeles) REFERENCES Modeles(Id_Modeles),
   FOREIGN KEY(Id_Marque) REFERENCES Marque(Id_Marque)
);

CREATE SEQUENCE validation_annonce_seq START 1;

CREATE TABLE Validation_annonce(
   Id_Validation_annonce SERIAL,
   date_validation DATE,
   Id_Annonce INTEGER NOT NULL,
   PRIMARY KEY(Id_Validation_annonce),
   FOREIGN KEY(Id_Annonce) REFERENCES Annonce(Id_Annonce)
);

CREATE SEQUENCE vendu_seq START 1;

CREATE TABLE Vendu(
   Id_Vendu SERIAL,
   date_vendu DATE,
   Id_Annonce INTEGER NOT NULL,
   PRIMARY KEY(Id_Vendu),
   FOREIGN KEY(Id_Annonce) REFERENCES Annonce(Id_Annonce)
);
