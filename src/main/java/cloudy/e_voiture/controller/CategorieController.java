package cloudy.e_voiture.controller;

import java.util.ArrayList;

import cloudy.e_voiture.models.Categorie;
import cloudy.e_voiture.repository.CategorieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
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
