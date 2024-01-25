package com.example.cloudy_spring.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.cloudy_spring.models.Couleur;

public interface CouleurRepository extends CrudRepository<Couleur,Integer> {
    
    Couleur findById(int id_Couleur);

}
