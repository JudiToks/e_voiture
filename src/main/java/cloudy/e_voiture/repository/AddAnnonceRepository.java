package cloudy.e_voiture.repository;

import cloudy.e_voiture.models.AddAnnonceRequest;
import org.springframework.data.repository.CrudRepository;

public interface AddAnnonceRepository extends CrudRepository<AddAnnonceRequest, Integer>
{

}
