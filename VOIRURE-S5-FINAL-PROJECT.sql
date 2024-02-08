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

-- ############################################################

-- statistique commission
SELECT
    SUM(montant) AS total_montant,
    EXTRACT(MONTH FROM date_recu) AS mois,
    EXTRACT(YEAR FROM date_recu) AS annee
FROM compte_commissionaire
GROUP BY mois, annee
ORDER BY annee, mois
LIMIT 12;


-- statistique best vendeur
select
    u.nom,
    count(v.Id_Annonce) as nombre
from Vendu v
    join Annonce a on a.Id_Annonce = v.Id_Annonce
    join Utilisateur u on u.Id_User = a.Id_User
group by u.nom
order by nombre desc
limit 7;


-- statistique best marque vendu
select
    nom,
    count(v.Id_Annonce) as nombre
from Vendu v
         join Annonce a on a.Id_Annonce = v.Id_Annonce
         join marque m on m.id_marque = a.id_marque
group by nom
order by nombre desc
limit 7;


select
    *
from annonce a
    join Status_lettre st on st.nombre = a.etat
where Id_User = 1;


with all_annonce as (
    select
        Id_Annonce
    from annonce a
        join Status_lettre st on st.nombre = a.etat
    where Id_User = 1
)
select
    *
from details_annonce
    join all_annonce on all_annonce.Id_Annonce = details_annonce.id_annonce;


create view v_image as
select
    a.id_annonce,
    url_image
from details_annonce
join public.annonce a on a.id_annonce = details_annonce.id_annonce;


create or replace view v_annonces as
select
    Id_Annonce,
    annee,
    conso,
    date_annonce,
    description,
    status_lettre.nom as nom_etat,
    C.nom as nom_carburant,
    C2.nom as nom_categorie,
    C3.nom as nom_couleur,
    M.nom as nom_marque,
    M2.nom as nom_modele,
    M3.nom as nom_moteur,
    t.nom as nom_transmission,
    Id_User,
    kilometrage,
    nbr_place,
    nbr_porte,
    prix,
    Status_lettre.nombre as etat
    from annonce
join status_lettre on Status_lettre.nombre=etat
join Carburant C on annonce.Id_Carburant = C.Id_Carburant
join Categorie C2 on C2.Id_Categorie = annonce.Id_Categorie
join Couleur C3 on C3.Id_Couleur = annonce.Id_Couleur
join Marque M on M.Id_Marque = annonce.Id_Marque
join Modeles M2 on annonce.Id_Modeles = M2.id_model
join Moteur M3 on M3.Id_Moteur = annonce.Id_Moteur
join public.transmission t on annonce.Id_Transmission = t.id_transmission;