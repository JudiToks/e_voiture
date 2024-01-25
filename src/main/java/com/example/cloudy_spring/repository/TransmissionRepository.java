package com.example.cloudy_spring.repository;

import com.example.cloudy_spring.models.Transmission;
import org.springframework.data.repository.CrudRepository;

public interface TransmissionRepository extends CrudRepository<Transmission, Integer>
{
    Transmission findById(int id_transmission);
}
