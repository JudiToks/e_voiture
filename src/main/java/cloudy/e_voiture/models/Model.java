package cloudy.e_voiture.models;

import jakarta.persistence.*;

@Entity
@Table(name = "modeles")
public class Model
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_model;
    private String nom;

    //    getters & setters
    public int getId_model() {
        return id_model;
    }
    public void setId_model(int id_model) {
        this.id_model = id_model;
    }
    public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }

    //    constructor
    protected Model() {}
    public Model(int id_model, String nom) {
        this.id_model = id_model;
        this.nom = nom;
    }

    //    utils
    public String toString()
    {
        return String.format("Model[id_model=%d, nom=%s]", id_model, nom);
    }
}