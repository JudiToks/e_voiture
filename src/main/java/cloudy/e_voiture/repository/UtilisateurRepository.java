package cloudy.e_voiture.repository;

import java.util.Optional;

import cloudy.e_voiture.models.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UtilisateurRepository extends JpaRepository<Utilisateur,Integer>{

    Optional<Utilisateur> findByEmail(String email);

}
