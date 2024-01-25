package com.example.cloudy_spring.controller;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.cloudy_spring.models.Pct_commission;
import com.example.cloudy_spring.repository.Pct_commissionRepository;

@RestController
@RequestMapping("/Pct_commission")
public class Pct_commissionController {
    
    private final Pct_commissionRepository Pct_commissionRepository;

    @Autowired
    public Pct_commissionController(Pct_commissionRepository Pct_commissionRepository) {
        this.Pct_commissionRepository = Pct_commissionRepository;
    }

    @GetMapping("findById/{id}")
    public Pct_commission findById(@PathVariable int id) {
        return Pct_commissionRepository.findById(id);
    }

    @GetMapping("/all")
    public ArrayList<Pct_commission> findAll() {
        return (ArrayList<Pct_commission>)Pct_commissionRepository.findAll();
    }

    @PostMapping("/save/{pct}/{datetime}")
    public Pct_commission save(@PathVariable int pct,@PathVariable String datetime ) {
        String dateFormat = "yyyy-MM-dd'T'HH:mm:ss";
        
        Date date = parseDate(datetime, dateFormat);
        Timestamp maj = new Timestamp(date.getTime());

        Pct_commission Pct_commission=new Pct_commission(pct,maj);
        return Pct_commissionRepository.save(Pct_commission);
    }

    @DeleteMapping("deleteById/{id}")
    public void deleteById(@PathVariable int id) {
        Pct_commissionRepository.deleteById(id);
    }


    private static Date parseDate(String dateString, String dateFormat) {
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        try {
            return (Date) sdf.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

}
