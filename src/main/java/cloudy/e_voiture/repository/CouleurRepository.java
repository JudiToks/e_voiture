package cloudy.e_voiture.repository;

import cloudy.e_voiture.models.Couleur;
import org.springframework.data.repository.CrudRepository;

public interface CouleurRepository extends CrudRepository<Couleur,Integer> {

    Couleur findById(int id_Couleur);

}