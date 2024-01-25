package com.example.cloudy_spring.repository;

import com.example.cloudy_spring.models.Model;
import org.springframework.data.repository.CrudRepository;

public interface ModelRepository extends CrudRepository<Model, Integer>
{
    Model findById(int id_model);
}
