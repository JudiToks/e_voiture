package com.example.cloudy_spring.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Moteur
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_moteur;
    private String nom;

//    getters & setters
    public int getId_moteur() {
        return id_moteur;
    }
    public void setId_moteur(int id_moteur) {
        this.id_moteur = id_moteur;
    }
    public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }

//    constructor
    protected Moteur() {}
    public Moteur(int id_model, String nom) {
        this.id_moteur = id_model;
        this.nom = nom;
    }

//    utils
    public String toString()
    {
        return String.format("Moteur[id_moteur=%d, nom=%s]", id_moteur, nom);
    }
}
