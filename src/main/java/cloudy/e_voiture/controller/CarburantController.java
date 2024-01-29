package cloudy.e_voiture.controller;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;

import cloudy.e_voiture.models.Annonce;
import cloudy.e_voiture.models.AnnonceRequest;
import cloudy.e_voiture.models.Carburant;
import cloudy.e_voiture.models.connect.Connect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import cloudy.e_voiture.repository.CarburantRepository;

@CrossOrigin
@RestController
@RequestMapping("/carburant")
public class CarburantController {

    private final CarburantRepository CarburantRepository;

    @Autowired
    public CarburantController(CarburantRepository CarburantRepository) {
        this.CarburantRepository = CarburantRepository;
    }

    @GetMapping("findById/{id}")
    public Carburant findById(@PathVariable int id) {
        return CarburantRepository.findById(id);
    }

    @GetMapping("/all")
    public ArrayList<Carburant> findAll() {
        return (ArrayList<Carburant>)CarburantRepository.findAll();
    }

    @PostMapping("/save/{nom}")
    public Carburant save(@PathVariable String nom) {
        Carburant Carburant=new Carburant(nom);
        return CarburantRepository.save(Carburant);
    }

    @GetMapping("/update/{id}/{nom}")
    public HashMap<String, Object> update(@PathVariable int id, @PathVariable String nom)
    {
        HashMap<String, Object> object = new HashMap<>();
        try
        {
            Connection connection = Connect.connectToPostgre();
            Carburant.update(connection, id, nom);
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
        CarburantRepository.deleteById(id);
    }


}