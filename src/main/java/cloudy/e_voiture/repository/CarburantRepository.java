package cloudy.e_voiture.repository;

import cloudy.e_voiture.models.Carburant;
import org.springframework.data.repository.CrudRepository;

public interface CarburantRepository extends CrudRepository<Carburant,Integer> {

    Carburant findById(int id_Carburant);

}
