package cloudy.e_voiture.controller;

import cloudy.e_voiture.models.Carburant;
import cloudy.e_voiture.models.Marque;
import cloudy.e_voiture.models.connect.Connect;
import cloudy.e_voiture.repository.MarqueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Connection;
import java.util.HashMap;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/marque")
public class MarqueController
{
    private final MarqueRepository marqueRepository;

    @Autowired
    public MarqueController(MarqueRepository marqueRepository)
    {
        this.marqueRepository = marqueRepository;
    }

    @GetMapping("/findAll")
    public List<Marque> findAll()
    {
        return (List<Marque>) marqueRepository.findAll();
    }

    @GetMapping("/findById/{id_marque}")
    public Marque findById(@PathVariable int id_marque)
    {
        return marqueRepository.findById(id_marque);
    }

    @PostMapping("/save/{nom}")
    public Marque save(@PathVariable String nom)
    {
        Marque marque = new Marque(0, nom);
        return marqueRepository.save(marque);
    }

    @GetMapping("/update/{id}/{nom}")
    public HashMap<String, Object> update(@PathVariable int id, @PathVariable String nom)
    {
        HashMap<String, Object> object = new HashMap<>();
        try
        {
            Connection connection = Connect.connectToPostgre();
            Marque.update(connection, id, nom);
            object.put("status", new ResponseEntity<>(HttpStatus.OK));
            connection.close();
        } catch (Exception e) {
            object.put("status", new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR));
            object.put("error", e.getMessage());
        }
        return object;
    }

    @PostMapping("/delete/{id_marque}")
    public void deleteById(@PathVariable int id_marque)
    {
        marqueRepository.deleteById(id_marque);
    }
}

