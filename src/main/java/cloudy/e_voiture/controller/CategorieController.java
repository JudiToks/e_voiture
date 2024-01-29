package cloudy.e_voiture.controller;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;

import cloudy.e_voiture.models.Carburant;
import cloudy.e_voiture.models.Categorie;
import cloudy.e_voiture.models.connect.Connect;
import cloudy.e_voiture.repository.CategorieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @GetMapping("/update/{id}/{nom}")
    public HashMap<String, Object> update(@PathVariable int id, @PathVariable String nom)
    {
        HashMap<String, Object> object = new HashMap<>();
        try
        {
            Connection connection = Connect.connectToPostgre();
            Categorie.update(connection, id, nom);
            object.put("status", new ResponseEntity<>(HttpStatus.OK));
            connection.close();
        } catch (Exception e) {
            object.put("status", new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR));
            object.put("error", e.getMessage());
        }
        return object;
    }

    @DeleteMapping("deleteById/{id}")
    public void deleteById(@PathVariable int id) {
        categorieRepository.deleteById(id);
    }


}
