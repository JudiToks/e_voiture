package cloudy.e_voiture.controller;

import cloudy.e_voiture.models.Carburant;
import cloudy.e_voiture.models.Couleur;
import cloudy.e_voiture.models.connect.Connect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import cloudy.e_voiture.repository.CouleurRepository;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;

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

    @GetMapping("/update/{id}/{nom}")
    public HashMap<String, Object> update(@PathVariable int id, @PathVariable String nom)
    {
        HashMap<String, Object> object = new HashMap<>();
        try
        {
            Connection connection = Connect.connectToPostgre();
            Couleur.update(connection, id, nom);
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
        CouleurRepository.deleteById(id);
    }


}
