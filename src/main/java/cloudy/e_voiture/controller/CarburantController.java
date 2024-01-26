package cloudy.e_voiture.controller;

import java.util.ArrayList;

import cloudy.e_voiture.models.Carburant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cloudy.e_voiture.repository.CarburantRepository;

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

    @DeleteMapping("deleteById/{id}")
    public void deleteById(@PathVariable int id) {
        CarburantRepository.deleteById(id);
    }


}