package cloudy.e_voiture.controller;

import cloudy.e_voiture.models.Carburant;
import cloudy.e_voiture.models.Transmission;
import cloudy.e_voiture.models.connect.Connect;
import cloudy.e_voiture.repository.TransmissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Connection;
import java.util.HashMap;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/transmission")
public class TransmissionController
{
    private final TransmissionRepository transmissionRepository;

    @Autowired
    public TransmissionController(TransmissionRepository transmissionRepository)
    {
        this.transmissionRepository = transmissionRepository;
    }

    @GetMapping("/findAll")
    public List<Transmission> findAll()
    {
        return (List<Transmission>) transmissionRepository.findAll();
    }

    @GetMapping("/findById/{id_transmission}")
    public Transmission findById(@PathVariable int id_transmission)
    {
        return transmissionRepository.findById(id_transmission);
    }

    @PostMapping("/save/{nom}")
    public Transmission save(@PathVariable String nom)
    {
        Transmission transmission = new Transmission(0, nom);
        return transmissionRepository.save(transmission);
    }

    @GetMapping("/update/{id}/{nom}")
    public HashMap<String, Object> update(@PathVariable int id, @PathVariable String nom)
    {
        HashMap<String, Object> object = new HashMap<>();
        try
        {
            Connection connection = Connect.connectToPostgre();
            Transmission.update(connection, id, nom);
            object.put("status", new ResponseEntity<>(HttpStatus.OK));
            connection.close();
        } catch (Exception e) {
            object.put("status", new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR));
            object.put("error", e.getMessage());
        }
        return object;
    }

    @PostMapping("/delete/{id_transmission}")
    public void deleteById(@PathVariable int id_transmission)
    {
        transmissionRepository.deleteById(id_transmission);
    }
}

