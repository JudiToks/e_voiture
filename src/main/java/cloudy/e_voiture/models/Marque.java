package cloudy.e_voiture.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Marque
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_marque;
    private String nom;

    //    getters & setters
    public int getId_marque() {
        return id_marque;
    }
    public void setId_marque(int id_marque) {
        this.id_marque = id_marque;
    }
    public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }

    //    constructor
    protected Marque() {}
    public Marque(int id_marque, String nom) {
        this.id_marque = id_marque;
        this.nom = nom;
    }

    //    utils
    public String toString()
    {
        return String.format("Marque[id_marque=%d, nom=%s]", id_marque, nom);
    }
}

