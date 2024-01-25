package com.example.cloudy_spring.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.cloudy_spring.models.Categorie;
import com.example.cloudy_spring.repository.CategorieRepository;

@RestController
@RequestMapping("/categorie")
public class CategorieController {
    
    private final CategorieRepository categorieRepository;

    @Autowired
    public CategorieController(CategorieRepository categorieRepository) {
        this.categorieRepository = categorieRepository;
    }

    @GetMapping("findById/{id}")
    public Categorie findById(@PathVariable int id) {
        return categorieRepository.findById(id);
    }

    @GetMapping("/all")
    public ArrayList<Categorie> findAll() {
        return (ArrayList<Categorie>)categorieRepository.findAll();
    }

    @PostMapping("/save/{nom}")
    public Categorie save(@PathVariable String nom) {
        Categorie Categorie=new Categorie(nom);
        return categorieRepository.save(Categorie);
    }

    @DeleteMapping("deleteById/{id}")
    public void deleteById(@PathVariable int id) {
        categorieRepository.deleteById(id);
    }


}
