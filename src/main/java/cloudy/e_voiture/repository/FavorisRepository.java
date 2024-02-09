package cloudy.e_voiture.repository;

import cloudy.e_voiture.models.Favoris;
import org.springframework.data.repository.CrudRepository;

public interface FavorisRepository extends CrudRepository<Favoris, Integer>
{

}
