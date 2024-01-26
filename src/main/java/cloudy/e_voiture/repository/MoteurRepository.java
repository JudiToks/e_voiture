package cloudy.e_voiture.repository;

import cloudy.e_voiture.models.Moteur;
import org.springframework.data.repository.CrudRepository;

public interface MoteurRepository extends CrudRepository<Moteur, Integer>
{
    Moteur findById(int id_moteur);
}
