package cloudy.e_voiture.controller;

import cloudy.e_voiture.models.*;
import cloudy.e_voiture.models.connect.Connect;
import cloudy.e_voiture.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
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

    @GetMapping("/recherche/{search}")
    public HashMap<String, Object> recherche(@PathVariable String search)
    {
        HashMap<String, Object> object = new HashMap<>();
        try
        {
            List<Marque> list_marque = (List<Marque>) marqueRepository.findAll();
            List<Model> list_model = (List<Model>) modelRepository.findAll();
            List<Carburant> list_carburant = (List<Carburant>) carburantRepository.findAll();
            List<Moteur> list_moteur = (List<Moteur>) moteurRepository.findAll();
            List<Transmission> list_transmission = (List<Transmission>) transmissionRepository.findAll();
            List<Couleur> list_couleur = (List<Couleur>) couleurRepository.findAll();

            String[] mots = search.split(" ");
            StringBuilder resultat = new StringBuilder();
            for (String mot : mots)
            {
                String debutMajuscule = mot.substring(0, 1).toUpperCase() + mot.substring(1);
                resultat.append(debutMajuscule).append(" ");
            }
            String searchString = resultat.toString().trim();

            String marque = "";
            String model = "";
            String carburant = "";
            String moteur = "";
            String transmission = "";
            String couleur = "";
            for (int i = 0; i < list_marque.size(); i++)
            {
                if (searchString.contains(list_marque.get(i).getNom()))
                {
                    marque = list_marque.get(i).getNom();
                    break;
                }
            }
            for (int i = 0; i < list_model.size(); i++)
            {
                if (searchString.contains(list_model.get(i).getNom()))
                {
                    model = list_model.get(i).getNom();
                    break;
                }
            }
            for (int i = 0; i < list_carburant.size(); i++)
            {
                if (searchString.contains(list_carburant.get(i).getNom()))
                {
                    carburant = list_carburant.get(i).getNom();
                    break;
                }
            }
            for (int i = 0; i < list_moteur.size(); i++)
            {
                if (searchString.contains(list_moteur.get(i).getNom()))
                {
                    moteur = list_moteur.get(i).getNom();
                    break;
                }
            }
            for (int i = 0; i < list_transmission.size(); i++)
            {
                if (searchString.contains(list_transmission.get(i).getNom()))
                {
                    transmission = list_transmission.get(i).getNom();
                    break;
                }
            }
            for (int i = 0; i < list_couleur.size(); i++)
            {
                if (searchString.contains(list_couleur.get(i).getNom()))
                {
                    couleur = list_couleur.get(i).getNom();
                    break;
                }
            }
            Connection connection = Connect.connectToPostgre();
            List<AnnonceUser> recherche = AnnonceUser.recherche(connection, marque, model, carburant, moteur, transmission, couleur);
            object.put("recherche", recherche);
            object.put("status", new ResponseEntity<>(HttpStatus.OK));
            connection.close();
        } catch (Exception e) {
            object.put("status", new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR));
            object.put("error", e.getMessage());
        }
        return object;
    }
}
