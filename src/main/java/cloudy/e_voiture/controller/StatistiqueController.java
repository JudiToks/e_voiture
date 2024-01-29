package cloudy.e_voiture.controller;

import cloudy.e_voiture.models.AnnonceUser;
import cloudy.e_voiture.models.Statistique;
import cloudy.e_voiture.models.connect.Connect;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Connection;
import java.util.HashMap;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/statistique")
public class StatistiqueController
{
    @PostMapping("/bestVendeur")
    public HashMap<String, Object> findStatBestVendeur()
    {
        HashMap<String, Object> object = new HashMap<>();
        try
        {
            Connection connection = Connect.connectToPostgre();
            List<Statistique> listStat = Statistique.findBestVendeur(connection);
            object.put("listStat", listStat);
            object.put("status", new ResponseEntity<>(HttpStatus.OK));
            connection.close();
        } catch (Exception e) {
            object.put("status", new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR));
            object.put("error", e.getMessage());
        }
        return object;
    }

    @PostMapping("/bestVendu")
    public HashMap<String, Object> findStatBestVendu()
    {
        HashMap<String, Object> object = new HashMap<>();
        try
        {
            Connection connection = Connect.connectToPostgre();
            List<Statistique> listStat = Statistique.findBestMarqueVendu(connection);
            object.put("listStat", listStat);
            object.put("status", new ResponseEntity<>(HttpStatus.OK));
            connection.close();
        } catch (Exception e) {
            object.put("status", new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR));
            object.put("error", e.getMessage());
        }
        return object;
    }

    @PostMapping("/commission")
    public HashMap<String, Object> findStatCommission()
    {
        HashMap<String, Object> object = new HashMap<>();
        try
        {
            Connection connection = Connect.connectToPostgre();
            List<Statistique> listStat = Statistique.findStatCommission(connection);
            object.put("listStat", listStat);
            object.put("status", new ResponseEntity<>(HttpStatus.OK));
            connection.close();
        } catch (Exception e) {
            object.put("status", new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR));
            object.put("error", e.getMessage());
        }
        return object;
    }
}
