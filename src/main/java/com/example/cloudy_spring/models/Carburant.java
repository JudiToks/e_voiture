package com.example.cloudy_spring.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Carburant {

@Id
@GeneratedValue(strategy=GenerationType.IDENTITY)
private int Id_Carburant ;
private String nom;

public Carburant(String nom) {
    this.nom = nom;
} 

@Override
public String toString() {
  return String.format(
      "Carburant[Id_Carburant=%d, nom='%s']",
      Id_Carburant, nom);
}

protected Carburant(){}

public int getId_Carburant() {
    return Id_Carburant;
}

public void setId_Carburant(int id_Carburant) {
    Id_Carburant = id_Carburant;
}

public String getNom() {
    return nom;
}

public void setNom(String nom) {
    this.nom = nom;
}



}
