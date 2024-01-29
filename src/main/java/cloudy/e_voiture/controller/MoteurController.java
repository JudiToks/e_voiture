package cloudy.e_voiture.controller;

import cloudy.e_voiture.models.Moteur;
import cloudy.e_voiture.repository.MoteurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/delete/{id_moteur}")
    public void deleteById(@PathVariable int id_moteur)
    {
        moteurRepository.deleteById(id_moteur);
    }
}

