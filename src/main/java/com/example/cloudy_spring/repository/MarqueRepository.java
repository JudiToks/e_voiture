package com.example.cloudy_spring.repository;

import com.example.cloudy_spring.models.Marque;
import org.springframework.data.repository.CrudRepository;

public interface MarqueRepository extends CrudRepository<Marque, Integer>
{
    Marque findById(int id_marque);
}
