package cloudy.e_voiture.controller;

import cloudy.e_voiture.models.Annonce;
import cloudy.e_voiture.models.AnnonceRequest;
import cloudy.e_voiture.models.Favoris;
import cloudy.e_voiture.repository.AnnonceRepository;
import cloudy.e_voiture.repository.FavorisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.HashMap;

@CrossOrigin
@RestController
@RequestMapping("/favoris")
public class FavorisController
{
    private final FavorisRepository favorisRepository;

    @Autowired
    public FavorisController(FavorisRepository favorisRepository)
    {
        this.favorisRepository = favorisRepository;
    }

    @PostMapping("/save")
    public HashMap<String, Object> save(@RequestBody Favoris favoris)
    {
        HashMap<String, Object> object = new HashMap<>();
        try
        {
            Favoris fav = new Favoris();
            fav.setId_annonce(favoris.getId_annonce());
            fav.setId_user(favoris.getId_user());
            favorisRepository.save(fav);
            object.put("status", new ResponseEntity<>(HttpStatus.OK));
        } catch (Exception e) {
            object.put("status", new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR));
            object.put("error", e.getMessage());
        }
        return object;
    }
}
