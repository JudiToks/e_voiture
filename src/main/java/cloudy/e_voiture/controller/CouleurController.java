package cloudy.e_voiture.controller;

import cloudy.e_voiture.models.Couleur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import cloudy.e_voiture.repository.CouleurRepository;

import java.util.ArrayList;

@CrossOrigin
@RestController
@RequestMapping("/couleur")
public class CouleurController {

    private final CouleurRepository CouleurRepository;

    @Autowired
    public CouleurController(CouleurRepository CouleurRepository) {
        this.CouleurRepository = CouleurRepository;
    }

    @GetMapping("findById/{id}")
    public Couleur findById(@PathVariable int id) {
        return CouleurRepository.findById(id);
    }

    @GetMapping("/all")
    public ArrayList<Couleur> findAll() {
        return (ArrayList<Couleur>)CouleurRepository.findAll();
    }

    @PostMapping("/save/{nom}")
    public Couleur save(@PathVariable String nom) {
        Couleur Couleur=new Couleur(nom);
        return CouleurRepository.save(Couleur);
    }

    @DeleteMapping("deleteById/{id}")
    public void deleteById(@PathVariable int id) {
        CouleurRepository.deleteById(id);
    }


}
