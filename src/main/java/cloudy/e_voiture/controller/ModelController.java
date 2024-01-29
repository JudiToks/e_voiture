package cloudy.e_voiture.controller;

import cloudy.e_voiture.models.Carburant;
import cloudy.e_voiture.models.Model;
import cloudy.e_voiture.models.connect.Connect;
import cloudy.e_voiture.repository.ModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Connection;
import java.util.HashMap;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/model")
public class ModelController
{
    private final ModelRepository modelRepository;

    @Autowired
    public ModelController(ModelRepository modelRepository)
    {
        this.modelRepository = modelRepository;
    }

    @GetMapping("/findAll")
    public List<Model> findAll()
    {
        return (List<Model>) modelRepository.findAll();
    }

    @GetMapping("/findById/{id_model}")
    public Model findById(@PathVariable int id_model)
    {
        return modelRepository.findById(id_model);
    }

    @PostMapping("/save/{nom}")
    public Model save(@PathVariable String nom)
    {
        Model model = new Model(0, nom);
        return modelRepository.save(model);
    }

    @GetMapping("/update/{id}/{nom}")
    public HashMap<String, Object> update(@PathVariable int id, @PathVariable String nom)
    {
        HashMap<String, Object> object = new HashMap<>();
        try
        {
            Connection connection = Connect.connectToPostgre();
            Model.update(connection, id, nom);
            object.put("status", new ResponseEntity<>(HttpStatus.OK));
            connection.close();
        } catch (Exception e) {
            object.put("status", new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR));
            object.put("error", e.getMessage());
        }
        return object;
    }

    @PostMapping("/delete/{id_model}")
    public void deleteById(@PathVariable int id_model)
    {
        modelRepository.deleteById(id_model);
    }
}

