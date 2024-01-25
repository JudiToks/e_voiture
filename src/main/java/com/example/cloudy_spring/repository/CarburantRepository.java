package com.example.cloudy_spring.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.cloudy_spring.models.Carburant;

public interface CarburantRepository extends CrudRepository<Carburant,Integer> {
    
    Carburant findById(int id_Carburant);

}
