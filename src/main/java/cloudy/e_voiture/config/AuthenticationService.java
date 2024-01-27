package cloudy.e_voiture.config;

import cloudy.e_voiture.models.*;
import cloudy.e_voiture.repository.UtilisateurRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UtilisateurRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    // @Bean
    public AuthenticationResponse register(RegisterRequest request) {
        var user= Utilisateur.builder()
                .nom(request.getNom())
                .prenom(request.getPrenom())
                .email(request.getEmail())
                .mdp(passwordEncoder.encode(request.getMdp()))
                .telephone(request.getTelephone())
                .role(Role.USER)
                .build();
        repository.save(user);
        var jwtToken =jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    // @Bean
    public AuthenticationResponse autenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var user=repository.findByEmail(request.getEmail())
                .orElseThrow();
        var jwtToken =jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

}
