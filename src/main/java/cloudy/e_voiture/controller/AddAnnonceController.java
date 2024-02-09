package cloudy.e_voiture.controller;

import cloudy.e_voiture.models.*;
import cloudy.e_voiture.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/addAnnonce")
public class AddAnnonceController
{
    private final MarqueRepository marqueRepository;
    private final ModelRepository modelRepository;
    private final MoteurRepository moteurRepository;
    private final CategorieRepository categorieRepository;
    private final CouleurRepository couleurRepository;
    private final TransmissionRepository transmissionRepository;
    private final CarburantRepository carburantRepository;

    @Autowired
    public AddAnnonceController(MarqueRepository marqueRepository, ModelRepository modelRepository, MoteurRepository moteurRepository, CategorieRepository categorieRepository, CouleurRepository couleurRepository, TransmissionRepository transmissionRepository, CarburantRepository carburantRepository) {
        this.marqueRepository = marqueRepository;
        this.modelRepository = modelRepository;
        this.moteurRepository = moteurRepository;
        this.categorieRepository = categorieRepository;
        this.couleurRepository = couleurRepository;
        this.transmissionRepository = transmissionRepository;
        this.carburantRepository = carburantRepository;
    }

    @GetMapping("/finAll")
    public AddAnnonceRequest findAll()
    {
        AddAnnonceRequest addAnnonceRequests = new AddAnnonceRequest();
        try
        {
            addAnnonceRequests.setMarque((List<Marque>) marqueRepository.findAll());
            addAnnonceRequests.setModel((List<Model>) modelRepository.findAll());
            addAnnonceRequests.setMoteur((List<Moteur>) moteurRepository.findAll());
            addAnnonceRequests.setCategorie((List<Categorie>) categorieRepository.findAll());
            addAnnonceRequests.setCouleur((List<Couleur>) couleurRepository.findAll());
            addAnnonceRequests.setTransmission((List<Transmission>) transmissionRepository.findAll());
            addAnnonceRequests.setCarburant((List<Carburant>) carburantRepository.findAll());
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return addAnnonceRequests;
    }
}
