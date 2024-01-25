package com.example.cloudy_spring.controller;

import com.example.cloudy_spring.models.Model;
import com.example.cloudy_spring.models.Moteur;
import com.example.cloudy_spring.repository.ModelRepository;
import com.example.cloudy_spring.repository.MoteurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/model")
public class ModelController
{
    private final ModelRepository modelRepository;

    @Autowired
    public ModelController(ModelRepository modelRepository)
    {
        this.modelRepository = modelRepository;
    }

    @GetMapping("/findAll")
    public List<Model> findAll()
    {
        return (List<Model>) modelRepository.findAll();
    }

    @GetMapping("/findById/{id_model}")
    public Model findById(@PathVariable int id_model)
    {
        return modelRepository.findById(id_model);
    }

    @PostMapping("/save/{nom}")
    public Model save(@PathVariable String nom)
    {
        Model model = new Model(0, nom);
        return modelRepository.save(model);
    }

    @PostMapping("/delete/{id_model}")
    public void deleteById(@PathVariable int id_model)
    {
        modelRepository.deleteById(id_model);
    }
}
