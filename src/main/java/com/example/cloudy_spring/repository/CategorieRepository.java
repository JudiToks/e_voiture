package com.example.cloudy_spring.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.cloudy_spring.models.Categorie;

public interface CategorieRepository extends CrudRepository<Categorie,Integer> {
    
    Categorie findById(int id_Categorie);

}
