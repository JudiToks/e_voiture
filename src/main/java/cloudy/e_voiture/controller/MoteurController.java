package cloudy.e_voiture.controller;

import cloudy.e_voiture.models.Carburant;
import cloudy.e_voiture.models.Moteur;
import cloudy.e_voiture.models.connect.Connect;
import cloudy.e_voiture.repository.MoteurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Connection;
import java.util.HashMap;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/moteur")
public class MoteurController
{
    private final MoteurRepository moteurRepository;

    @Autowired
    public MoteurController(MoteurRepository moteurRepository)
    {
        this.moteurRepository = moteurRepository;
    }

    @GetMapping("/findAll")
    public List<Moteur> findAll()
    {
        return (List<Moteur>) moteurRepository.findAll();
    }

    @GetMapping("/findById/{id_moteur}")
    public Moteur findById(@PathVariable int id_moteur)
    {
        return moteurRepository.findById(id_moteur);
    }

    @PostMapping("/save/{nom}")
    public Moteur save(@PathVariable String nom)
    {
        Moteur moteur = new Moteur(0, nom);
        return moteurRepository.save(moteur);
    }

    @GetMapping("/update/{id}/{nom}")
    public HashMap<String, Object> update(@PathVariable int id, @PathVariable String nom)
    {
        HashMap<String, Object> object = new HashMap<>();
        try
        {
            Connection connection = Connect.connectToPostgre();
            Moteur.update(connection, id, nom);
            object.put("status", new ResponseEntity<>(HttpStatus.OK));
            connection.close();
        } catch (Exception e) {
            object.put("status", new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR));
            object.put("error", e.getMessage());
        }
        return object;
    }

    @PostMapping("/delete/{id_moteur}")
    public void deleteById(@PathVariable int id_moteur)
    {
        moteurRepository.deleteById(id_moteur);
    }
}

