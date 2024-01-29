package cloudy.e_voiture.controller;

import cloudy.e_voiture.models.Annonce;
import cloudy.e_voiture.models.AnnonceRequest;
import cloudy.e_voiture.repository.AnnonceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;

import static org.springframework.http.HttpStatus.OK;

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

//    @PostMapping("/save/{nom}/{description}/{nbr_place}/{nbr_porte}/{etat}/{kilometrage}/{conso}/{date_annonce}/{annee}/{id_user}/{id_carburant}/{id_transmission}/{id_moteur}/{id_categorie}/{id_couleur}/{id_modeles}/{id_marque}")
//    public Annonce save(@PathVariable String nom, @PathVariable String description, @PathVariable int nbr_place, @PathVariable int nbr_porte, @PathVariable int etat, @PathVariable double kilometrage, @PathVariable double conso, @PathVariable Date date_annonce, @PathVariable int annee, @PathVariable int id_user, @PathVariable int id_carburant, @PathVariable int id_transmission, @PathVariable int id_moteur, @PathVariable int id_categorie, @PathVariable int id_couleur, @PathVariable int id_modeles, @PathVariable int id_marque)
//    {
//        Annonce annonce = new Annonce(0, description, nbr_place, nbr_porte, etat, kilometrage, conso, date_annonce, annee, id_user, id_carburant, id_transmission, id_moteur, id_categorie, id_couleur, id_modeles, id_marque);
//        return annonceRepository.save(annonce);
//    }

    @PostMapping("/save")
    public HashMap<String, Object> save(@RequestBody AnnonceRequest annonceParam)
    {
        HashMap<String, Object> object = new HashMap<>();
        try
        {
            Annonce annonce = new Annonce(annonceParam.getDescription(), annonceParam.getNbr_place(), annonceParam.getNbr_porte(), annonceParam.getEtat(), annonceParam.getKilometrage(), annonceParam.getConso(), annonceParam.getDate_annonce(), annonceParam.getAnnee(), annonceParam.getId_user(), annonceParam.getId_carburant(), annonceParam.getId_transmission(), annonceParam.getId_moteur(), annonceParam.getId_categorie(), annonceParam.getId_couleur(), annonceParam.getId_modeles(), annonceParam.getId_marque(), annonceParam.getPrix());
            annonce.setDate_annonce(new Date(System.currentTimeMillis()));
            annonceRepository.save(annonce);
            object.put("status", new ResponseEntity<>(OK));
        }
        catch (Exception e)
        {
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
