package cloudy.e_voiture.controller;

import cloudy.e_voiture.models.Annonce;
import cloudy.e_voiture.models.AnnonceRequest;
import cloudy.e_voiture.models.AnnonceUser;
import cloudy.e_voiture.models.DetailsAnnonce;
import cloudy.e_voiture.models.connect.Connect;
import cloudy.e_voiture.repository.AnnonceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Connection;
import java.sql.Date;
import java.util.HashMap;
import java.util.List;

import static org.springframework.http.HttpStatus.OK;

@CrossOrigin
@RestController
@RequestMapping("/annonce")
public class AnnonceController
{
    private final AnnonceRepository annonceRepository;

    @Autowired
    public AnnonceController(AnnonceRepository annonceRepository)
    {
        this.annonceRepository = annonceRepository;
    }

    @GetMapping("/findAll")
    public List<Annonce> findAll()
    {
        return (List<Annonce>) annonceRepository.findAll();
    }

    @GetMapping("/findById/{id_annonce}")
    public Annonce findById(@PathVariable int id_annonce)
    {
        return annonceRepository.findById(id_annonce);
    }

    @GetMapping("/findByIdUser/{id_user}")
    public List<Annonce> findByIdUser(@PathVariable int id_user)
    {
        List<Annonce> listAnnonce = Annonce.findByIdUser(null, id_user);
        return listAnnonce;
    }

    @PostMapping("/save")
    public HashMap<String, Object> save(@RequestBody AnnonceRequest annonceParam)
    {
        HashMap<String, Object> object = new HashMap<>();
        try
        {
            Annonce annonce = new Annonce(annonceParam.getDescription(), annonceParam.getNbr_place(), annonceParam.getNbr_porte(), annonceParam.getEtat(), annonceParam.getKilometrage(), annonceParam.getConso(), annonceParam.getDate_annonce(), annonceParam.getAnnee(), annonceParam.getId_user(), annonceParam.getId_carburant(), annonceParam.getId_transmission(), annonceParam.getId_moteur(), annonceParam.getId_categorie(), annonceParam.getId_couleur(), annonceParam.getId_modeles(), annonceParam.getId_marque(), annonceParam.getPrix());
            annonce.setDate_annonce(new Date(System.currentTimeMillis()));
            Annonce savedAnnonce = annonceRepository.save(annonce);

            // Retrieve the ID from the saved entity
            int id_annonce = savedAnnonce.getId_annonce();

            // Add the ID to the response
            object.put("status", new ResponseEntity<>(HttpStatus.OK));
            object.put("id_annonce", id_annonce);
        } catch (Exception e) {
            object.put("status", new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR));
            object.put("error", e.getMessage());
        }
        return object;
    }

    @PostMapping("/saveDetailsAnnonce")
    public HashMap<String, Object> saveDetailsAnnonce(@RequestBody DetailsAnnonce detailsAnnonce)
    {
        HashMap<String, Object> object = new HashMap<>();
        try
        {
            Connection connection = Connect.connectToPostgre();
            detailsAnnonce.insert(connection);
            object.put("status", new ResponseEntity<>(HttpStatus.OK));
            connection.close();
        } catch (Exception e) {
            object.put("status", new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR));
            object.put("error", e.getMessage());

        }
        return object;
    }

    @GetMapping("/findAnnonceUser/{id_user}")
    public HashMap<String, Object> findAnnonceUser(@PathVariable int id_user)
    {
        HashMap<String, Object> object = new HashMap<>();
        try
        {
            Connection connection = Connect.connectToPostgre();
            List<AnnonceUser> listAnnonceUser = AnnonceUser.getAllAnnonceUserByIdUser(connection, id_user);
            object.put("mesAnnonces", listAnnonceUser);
            object.put("status", new ResponseEntity<>(HttpStatus.OK));
            connection.close();
        } catch (Exception e) {
            object.put("status", new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR));
            object.put("error", e.getMessage());

        }
        return object;
    }

    @GetMapping("/findAllAnnonceValide")
    public HashMap<String, Object> findAllAnnonceValide(@PathVariable int id_user)
    {
        HashMap<String, Object> object = new HashMap<>();
        try
        {
            Connection connection = Connect.connectToPostgre();
            List<AnnonceUser> listAnnonce = AnnonceUser.findAllAnnonceValider(connection);
            object.put("allAnnonces", listAnnonce);
            object.put("status", new ResponseEntity<>(HttpStatus.OK));
            connection.close();
        } catch (Exception e) {
            object.put("status", new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR));
            object.put("error", e.getMessage());

        }
        return object;
    }

    @PostMapping("/updateEtat")
    public HashMap<String, Object> update(@RequestBody AnnonceRequest annonceRequest)
    {
        HashMap<String, Object> object = new HashMap<>();
        try
        {
            Connection connection = Connect.connectToPostgre();
            Annonce.updateEtatAnnonce(connection, annonceRequest.getId_annonce(), annonceRequest.getEtat());
            object.put("status", new ResponseEntity<>(HttpStatus.OK));
            connection.close();
        } catch (Exception e) {
            object.put("status", new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR));
            object.put("error", e.getMessage());
        }
        return object;
    }

    @PostMapping("/delete/{id_annonce}")
    public void deleteById(@PathVariable int id_annonce)
    {
        annonceRepository.deleteById(id_annonce);
    }
}
