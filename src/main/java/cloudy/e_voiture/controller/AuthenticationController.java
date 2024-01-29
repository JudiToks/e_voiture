package cloudy.e_voiture.controller;

import cloudy.e_voiture.config.AuthenticationService;
import cloudy.e_voiture.models.AuthenticationRequest;
import cloudy.e_voiture.models.AuthenticationResponse;
import cloudy.e_voiture.models.RegisterRequest;
import cloudy.e_voiture.models.Utilisateur;
import cloudy.e_voiture.repository.UtilisateurRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService service;
    private final UtilisateurRepository utilisateurRepository;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody RegisterRequest request
    ){
        return ResponseEntity.ok(service.register(request));
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
