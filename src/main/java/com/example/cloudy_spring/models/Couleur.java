package com.example.cloudy_spring.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Couleur {

@Id
@GeneratedValue(strategy=GenerationType.IDENTITY)
private int Id_Couleur ;
private String nom;

public Couleur(String nom) {
    this.nom = nom;
} 

@Override
public String toString() {
  return String.format(
      "Couleur[Id_Couleur=%d, nom='%s']",
      Id_Couleur, nom);
}

protected Couleur(){}

public int getId_Couleur() {
    return Id_Couleur;
}

public void setId_Couleur(int id_Couleur) {
    Id_Couleur = id_Couleur;
}

public String getNom() {
    return nom;
}

public void setNom(String nom) {
    this.nom = nom;
}



}
