package cloudy.e_voiture.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Categorie {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int Id_Categorie ;
    private String nom;

    public Categorie(String nom) {
        this.nom = nom;
    }

    @Override
    public String toString() {
        return String.format(
                "Categorie[Id_Categorie=%d, nom='%s']",
                Id_Categorie, nom);
    }

    protected Categorie(){}

    public int getId_Categorie() {
        return Id_Categorie;
    }

    public void setId_Categorie(int id_Categorie) {
        Id_Categorie = id_Categorie;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }



}
