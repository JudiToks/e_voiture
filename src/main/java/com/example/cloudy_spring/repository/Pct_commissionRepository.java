package com.example.cloudy_spring.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.cloudy_spring.models.Pct_commission;

public interface Pct_commissionRepository extends CrudRepository<Pct_commission,Integer> {
    
    Pct_commission findById(int id_Pct_commission);

}
