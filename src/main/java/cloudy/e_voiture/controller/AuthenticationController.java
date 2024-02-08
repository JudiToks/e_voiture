package cloudy.e_voiture.controller;

import cloudy.e_voiture.config.AuthenticationService;
import cloudy.e_voiture.models.AuthenticationRequest;
import cloudy.e_voiture.models.AuthenticationResponse;
import cloudy.e_voiture.models.RegisterRequest;
import cloudy.e_voiture.models.Utilisateur;
import cloudy.e_voiture.models.connect.Connect;
import cloudy.e_voiture.repository.UtilisateurRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import lombok.RequiredArgsConstructor;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService service;
    private final UtilisateurRepository utilisateurRepository;

    @PostMapping("/register")
    public HashMap<String, Object> register(@RequestBody RegisterRequest request) throws SQLException
    {
        HashMap<String, Object> objectHashMap = new HashMap<>();
        try
        {
            Connection connection = Connect.connectToPostgre();
            List<String> listEmail = Utilisateur.getAllEmailUser(connection);
            StringBuilder stringBuilder = new StringBuilder();
            for (String email : listEmail)
            {
                stringBuilder.append(email+" ");
            }
            if (!stringBuilder.toString().contains(request.getEmail()))
            {
                objectHashMap.put("status", ResponseEntity.ok(service.register(request)));
            }
            else
            {
                objectHashMap.put("error", "E-mail déjà utilisé");
            }
            connection.close();
        }
        catch (Exception e)
        {
            objectHashMap.put("status", new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR));
            objectHashMap.put("error", e.getMessage());
        }
        return objectHashMap;
    }


    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody AuthenticationRequest request
    ){
        return ResponseEntity.ok(service.autenticate(request));
    }

    @GetMapping("/findUserByEmail/{email}")
    public Optional<Utilisateur> findUserByEmail(@PathVariable String email)
    {
        return utilisateurRepository.findByEmail(email);
    }
}
