package cloudy.e_voiture.controller;

import cloudy.e_voiture.models.Marque;
import cloudy.e_voiture.repository.MarqueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @PostMapping("/delete/{id_marque}")
    public void deleteById(@PathVariable int id_marque)
    {
        marqueRepository.deleteById(id_marque);
    }
}

