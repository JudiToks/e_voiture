package cloudy.e_voiture.repository;

import cloudy.e_voiture.models.Annonce;
import org.springframework.data.repository.CrudRepository;

public interface AnnonceRepository extends CrudRepository<Annonce, Integer>
{
    Annonce findById(int id_annonce);
}
