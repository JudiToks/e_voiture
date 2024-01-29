package cloudy.e_voiture.controller;

import cloudy.e_voiture.models.Transmission;
import cloudy.e_voiture.repository.TransmissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/delete/{id_transmission}")
    public void deleteById(@PathVariable int id_transmission)
    {
        transmissionRepository.deleteById(id_transmission);
    }
}

