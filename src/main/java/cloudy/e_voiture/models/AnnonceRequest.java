package cloudy.e_voiture.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AnnonceRequest
{
    private int id_annonce;
    private String description;
    private int nbr_place;
    private int nbr_porte;
    private int etat;
    private double kilometrage;
    private double conso;
    private Date date_annonce;
    private int annee;
    private int id_user;
    private int id_carburant;
    private int id_transmission;
    private int id_moteur;
    private int id_categorie;
    private int id_couleur;
    private int id_modeles;
    private int id_marque;
    private double prix;

}
